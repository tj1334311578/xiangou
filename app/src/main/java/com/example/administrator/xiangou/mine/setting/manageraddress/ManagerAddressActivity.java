package com.example.administrator.xiangou.mine.setting.manageraddress;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ManagerAddressActivity extends MVPBaseActivity<ManagerAddressContract.View, ManagerAddressPresenter> implements ManagerAddressContract.View {
    private static final int REQUEST = 100;
    private RecyclerView mRecyclerView;
    private Button newaddressBtn;
    private ImageView backBtn;
    private TextView TitleTv,SaveTv;
    private int editposition;
    private AddressAdapter adapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null) {
        Log.e("onActivityResult", "onActivityResult: "+data.getStringExtra("type") +"\n position  "+editposition);
            if (resultCode == RESULT_OK && requestCode == REQUEST) {
//                AddressBean addressBean = (AddressBean) data.getSerializableExtra("data");
//                UserAddressBean.DataBean bean = new UserAddressBean.DataBean();
//                bean.setConsignee(addressBean.getConsignee());
//                bean.setMobile(addressBean.getMobile());
//                bean.setProvince_id(addressBean.getProvince());
//                bean.setCity_id(addressBean.getCity());
//                bean.setDistrict_id(addressBean.getDistrict());
//                bean.setAddress(addressBean.getAddress());
//                bean.setUser_id(addressBean.getUser_id());
//                bean.setAddress_id(addressBean.getAddress_id());
//                bean.setMap_x(addressBean.getMap_x());
//                bean.setMap_y(addressBean.getMap_y());
//                this.list.add(bean);
//                adapter.notifyItemInserted(list.size()-1);
                mPresenter.getUserAddressList(bUser.getUser_id(),data.getStringExtra("type"));
            }
            adapter.notifyItemRangeChanged(0,list.size());
        }
    }

//    private UserAddressBean.DataBean bean2bean(AddressBean bean){
//        UserAddressBean.DataBean dataBean = new UserAddressBean.DataBean();
//        dataBean.setAddress(bean.getAddress());
//        dataBean.setConsignee(bean.getConsignee());
//        dataBean.setMobile(bean.getMobile());
//        dataBean.setUser_id(bean.getUser_id());
//        dataBean.setProvince_id(bean.getProvince());
//        return dataBean;
//    }
    public List<UserAddressBean.DataBean> getList() {
        return list;
    }

    public void setList(List<UserAddressBean.DataBean> list) {
        this.list = list;
    }

    private List<UserAddressBean.DataBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manageraddress);
        mPresenter.getUserAddressList(bUser.getUser_id(), "get");
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
        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);//关闭动画。

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
            // TODO: 2017/4/24 添加新地址
            UserAddressBean.DataBean dataBean = new UserAddressBean.DataBean();
            dataBean.setUser_id(bUser.getUser_id());
            String[] keys = {"edit_address","type"};
            Object[] values = {dataBean,"add"};
            startNewUIForResult(EditAddressActivity.class,REQUEST,keys,values);
        }
    }

    @Override
    public void dealDataToView(List<UserAddressBean.DataBean> data, String type) {
        if (data!=getList()){
            setList(data);

            Log.e("adapter", "dealDataToView: " + adapter);
            if (adapter == null) {
                Log.e("new adapter", "dealDataToView: new ++++");
                adapter = new AddressAdapter(this, getList());
                adapter.setAddressManagerListener(new AddressAdapter.AddressManagerListener() {
                    @Override
                    public void dealCheckBox(CompoundButton buttonView, boolean isChecked, int position) {
                        //                                        if (buttonView.getVerticalScrollbarPosition()!=position){
                        //                                            position = buttonView.getVerticalScrollbarPosition();
                        //                                        }
                        Log.e("list", "dealDataToView: " + " size= " + position + getList());
                        if (isChecked) {
                            if (getList().get(position).getIs_default() != 1) {
                                mPresenter.setDefaultAddress(bUser.getUser_id(), getList().get(position).getAddress_id(), position);
                                for (int i = 0; i < getList().size(); i++) {
                                    if (getList().get(i).getIs_default() == 1 && i != position) {
                                        getList().get(i).setIs_default(0);
                                    }
                                }
                                getList().get(position).setIs_default(1);
                            } else {
                                showToast("已是默认地址！");
                            }
                        }
                    }

                    @Override
                    public void dealEditTextTv(View v, int position) {
                        String[] keys = {"edit_address","type"};
                        Object[] values = {list.get(position),"edit"};
                        startNewUIForResult(EditAddressActivity.class,REQUEST,keys,values);
                        editposition = position;
                    }

                    @Override
                    public void dealDelTextTv(View v, final int position) {
                        AlertDialog.Builder delDialog = new AlertDialog.Builder(ManagerAddressActivity.this);
                        delDialog.setTitle("删除收货地址");
                        delDialog.setMessage("是否决定删除此条收货地址:\n" + list.get(position).getAddress());
                        delDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        delDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.deleteUserAddress(bUser.getUser_id(), list.get(position).getAddress_id(), position);
                                dialog.dismiss();
                            }
                        });
                        delDialog.create().show();
                    }
                });
                mRecyclerView.setAdapter(adapter);
            } else {
                if (type.equals("add")){
                    adapter.notifyItemInserted(list.size()-1);
                }else if (type.equals("edit")) {
                    adapter.notifyItemRangeChanged(0, list.size());
                }
                Log.e("updata adapter", "dealDataToView: updata*****");
            }
        }else {
            mPresenter.getUserAddressList(bUser.getUser_id(),type);
        }
    }

//    public void toUpdataView(final int startpos, final int count) {
//        Handler handler = new Handler();
//        final Runnable r = new Runnable() {
//            public void run() {
//                adapter.notifyItemRangeChanged(startpos,count);//更新
//            }
//        };
//        handler.post(r);
//    }

    @Override
    public void setDefaultAddressSuccess(String str, int position) {
        Log.e("setDefault", "setDefaultAddressSuccess: " +position );
        adapter.notifyItemRangeChanged(0,list.size());
        showToast(str);
    }

    @Override
    public void deleteAddressSuccess(String str, int address_id, int position) {
        getList().remove(position);
        adapter.notifyItemRemoved(position);
        showToast(str);
    }
}
