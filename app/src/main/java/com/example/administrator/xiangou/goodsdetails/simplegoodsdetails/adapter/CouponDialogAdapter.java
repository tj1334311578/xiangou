package com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.SimpleGoodsDetialBean;

import java.util.List;

/**
 * 作者： tj on 2017/5/22.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */
public class CouponDialogAdapter extends RVBaseAdapter<SimpleGoodsDetialBean.DataBean.CouponBean> {
    public CouponDialogAdapter(Context context, List<SimpleGoodsDetialBean.DataBean.CouponBean> coupon) {
        super(context, R.layout.simple_goodsdetails_coupon_dialog_item,coupon);
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, SimpleGoodsDetialBean.DataBean.CouponBean couponBean, int position) {
        holder.getTextView(R.id.simple_goodsdetails_coupon_dialog_item_value).setText(couponBean.getMoney());
        holder.getTextView(R.id.simple_goodsdetails_coupon_dialog_item_condition).setText("满"+couponBean.getCondition()+"元使用");
        holder.getTextView(R.id.simple_goodsdetails_coupon_dialog_item_date).setText(couponBean.getUse_start_time()+"-"+couponBean.getUse_end_time());
        final TextView Tv1=holder.getTextView(R.id.simple_goodsdetails_coupon_dialog_item_alreadyreceived);
        final TextView Tv=holder.getTextView(R.id.simple_goodsdetails_coupon_dialog_item_receive);
        if (couponBean.getIs_get()==0){//未领取优惠券
            //gone掉已领item TextView
            Tv1.setVisibility(View.GONE);
            //显示未领item
            Tv.setVisibility(View.VISIBLE);
            Tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo:上传领取优惠券数据并更改界面数据显示
                    Toast.makeText(mContext, "领取成功", Toast.LENGTH_SHORT).show();
                    Tv1.setVisibility(View.VISIBLE);
                    Tv.setVisibility(View.GONE);
                }
            });
        }else{
            Tv.setVisibility(View.GONE);
            Tv1.setVisibility(View.VISIBLE);
        }
    }

}
