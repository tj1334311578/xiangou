package com.example.administrator.xiangou.goods_details.simplegoodsdetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.AutoRVAdapter;
import com.example.administrator.xiangou.tool.ContextUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
public class CouponAdapter extends AutoRVAdapter {
    List<SimpleGoodsDetialBean.DataBean.CouponBean> coupon;
    public CouponAdapter(Context context, List<SimpleGoodsDetialBean.DataBean.CouponBean> coupon) {
        super(context,coupon);
        this.coupon=coupon;
    }

    @Override
    public int getItemCount() {
        if (coupon!=null&&coupon.size()<=2)
        return super.getItemCount();
        else{
            return 2;
        }
    }

    @Override
    protected int onCreateViewHolderID(int viewType) {
        return R.layout.simple_goodsdetails_getcoupons_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setTextView(R.id.simpele_coupon_item_Tv,"满"+(int)Double.parseDouble(coupon.get(position).getCondition())+"减"+(int)Double.parseDouble(coupon.get(position).getMoney())+"元");
    }
}
