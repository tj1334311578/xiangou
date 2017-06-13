package com.example.administrator.xiangou.mine.mystore.couponmanager;

import android.content.Context;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CouponManagerContract {
    interface View extends BaseView {
        void  dataToView(CouponBean couponDatas);
        void  addCouponSuccess();
        
    }

    interface  Presenter extends BasePresenter<View> {
        void callFindCoupon(int did,String condition);
        void callAddCoupon(int did,double condition,double money,int createnum,int use_start_time,int use_end_time);
    }
}
