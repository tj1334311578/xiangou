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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.BaseAdapter;
import com.example.administrator.xiangou.base.BaseViewHolder;
import com.example.administrator.xiangou.cart.model.DealBean;
import com.example.administrator.xiangou.cart.model.GoodsDealBean;
import com.example.administrator.xiangou.tool.ContextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouzongyao on 2017/3/7.
 */

public class AdapterDealCart extends BaseAdapter<DealBean> implements View.OnClickListener{

    private CheckBox mAllCb;
    private TextView mAllEditTv,mFreePriceTv;
    private RecyclerView mGoodsRv;
    private boolean isCheckedAll,isEditAll;
    private float goodsAllPrice;
    private Map<Integer,Float> mapFreePrice;
    private AdapterItemGoodsDealRv mAdapterItemGoodsDealRv;
    private List<GoodsDealBean> mList;
    private int pos;
    private Map<Integer, Boolean> mItemCheckedMap,mStoreCheckedMap;

    public AdapterDealCart(Context context, List<DealBean> mDatas) {
        super(context, R.layout.item_cart_dealrv, mDatas);
        mapFreePrice = new HashMap<>();
        mList = new ArrayList<>();
        mList.add(new GoodsDealBean(R.mipmap.cart_goods_dfimg,"【实拍原版】春季韩版学院风九分裤毛边高腰宽松直筒阔腿裤","浅蓝",
                "26","S",2,59.50f,85.00f,7f));
        mList.add(new GoodsDealBean(R.mipmap.cart_recommend_dfimg,"【实拍原版】春季韩版套装条纹宽松连衣裙","黑白条纹",
                "28","m",1,51.50f,68.00f,6f));
    }

//    public void setGoodsAllPrice(float goodsAllPrice) {
//        this.goodsAllPrice = goodsAllPrice;
//    }

    @Override
    protected void bindData(BaseViewHolder holder, DealBean dealBean, final int pos) {
        this.pos= pos;
        holder.setIsRecyclable(false);
        goodsAllPrice = 0;
        if (mapFreePrice.get(pos)==null)
            mapFreePrice.put(pos,mDatas.get(pos).getGoodsFreePrice());

        mAllCb = holder.getCheckBox(R.id.cart_all_checkBox);
        //在初始化CheckBox状态和设置状态变化监听事件之前，先把状态变化监听事件设置为null
        mAllCb.setOnCheckedChangeListener(null);
        //设置CheckBox状态
        mAllCb.setChecked(isCheckedAll);

        mAllCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("mAllCb", "onCheckedChanged: " +pos+" : "+isChecked);

                Log.e("Rv-view ", "all--tag: " +mGoodsRv.getTag());
                isCheckedAll = isChecked;
                mItemCheckedMap = mAdapterItemGoodsDealRv.getMapGoodsItemChecked();
                if (isChecked){
                    for (int i = 0; i < mItemCheckedMap.size(); i++) {
                        mItemCheckedMap.put(i,true);
                    }
                }else {
                    for (int i = 0; i < mItemCheckedMap.size(); i++) {
                        mItemCheckedMap.put(i,false);
                    }
                }
                mAdapterItemGoodsDealRv.notifyDataSetChanged();//更新里面的adapter
//                notifyItemChanged(pos);
            }
        });

        holder.getTextView(R.id.cart_storename_tv).setText(dealBean.getStoreName());
        holder.getTextView(R.id.cart_ticket_tv).setOnClickListener(this);
        holder.getTextView(R.id.cart_deal_edit_tv).setOnClickListener(this);

        mGoodsRv = holder.getRecyclerView(R.id.cart_item_goods_rv);
        mGoodsRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        ((SimpleItemAnimator)mGoodsRv.getItemAnimator()).setSupportsChangeAnimations(false);

        mGoodsRv.setTag(pos);

        mAdapterItemGoodsDealRv = new AdapterItemGoodsDealRv(mContext, mList,isCheckedAll);
        mAdapterItemGoodsDealRv.setOnMineItemClickListener(new OnMineItemClickListener() {
            @Override
            public void onMineItemClick(View view, int position) {

                final CheckBox cb = (CheckBox) ((LinearLayout)((LinearLayout)view).getChildAt(0)).getChildAt(0);
//                final CheckBox cb = (CheckBox) view;
                Log.e("mitemCb", "item: " +position+" : "+cb.isChecked());
                Log.e("Rv-view ", "tag: " +((RecyclerView)view.getParent()).getTag());
                mAdapterItemGoodsDealRv.setCheckedItem(position);
//                mOnItemClickListener.onMineItemClick(mGoodsRv,pos);
            }

        });
        mGoodsRv.setAdapter(mAdapterItemGoodsDealRv);


        for (int i = 0; i < mList.size(); i++) {
            if (mAdapterItemGoodsDealRv.getMapGoodsItemChecked().get(i)){
                goodsAllPrice += mList.get(i).getGoodsPrice();
            }
        }
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

}
