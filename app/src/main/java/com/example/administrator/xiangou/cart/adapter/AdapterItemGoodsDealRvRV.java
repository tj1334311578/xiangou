package com.example.administrator.xiangou.cart.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.cart.model.CartItemCbBean;
import com.example.administrator.xiangou.cart.model.CartMergeItemBean;
import com.example.administrator.xiangou.tool.ContextUtils;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/7.
 */

public class AdapterItemGoodsDealRvRV extends RVBaseAdapter<CartMergeItemBean> implements View.OnClickListener {

    private CheckBox mItemCb;
    private float goodsAllPrice;
    private CartItemCbBean mItemCbBean;

    public AdapterItemGoodsDealRvRV(Context context, List<CartMergeItemBean> mergeItemBeanList) {
        super(context, R.layout.item_cart_item_goods_rv, mergeItemBeanList);
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, CartMergeItemBean cartMergeItemBean, final int position) {
        mItemCbBean = cartMergeItemBean.getItemCbBean();
//        holder.getItemView().setTag(position);
        holder.getItemView().setOnClickListener(this);
        goodsAllPrice = cartMergeItemBean.getGoodsDealBean().getGoodsPrice()*cartMergeItemBean.getGoodsDealBean().getGoodsCount();
        mItemCb = holder.getCheckBox(R.id.cart_item_checkBox);
        // 设置CheckBox的状态
        mItemCb.setChecked(mItemCbBean.ischeck());
        mItemCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //将商品的checkbox的点击变化事件进行回调给第一个Recyclerview
                if (mOnCheckBoxClickListener != null){
                    mOnCheckBoxClickListener.setOnCheckBoxClick(isChecked,position);
                }
            }
        });

        holder.getCustomView(R.id.item_cart_item_goods_img).setImageResource(cartMergeItemBean.getGoodsDealBean().getGoodsImg());
        holder.getTextView(R.id.item_cart_item_goodsname).setText(cartMergeItemBean.getGoodsDealBean().getGoodsName());
        holder.getTextView(R.id.item_cart_item_goods_property).setText("颜色："+cartMergeItemBean.getGoodsDealBean().getGoodsColor()+";尺码："+cartMergeItemBean.getGoodsDealBean().getGoodsSize());
        holder.getTextView(R.id.item_cart_item_price).setText("￥"+ ContextUtils.S2places(cartMergeItemBean.getGoodsDealBean().getGoodsPrice()));
        TextView originalprice = holder.getTextView(R.id.item_cart_item_originalprice);
        originalprice.setText("￥"+ContextUtils.S2places(cartMergeItemBean.getGoodsDealBean().getGoodsOriginalPrice()));
        originalprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);//设置中划线并加清晰
        holder.getTextView(R.id.item_cart_item_goods_count).setText("x"+cartMergeItemBean.getGoodsDealBean().getGoodsCount());
        holder.getTextView(R.id.item_cart_item_goods_discount).setText(cartMergeItemBean.getGoodsDealBean().getGoodsDiscount()+"折");

        holder.getItemView().setId(position);
    }

    @Override
    public void onClick(View v) {

    }

    protected OnCheckBoxClickListener mOnCheckBoxClickListener;
    public interface OnCheckBoxClickListener {
        //回调函数 将店铺的checkbox的点击变化事件进行回调
        void setOnCheckBoxClick(boolean isChecked, int position);
    }
    public void setOnCheckBoxClickListener(OnCheckBoxClickListener checkBoxClickListener) {
        this.mOnCheckBoxClickListener = checkBoxClickListener;
    }

}
