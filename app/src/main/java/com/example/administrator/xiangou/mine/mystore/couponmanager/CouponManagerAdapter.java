package com.example.administrator.xiangou.mine.mystore.couponmanager;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;

import java.util.List;

/**
 * 作者： tj on 2017/5/27.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */
public class CouponManagerAdapter extends RVBaseAdapter<CouponManageBean> {
    private int tag;
    private TextView startTime,endTime,remainingquantity,preferentialvalue,condition,condition1;
    private RelativeLayout bg;

    public CouponManagerAdapter(Context context, List<CouponManageBean> mDatas,int tag) {
        super(context,R.layout.seller_center_coupon_item, mDatas);
        this.tag=tag;
    }

    private void findView(RVBaseViewHolder holder) {
        startTime= holder.getTextView(R.id.seller_center_coupon_item_starttime);
        endTime= holder.getTextView(R.id.seller_center_coupon_item_endtime);
        remainingquantity= holder.getTextView(R.id.seller_center_coupon_item_remainingquantity);
        preferentialvalue=  holder.getTextView(R.id.seller_center_coupon_item_preferentialvalue);
        condition= holder.getTextView(R.id.seller_center_coupon_item_condition);
        condition1=  holder.getTextView(R.id.seller_center_coupon_item_condition1);
        bg= (RelativeLayout) holder.getView(R.id.seller_center_coupon_style_rl);
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, CouponManageBean couponManageBean, int position) {
        findView(holder);
        switch (tag){
            case 1:
                style1();
                break;
            case 2:
                style1();
                break;
            case 3:
                style2();
                break;
        }

        dataToView(couponManageBean);
    }

    private void dataToView(CouponManageBean couponManageBean) {
        condition.setText(couponManageBean.getCondition());
        startTime.setText(couponManageBean.getStartTime());
        endTime.setText(couponManageBean.getEndTime());
        remainingquantity.setText(couponManageBean.getRemainingquantity());
        preferentialvalue.setText(couponManageBean.getPreferentialvalue());
        condition1.setText(couponManageBean.getCondition1());
    }

    //设置样式2
    private void style2() {
        condition.setTextColor(mContext.getResources().getColor(R.color.white));
        startTime.setTextColor(mContext.getResources().getColor(R.color.white_b3));
        endTime.setTextColor(mContext.getResources().getColor(R.color.white_b3));
        remainingquantity.setTextColor(mContext.getResources().getColor(R.color.white_b3));
        preferentialvalue.setTextColor(mContext.getResources().getColor(R.color.white));
        condition1.setTextColor(mContext.getResources().getColor(R.color.white_b3));
        bg.setBackgroundResource(R.mipmap.privilege_management_lose_efficacy_background_color);
    }
    //设置样式1
    private void style1() {
        condition.setTextColor(mContext.getResources().getColor(R.color.textcolor_pink));
        startTime.setTextColor(mContext.getResources().getColor(R.color.cart_textb316color));
        endTime.setTextColor(mContext.getResources().getColor(R.color.cart_textb316color));
        remainingquantity.setTextColor(mContext.getResources().getColor(R.color.textcolor_pink));
        preferentialvalue.setTextColor(mContext.getResources().getColor(R.color.textcolor_pink));
        condition1.setTextColor(mContext.getResources().getColor(R.color.red_b3pink));
        bg.setBackgroundResource(R.mipmap.privilege_management_use_background_color);
    }
}
