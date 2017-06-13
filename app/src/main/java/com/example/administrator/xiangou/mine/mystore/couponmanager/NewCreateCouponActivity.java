package com.example.administrator.xiangou.mine.mystore.couponmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.ContextUtils;
import com.example.administrator.xiangou.tool.TimeStampUtil;

import java.text.ParseException;

import butterknife.internal.Utils;

/**
 * 作者： tj on 2017/5/31.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class NewCreateCouponActivity extends MVPBaseActivity<CouponManagerContract.View,CouponManagerPresenter> implements  CouponManagerContract.View{
    private EditText conditionEdit,preferentialEdit,totalamountissuedEdit,startTimeEdit,endTimeEdit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewcoupon);
        initView();
    }

    private void initView() {
        findContentView(R.id.newCreateCouponBackBtn);
        findContentView(R.id.addnewcoupon_savedataBtn);
        conditionEdit=findContentView(R.id.addnewcoupon_order_amount_edit,false);
        preferentialEdit=findContentView(R.id.addnewcoupon_preferential_value_edit,false);
        totalamountissuedEdit=findContentView(R.id.addnewcoupon_totalamountissued_edit,false);
        startTimeEdit=findContentView(R.id.addnewcoupon_entry_into_force_time_edit,false);
        endTimeEdit=findContentView(R.id.addnewcoupon_expiration_date_edit,false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newCreateCouponBackBtn:
                finish();
                break;
            case R.id.addnewcoupon_savedataBtn:
                saveData();
                break;
        }

    }
    //保存数据并提交请求
    private void saveData() {
        int did=1;
        Double condition = ContextUtils.D2places(Double.parseDouble(conditionEdit.getText().toString().replaceAll(" ","")));
        Double money=ContextUtils.D2places(Double.parseDouble(preferentialEdit.getText().toString().replaceAll(" ","")));
        int createnum=(int) Double.parseDouble(totalamountissuedEdit.getText().toString().replaceAll(" ",""));
        int use_start_time=0,use_end_time=0;
        try {
            use_start_time= (int) TimeStampUtil.StringToFormatTimeStamp(startTimeEdit.getText().toString().replaceAll(" ",""));
            use_end_time= (int) TimeStampUtil.StringToFormatTimeStamp(endTimeEdit.getText().toString().replaceAll(" ",""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (did!=0&&condition!=0&&money!=0&&createnum!=0&&use_start_time!=0&&use_end_time!=0){
            mPresenter.callAddCoupon(did,condition,money,createnum,use_start_time,use_end_time);
        }else{
            showToast("所填参数不规范或缺少参数！！！");
        }
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void dataToView(CouponBean couponDatas) {

    }

    @Override
    public void addCouponSuccess() {
        showToast("添加优惠券成功");
        finish();
    }
}
