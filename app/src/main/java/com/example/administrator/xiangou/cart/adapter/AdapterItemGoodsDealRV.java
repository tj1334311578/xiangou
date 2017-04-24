package com.example.administrator.xiangou.cart.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.cart.model.CartMergeItemBean;
import com.example.administrator.xiangou.tool.ContextUtils;
import com.example.administrator.xiangou.tool.CustomImageView;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/7.
 */

public class AdapterItemGoodsDealRV extends RVBaseAdapter<CartMergeItemBean> {

    private CheckBox mItemCb;
    private CustomImageView mGoodsImgCiv;
    private TextView mDeleteGoodsTv,mGoodsNameTv,mGoodsPropertyTv,mGoodsPriceTv,mOriginalpriceTv,mGoodsCountTv;
    private ImageView mDecreaseCountIv,mAddCountIv,mEditGoodsIv;
//    private int position;

    public AdapterItemGoodsDealRV(Context context, List<CartMergeItemBean> mergeItemBeanList) {
        super(context, R.layout.item_cart_item_goods_rv, mergeItemBeanList);
    }

    @Override
    protected void bindData(final RVBaseViewHolder holder, final CartMergeItemBean cartMergeItemBean, final int position) {
//        this.position = position;
        mGoodsImgCiv = holder.getCustomView(R.id.item_cart_item_goods_img);

        // 设置CheckBox的状态
        mItemCb = holder.getCheckBox(R.id.cart_item_checkBox);
        mItemCb.setChecked(cartMergeItemBean.getItemStatusBean().ischeck());
        mItemCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //将商品的checkbox的点击变化事件进行回调给第一个Recyclerview
                if (mOnItemGoodsClickListener != null){
                    mOnItemGoodsClickListener.setOnCheckBoxClick(isChecked,position);
                }
            }
        });
        mGoodsImgCiv.setImageResource(cartMergeItemBean.getGoodsDealBean().getGoodsImg());

        if (cartMergeItemBean.getItemStatusBean().isHasToEditGoods()){
            holder.getView(R.id.item_cart_deal_edit_rl).setVisibility(View.VISIBLE);
            holder.getView(R.id.item_cart_deal_before_ll).setVisibility(View.GONE);

            mGoodsCountTv = holder.getTextView(R.id.item_cart_item_goods_count);
            mGoodsCountTv.setTag(mDatas.get(position));
            mGoodsCountTv.setText(cartMergeItemBean.getGoodsDealBean().getGoodsCount()+"");
            holder.getTextView(R.id.item_cart_item_goods_color).setText("颜色："+cartMergeItemBean.getGoodsDealBean().getGoodsColor());
            holder.getTextView(R.id.item_cart_item_goods_inch).setText("尺码："+cartMergeItemBean.getGoodsDealBean().getGoodsSize());
            mDeleteGoodsTv = holder.getTextView(R.id.item_cart_item_goods_delete);
            mDecreaseCountIv = holder.getImageView(R.id.item_cart_item_goods_decrease);
            mAddCountIv = holder.getImageView(R.id.item_cart_item_goods_add);
            mEditGoodsIv = holder.getImageView(R.id.item_cart_item_goods_edit);
            mDeleteGoodsTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //删除商品
                    mOnItemGoodsClickListener.setOnDeleteGoodsClick((TextView) v,position);
                }
            });
            mDecreaseCountIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //减少商品数量
                    if (holder.getTextView(R.id.item_cart_item_goods_count).getTag() == mDatas.get(position))
                    {
                        mGoodsCountTv = holder.getTextView(R.id.item_cart_item_goods_count);
                    }
                    mOnItemGoodsClickListener.setOnDecreaseGoodsClick((ImageView) v,position, mGoodsCountTv);
                }
            });
            mAddCountIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //增加商品数量
                    if (holder.getTextView(R.id.item_cart_item_goods_count).getTag() == mDatas.get(position))
                    {
                        mGoodsCountTv = holder.getTextView(R.id.item_cart_item_goods_count);
                    }
                    mOnItemGoodsClickListener.setOnAddGoodsClick((ImageView) v,position, mGoodsCountTv);
                }
            });
            mEditGoodsIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //编辑商品
                    mOnItemGoodsClickListener.setOnEditGoodsClick((ImageView) v,position);
                }
            });

        }else {
            holder.getView(R.id.item_cart_deal_before_ll).setVisibility(View.VISIBLE);
            holder.getView(R.id.item_cart_deal_edit_rl).setVisibility(View.GONE);
//            mGoodsNameTv = holder.getTextView(R.id.item_cart_item_goodsname);
//            mGoodsPropertyTv = holder.getTextView(R.id.item_cart_item_goods_property);
//            mGoodsPriceTv = holder.getTextView(R.id.item_cart_item_price);
//            mGoodsCountTv = holder.getTextView(R.id.item_cart_item_count);
            holder.getTextView(R.id.item_cart_item_goodsname).setText(cartMergeItemBean.getGoodsDealBean().getGoodsName());
            holder.getTextView(R.id.item_cart_item_goods_property)
                    .setText("颜色："+cartMergeItemBean.getGoodsDealBean().getGoodsColor()+";尺码："+cartMergeItemBean.getGoodsDealBean().getGoodsSize());
            holder.getTextView(R.id.item_cart_item_price).setText("￥"+ ContextUtils.S2places(cartMergeItemBean.getGoodsDealBean().getGoodsPrice()));
            mOriginalpriceTv = holder.getTextView(R.id.item_cart_item_originalprice);
            mOriginalpriceTv.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);//设置中划线并加清晰
            mOriginalpriceTv.setText("￥"+ContextUtils.S2places(cartMergeItemBean.getGoodsDealBean().getGoodsOriginalPrice()));
            holder.getTextView(R.id.item_cart_item_count).setText("x"+cartMergeItemBean.getGoodsDealBean().getGoodsCount());
        }

        holder.itemView.setTag(mDatas.get(position));
    }

    protected OnItemGoodsClickListener mOnItemGoodsClickListener;

    public interface OnItemGoodsClickListener {
        //回调函数 将店铺商品的checkbox的点击变化事件进行回调
        void setOnCheckBoxClick(boolean isChecked, int position);
        //删除商品
        void setOnDeleteGoodsClick(TextView tv,int position);
        //减少商品数量
        void setOnDecreaseGoodsClick(ImageView iv, int position, TextView goodsCountTv);
        //增加商品数量
        void setOnAddGoodsClick(ImageView iv, int position, TextView goodsCountTv);
        //编辑商品属性
        void setOnEditGoodsClick(ImageView tv, int position);
    }
    public void setOnItemGoodsClickListener(OnItemGoodsClickListener checkBoxClickListener) {
        this.mOnItemGoodsClickListener = checkBoxClickListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return gridLayoutManager.getSpanCount();
                }
            });
        }
    }
}
