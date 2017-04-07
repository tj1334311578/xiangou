package com.example.administrator.xiangou.cart.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.BaseAdapter;
import com.example.administrator.xiangou.base.BaseViewHolder;
import com.example.administrator.xiangou.cart.model.GoodsDealBean;
import com.example.administrator.xiangou.tool.ContextUtils;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/7.
 */

public class AdapterItemGoodsDealRv extends BaseAdapter<GoodsDealBean> implements View.OnClickListener{

    private CheckBox mItemCb;
    private boolean isCheckedAll,isEditAll;
    private float goodsAllPrice;
    public AdapterItemGoodsDealRv(Context context, List<GoodsDealBean> mDatas, boolean isCheckedAll) {
        super(context, R.layout.item_cart_item_goods_rv, mDatas);
        this.isCheckedAll = isCheckedAll;
    }

    @Override
    protected void bindData(BaseViewHolder holder, GoodsDealBean goodsDealBean, int position) {
        goodsAllPrice = goodsDealBean.getGoodsPrice()*goodsDealBean.getGoodsCount();
        mItemCb = holder.getCheckBox(R.id.cart_item_checkBox);
        mOnItemClickListener.onMineItemClick(mItemCb,position);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cart_item_checkBox:
                if (mItemCb.isChecked()){
                    isCheckedAll = true;
                }else {
                    isCheckedAll =false;
                }
                break;
        }
    }
}
