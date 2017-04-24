package com.example.administrator.xiangou.cart.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.cart.model.CartMergeBean;
import com.example.administrator.xiangou.cart.model.StoreDealBean;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/7.
 */

public class AdapterDealCartRV extends RVBaseAdapter<CartMergeBean> implements View.OnClickListener{

    private CheckBox mStotrCb;
    private TextView mAllEditTv,mStoreName;
    private RecyclerView mGoodsRv;
    private boolean isFirst=true;
    private AdapterItemGoodsDealRV mAdapterItemGoodsDealRv;
    private ItemIntervalDecoration mDecoration;

    public AdapterDealCartRV(Context context, List<CartMergeBean> mDatas) {
        super(context, R.layout.item_cart_dealrv, mDatas);
        mDecoration = new ItemIntervalDecoration(0, 5, 0, 0);
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, final CartMergeBean cartMergeBean, final int pos) {

        StoreDealBean storeDealBean = cartMergeBean.getStoreDealBean();
//        holder.setIsRecyclable(false);//不再复用holder

        //设置CheckBox状态
        mStotrCb = holder.getCheckBox(R.id.cart_all_checkBox);
        mStotrCb.setTag(mDatas.get(pos));

        mStotrCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //将店铺的checkbox的点击变化事件进行回调
                if (mOnStroeClickListener !=null){
                    mOnStroeClickListener.setOnStoreCbClick(isChecked,pos);
                }
            }
        });
        if (holder.getCheckBox(R.id.cart_all_checkBox).getTag() == mDatas.get(pos)) {
            mStotrCb = holder.getCheckBox(R.id.cart_all_checkBox);
        }
        mStotrCb.setChecked(cartMergeBean.getStoreStatusBean().ischeck());
        mStoreName = holder.getTextView(R.id.cart_storename_tv);
        mStoreName.setText(storeDealBean.getStoreName());
        holder.getTextView(R.id.cart_ticket_tv).setOnClickListener(this);
        mAllEditTv = holder.getTextView(R.id.cart_deal_edit_tv);
        mAllEditTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnStroeClickListener.setOnEditStoreGoods((TextView) v,pos);
            }
        });

        //使用itemview的context
        mGoodsRv = holder.getRecyclerView(R.id.cart_item_goods_rv);
        mGoodsRv.setTag(mDatas.get(pos));
        mGoodsRv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.VERTICAL, false));
        if (isFirst) {
            isFirst=false;
        }else {
            mGoodsRv.removeItemDecoration(mDecoration);
        }
        mGoodsRv.addItemDecoration(mDecoration);
        mGoodsRv.setHasFixedSize(true);
        mGoodsRv.setNestedScrollingEnabled(false);
        ((SimpleItemAnimator)mGoodsRv.getItemAnimator()).setSupportsChangeAnimations(false);

        mAdapterItemGoodsDealRv = new AdapterItemGoodsDealRV(holder.itemView.getContext(), mDatas.get(pos).getMergeItemBeanList());

        if (holder.getRecyclerView(R.id.cart_item_goods_rv).getTag() == mDatas.get(pos)){
            mGoodsRv = holder.getRecyclerView(R.id.cart_item_goods_rv);
        }
        mGoodsRv.setAdapter(mAdapterItemGoodsDealRv);
        mAdapterItemGoodsDealRv.setOnItemGoodsClickListener(new AdapterItemGoodsDealRV.OnItemGoodsClickListener() {
            @Override
            public void setOnCheckBoxClick(boolean isChecked, final int position) {
                //将店铺商品的checkbox的点击变化事件进行回调
                if (mOnStroeClickListener !=null){
                    mOnStroeClickListener.setOnItemCbCheckListener(isChecked,pos,position);
                    UpdateRecyclerView(position);
                }
            }

            @Override
            public void setOnDeleteGoodsClick(TextView tv, int position) {
                if (mOnStroeClickListener !=null) {
                    mOnStroeClickListener.setOnDeleteGoodsClick(tv, pos, position);
                    RemoveRecyclerViewItem(position);
                }
            }

            @Override
            public void setOnDecreaseGoodsClick(ImageView iv, int position, TextView goodsCountTv) {
                if (mOnStroeClickListener !=null) {
                    mOnStroeClickListener.setOnDecreaseGoodsClick(iv, pos, position, goodsCountTv);
                    UpdateRecyclerView(position);
                }
            }

            @Override
            public void setOnAddGoodsClick(ImageView iv, int position, TextView goodsCountTv) {
                if (mOnStroeClickListener !=null) {
                    mOnStroeClickListener.setOnAddGoodsClick(iv, pos, position, goodsCountTv);
                    UpdateRecyclerView(position);
                }
            }

            @Override
            public void setOnEditGoodsClick(ImageView iv, int position) {
                if (mOnStroeClickListener !=null) {
                    mOnStroeClickListener.setOnEditGoodsClick(iv, pos, position);
                    UpdateRecyclerView(position);
                }
            }

        });

        holder.itemView.setTag(mDatas.get(pos));
    }

    private void UpdateRecyclerView(final int position) {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                mAdapterItemGoodsDealRv.notifyItemChanged(position);//更新
            }
        };
        handler.post(r);
    }
    private void RemoveRecyclerViewItem(final int position) {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                mAdapterItemGoodsDealRv.notifyItemRangeRemoved(position,1);//更新
            }
        };
        handler.post(r);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cart_ticket_tv:
                Toast.makeText(mContext, "领券了，领券了", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    protected OnStroeClickListener mOnStroeClickListener;
    public interface OnStroeClickListener {
        //回调函数 将店铺的checkbox的点击变化事件进行回调
        void setOnStoreCbClick(boolean isChecked, int position);
        //回调函数 将店铺商品的checkbox的点击变化事件进行回调
        void setOnItemCbCheckListener(boolean isItemChecked, int parentposition, int chaildposition);
        //回调函数 将店铺商品的编辑的点击事件进行回调
        void setOnEditStoreGoods(TextView v, int position);
        //删除商品
        void setOnDeleteGoodsClick(TextView tv, int parentposition, int chaildposition);
        //减少商品数量
        void setOnDecreaseGoodsClick(ImageView iv, int parentposition, int chaildposition, TextView goodsCountTv);
        //增加商品数量
        void setOnAddGoodsClick(ImageView iv, int parentposition, int chaildposition, TextView goodsCountTv);
        //编辑商品属性
        void setOnEditGoodsClick(ImageView view, int parentposition, int chaildposition);
    }
    public void setOnStroeClickListener(OnStroeClickListener stroeCbClickListener) {
        this.mOnStroeClickListener = stroeCbClickListener;
    }

}
