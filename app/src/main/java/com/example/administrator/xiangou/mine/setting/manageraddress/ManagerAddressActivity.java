package com.example.administrator.xiangou.mine.setting.manageraddress;


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
    private static final int REQUEST1 = 110;
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
                Log.e("onActivityResult", "onActivityResult: "+data.getSerializableExtra("data") +"\n position  "+editposition);
            if (resultCode == 200 && requestCode == REQUEST) {
                this.list.add((AddressBean) data.getSerializableExtra("data"));
            } else if (resultCode == 200 && requestCode == REQUEST1) {
                this.list.set(editposition, (AddressBean) data.getSerializableExtra("data"));
            }
        }
        Log.e("list", "onActivityResult: "+this.list.toString() );
        adapter.notifyItemRangeChanged(0,list.size());
    }

    public List<AddressBean> getList() {
        return list;
    }

    public void setList(List<AddressBean> list) {
        this.list = list;
    }

    private List<AddressBean> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manageraddress);
        mPresenter.getUserAddressList(bUser.getUser_id());
        initView();
    }

    private void initView() {

        mRecyclerView=findContentView(R.id.manageraddress_recycle);
        newaddressBtn=findContentView(R.id.manageraddress_newaddressBtn,true);
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

    }

    @Override
    public void onClick(View v) {
        if (v==backBtn){
            finish();
        }else if (v==newaddressBtn){
            // TODO: 2017/4/24 添加新地址
            startNewUIForResult(EditAddressActivity.class,REQUEST,"addresstype","add");
        }
    }

    @Override
    public void dealDataToView(final List<UserAddressBean.DataBean> data) {
        adapter = new AddressAdapter(this, data);
        adapter.setAddressManagerListener(new AddressAdapter.AddressManagerListener() {
            @Override
            public void dealCheckBox(CompoundButton buttonView, boolean isChecked, int position) {
                mPresenter.setDefaultAddress(bUser.getUser_id(),data.get(position).getAddress_id());
            }

            @Override
            public void dealEditTextTv(View v, int position) {
                startNewUIForResult(EditAddressActivity.class,REQUEST1,"name",data.get(position));
                editposition = position;
            }

            @Override
            public void dealDelTextTv(View v, int position) {

            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setDefaultAddressSuccess(String str) {
        showToast(str);
    }
}
