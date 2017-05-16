package com.example.administrator.xiangou.mine.couponpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.AutoRVAdapter;
import com.example.administrator.xiangou.tool.ContextUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
public class CouponPageAdapter extends AutoRVAdapter {
    List<CouponBean> lists;
    public CouponPageAdapter(Context context, List<CouponBean> lists) {
        super(context,lists);
        this.lists=lists;
    }

    @Override
    protected int onCreateViewHolderID(int viewType) {
        if (lists.get(0).isuse()){
            //设置可用优惠券所需布局
            return R.layout.coupon_item_viable;
        }else
        {
            //设置不可用优惠券所需布局
            return R.layout.coupon_item_unviable;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (lists.get(0).isuse()){
            //设置可用优惠券所需布局
            setViable(holder,position);
        }else
        {
            //设置不可用优惠券所需布局
            setInviable(holder,position);
        }

    }
    //设置不可用优惠券数据
    private void setInviable(ViewHolder holder, int position) {
        if (lists.get(position).getCouponStyle()==2){
        holder.setTextView(R.id.coupon_item_unviable_style,"全场券·闲购专用");
        }else{
            holder.setTextView(R.id.coupon_item_unviable_style,"店铺券·轩轩格调店铺");
        }
        holder.setTextView(R.id.coupon_item_unviable_discount, ContextUtils.S2places(lists.get(position).getDiscount()));
        holder.setTextView(R.id.coupon_item_unviable_condition,"[满"+lists.get(position).getCondition()+"元可用]");
        holder.setTextView(R.id.coupon_item_unviable_effectivedate,lists.get(position).getEffectiveDate());
    }
    //设置可用优惠券数据
    private void setViable(ViewHolder holder, int position) {
        if (lists.get(position).getCouponStyle()==2){
            holder.setTextView(R.id.coupon_item_viable_style,"全场券·闲购专用");
        }else{
            holder.setTextView(R.id.coupon_item_viable_style,"店铺券·轩轩格调店铺");
        }
        holder.setTextView(R.id.coupon_item_viable_discount, ContextUtils.S2places(lists.get(position).getDiscount()));
        holder.setTextView(R.id.coupon_item_viable_condition,"[满"+lists.get(position).getCondition()+"元可用]");
        holder.setTextView(R.id.coupon_item_viable_effectivedate,lists.get(position).getEffectiveDate());
    }
}
