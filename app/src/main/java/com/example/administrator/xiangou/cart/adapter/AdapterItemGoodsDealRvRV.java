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
import com.example.administrator.xiangou.cart.model.GoodsDealBean;
import com.example.administrator.xiangou.tool.ContextUtils;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/7.
 */

public class AdapterItemGoodsDealRvRV extends RVBaseAdapter<GoodsDealBean> implements View.OnClickListener {

    private CheckBox mItemCb;
    private int position;
    private float goodsAllPrice;
    private List<CartItemCbBean> mItemCbBeanList;
    public AdapterItemGoodsDealRvRV(Context context, List<GoodsDealBean> mDatas, List<CartItemCbBean> mItemCbBeanList) {
        super(context, R.layout.item_cart_item_goods_rv, mDatas);
        this.mItemCbBeanList = mItemCbBeanList;
    }


    @Override
    protected void bindData(RVBaseViewHolder holder, GoodsDealBean goodsDealBean, final int position) {
        this.position = position;
        holder.getItemView().setTag(position);
        holder.getItemView().setOnClickListener(this);
        goodsAllPrice = goodsDealBean.getGoodsPrice()*goodsDealBean.getGoodsCount();
        mItemCb = holder.getCheckBox(R.id.cart_item_checkBox);
        // 设置CheckBox的状态
        mItemCb.setChecked(mItemCbBeanList.get(position).ischeck());
        mItemCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //将商品的checkbox的点击变化事件进行回调给第一个Recyclerview
                if (mOnCheckBoxClickListener != null){
                    mOnCheckBoxClickListener.setOnCheckBoxClick(isChecked,position);
                }
            }
        });

        holder.getCustomView(R.id.item_cart_item_goods_img).setImageResource(goodsDealBean.getGoodsImg());
        holder.getTextView(R.id.item_cart_item_goodsname).setText(goodsDealBean.getGoodsName());
        holder.getTextView(R.id.item_cart_item_goods_property).setText("颜色："+goodsDealBean.getGoodsColor()+";尺码："+goodsDealBean.getGoodsSize());
        holder.getTextView(R.id.item_cart_item_price).setText("￥"+ ContextUtils.S2places(goodsDealBean.getGoodsPrice()));
        TextView originalprice = holder.getTextView(R.id.item_cart_item_originalprice);
        originalprice.setText("￥"+ContextUtils.S2places(goodsDealBean.getGoodsOriginalPrice()));
        originalprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);//设置中划线并加清晰
        holder.getTextView(R.id.item_cart_item_goods_count).setText("x"+goodsDealBean.getGoodsCount());
        holder.getTextView(R.id.item_cart_item_goods_discount).setText(goodsDealBean.getGoodsDiscount()+"折");
    }


    protected OnCheckBoxClickListener mOnCheckBoxClickListener;

    @Override
    public void onClick(View v) {

    }

    public interface OnCheckBoxClickListener {
        //回调函数 将店铺的checkbox的点击变化事件进行回调
        void setOnCheckBoxClick(boolean isChecked, int position);
    }
    public void setOnCheckBoxClickListener(OnCheckBoxClickListener checkBoxClickListener) {
        this.mOnCheckBoxClickListener = checkBoxClickListener;
    }

}
