package com.example.administrator.xiangou.mine.setting.manageraddress;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mine.setting.manageraddress.editaddress.EditAddressActivity;
import com.example.administrator.xiangou.mine.setting.manageraddress.model.UserAddressBean;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;

import java.util.List;

public class ManagerAddressActivity extends MVPBaseActivity<ManagerAddressContract.View, ManagerAddressPresenter>
        implements ManagerAddressContract.View {

    private RecyclerView mRecyclerView;
    private Button newaddressBtn;
    private ImageView backBtn;
    private TextView TitleTv,SaveTv;
    private AddressAdapter adapter;
    private List<UserAddressBean.DataBean> mAddressDataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manageraddress);
        mPresenter.getUserAddressList(getUser().getUser_id(),"get");
        initView();
    }

    private void initView() {

        mRecyclerView=findContentView(R.id.manageraddress_recycle,false);
        newaddressBtn=findContentView(R.id.manageraddress_newaddressBtn);
        backBtn= (ImageView) findViewById(R.id.manageraddress_head).findViewById(R.id.setting_head_back);
        TitleTv= (TextView) findViewById(R.id.manageraddress_head).findViewById(R.id.setting_head_center);
        SaveTv= (TextView) findViewById(R.id.manageraddress_head).findViewById(R.id.setting_head_right);
        findContentView(backBtn.getId(),true);
        SaveTv.setVisibility(View.GONE);
        TitleTv.setText("管理收货地址");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);//关闭动画
    }

    private void initAdapter(List<UserAddressBean.DataBean> datas) {
        final List<UserAddressBean.DataBean> data = datas;

        adapter = new AddressAdapter(this, data);
        mRecyclerView.setAdapter(adapter);
        adapter.setAddressManagerListener(new AddressAdapter.AddressManagerListener() {
            @Override
            public void dealCheckBox(CompoundButton buttonView, boolean isChecked, int position) {
                Log.e("list", "dealDataToView: " + " size= " + position + data);
                if (isChecked) {
                    if (data.get(position).getIs_default() != 1) {
                        for (int i = 0; i < data.size(); i++) {
                            if (data.get(i).getIs_default() == 1 && i != position) {
                                data.get(i).setIs_default(0);
                            }
                        }
                        data.get(position).setIs_default(1);
                        //更改默认地址位置0
                        if (position!=0){
                            Log.e("data look", "dealCheckBox: 0=" + data.get(0)+"\n"+position+"="+data.get(position));
                            UserAddressBean.DataBean temp = data.get(position);
                            data.remove(position);
                            data.add(position,data.get(0));
                            data.remove(0);
                            data.add(0,temp);
                            Log.e("data look", "dealCheckBox: 0=" + data.get(0)+"\n"+position+"="+data.get(position));
                        }
                        toUpdataView(0,data.size());
                        mPresenter.setDefaultAddress(getUser().getUser_id(), data.get(0).getAddress_id());
                    }
                }
            }

            @Override
            public void dealEditTextTv(View v, int position) {
                String[] keys = {"edit_address","type"};
                Object[] values = {data.get(position),"edit"};
                startNewUICarryStr(EditAddressActivity.class,keys,values);
            }

            @Override
            public void dealDelTextTv(View v, final int position) {
                AlertDialog.Builder delDialog = new AlertDialog.Builder(ManagerAddressActivity.this);
                delDialog.setTitle("删除收货地址");
                delDialog.setMessage("是否决定删除此条收货地址:\n" + data.get(position).getAddress());
                delDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                delDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteUserAddress(getUser().getUser_id(), data.get(position).getAddress_id());
                        Log.e("position", "onClick: " + position+" = "+ adapter.getDatas().size());
                        adapter.getDatas().remove(position);
                        Log.e("position", "onClick: " + adapter.getDatas().size());
                        adapter.notifyItemRemoved(position);
                        toUpdataView(0,data.size());
                        dialog.dismiss();
                    }
                });
                delDialog.create().show();
            }
        });
    }

    @Override
    public void sendFialRequest(String message) {
        showToast(message);
    }

    @Override
    public void onClick(View v) {
        if (v==backBtn){
            finish();
        }else if (v==newaddressBtn){
            UserAddressBean.DataBean dataBean = new UserAddressBean.DataBean();
            dataBean.setUser_id(getUser().getUser_id());
            String[] keys = {"edit_address","type"};
            Object[] values = {dataBean,"add"};
            startNewUICarryStr(EditAddressActivity.class,keys,values);
        }
    }

    @Override
    public void dealDataToView(final List<UserAddressBean.DataBean> data, String type) {
        mAddressDataList = data;
        Log.e("new adapter",data.size()+" dealDataToView: ++++ "+type+"\n"+mAddressDataList.toString());
        if (type.equals("get")){
            initAdapter(mAddressDataList);
        }
    }

    public void toUpdataView(final int startpos, final int count) {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                adapter.notifyItemRangeChanged(startpos,count);//更新
            }
        };
        handler.post(r);
    }

    @Override
    public void setDefaultAddressSuccess(String str) {
        showToast(str);
    }

    @Override
    public void deleteAddressSuccess(String str) {
        showToast(str);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.getUserAddressList(getUser().getUser_id(),"get");
    }
}
