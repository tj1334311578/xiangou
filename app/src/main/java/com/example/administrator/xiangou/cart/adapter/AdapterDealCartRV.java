package com.example.administrator.xiangou.cart.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.cart.model.CartAllCbBean;
import com.example.administrator.xiangou.cart.model.DealBean;
import com.example.administrator.xiangou.cart.model.GoodsDealBean;
import com.example.administrator.xiangou.tool.ContextUtils;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/7.
 */

public class AdapterDealCartRV extends RVBaseAdapter<DealBean> implements View.OnClickListener{

    private CheckBox mStotrCb;
    private TextView mAllEditTv,mFreePriceTv;
    private RecyclerView mGoodsRv;
    private boolean isCheckedAll,isEditAll;
    private float goodsAllPrice;
    private AdapterItemGoodsDealRvRV mAdapterItemGoodsDealRv;
    private List<GoodsDealBean> mList;
    private List<CartAllCbBean> mAllCbBeanList;

    public AdapterDealCartRV(Context context, List<DealBean> mDatas ,List<CartAllCbBean> mAllCbBeanList) {
        super(context, R.layout.item_cart_dealrv, mDatas);
        this.mAllCbBeanList = mAllCbBeanList;
//        mList = new ArrayList<>();
//        mList.add(new GoodsDealBean(R.mipmap.cart_goods_dfimg,"【实拍原版】春季韩版学院风九分裤毛边高腰宽松直筒阔腿裤","浅蓝",
//                "26","S",2,59.50f,85.00f,7f));
//        mList.add(new GoodsDealBean(R.mipmap.cart_recommend_dfimg,"【实拍原版】春季韩版套装条纹宽松连衣裙","黑白条纹",
//                "28","m",1,51.50f,68.00f,6f));
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, DealBean dealBean, final int pos) {
        holder.setIsRecyclable(false);
        goodsAllPrice = 0;

        mStotrCb = holder.getCheckBox(R.id.cart_all_checkBox);
        //设置CheckBox状态
        mStotrCb.setChecked(mAllCbBeanList.get(pos).ischeck());
        mStotrCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //将店铺的checkbox的点击变化事件进行回调
                if (mOnStroeCbClickListener!=null){
                    mOnStroeCbClickListener.setOnStoreCbClick(isChecked,pos);
                }
            }
        });

        holder.getTextView(R.id.cart_storename_tv).setText(dealBean.getStoreName());
        holder.getTextView(R.id.cart_ticket_tv).setOnClickListener(this);
        holder.getTextView(R.id.cart_deal_edit_tv).setOnClickListener(this);

        mGoodsRv = holder.getRecyclerView(R.id.cart_item_goods_rv);
        mGoodsRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        ((SimpleItemAnimator)mGoodsRv.getItemAnimator()).setSupportsChangeAnimations(false);

        mList = dealBean.getList();
        mAdapterItemGoodsDealRv = new AdapterItemGoodsDealRvRV(mContext, mList,mAllCbBeanList.get(pos).getList());
        mAdapterItemGoodsDealRv.setOnCheckBoxClickListener(new AdapterItemGoodsDealRvRV.OnCheckBoxClickListener() {
            @Override
            public void setOnCheckBoxClick(boolean isChecked, int position) {
                //将店铺商品的checkbox的点击变化事件进行回调
                if (mOnStroeCbClickListener!=null){
                    mOnStroeCbClickListener.setOnItemCbCheckListener(isChecked,pos,position);
                }
            }
        });
        mGoodsRv.setAdapter(mAdapterItemGoodsDealRv);


        mFreePriceTv = holder.getTextView(R.id.cart_item_free_text);
        if (mDatas.get(pos).getGoodsFreePrice()>goodsAllPrice){
            mFreePriceTv.setText("再买"+ ContextUtils.S2places(mDatas.get(pos).getGoodsFreePrice()-goodsAllPrice)+"元，免运费");
        }else {
            mFreePriceTv.setText("免邮费！");
        }
        Log.e("goodsPrice", "toData: goodsFreePrice=" +mDatas.get(pos).getGoodsFreePrice()+"---goodsAllPrice="
                +goodsAllPrice+"===="+ContextUtils.S2places(mDatas.get(pos).getGoodsFreePrice()-goodsAllPrice));

        holder.getTextView(R.id.cart_item_free_addon).setOnClickListener(this);
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
            case R.id.cart_deal_edit_tv:
                if (!isEditAll){
                    mAllEditTv = (TextView) v;
                    mAllEditTv.setText("完成");
                    isEditAll = true;
                }else {
                    mAllEditTv = (TextView) v;
                    mAllEditTv.setText("编辑");
                    isEditAll =false;
                }
                break;
        }
    }

    protected OnStroeCbClickListener mOnStroeCbClickListener;
    public interface OnStroeCbClickListener {
        //回调函数 将店铺的checkbox的点击变化事件进行回调
        void setOnStoreCbClick(boolean isChecked, int position);
        //回调函数 将店铺商品的checkbox的点击变化事件进行回调
        void setOnItemCbCheckListener(boolean isItemChecked, int parentposition, int chaildposition);
    }
    public void setOnStroeCbClickListener(OnStroeCbClickListener stroeCbClickListener) {
        this.mOnStroeCbClickListener = stroeCbClickListener;
    }

}
