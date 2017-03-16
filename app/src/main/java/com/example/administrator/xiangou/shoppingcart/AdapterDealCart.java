package com.example.administrator.xiangou.shoppingcart;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.BaseViewHolder;
import com.example.administrator.xiangou.base.SimpleAdapter;
import com.example.administrator.xiangou.tool.ContextUtils;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/7.
 */

public class AdapterDealCart extends SimpleAdapter<DealBean> implements View.OnClickListener{

    private CheckBox mAllCb;
    private TextView mAllEditTv;
    private RecyclerView mGoodsRv;
    private boolean isSelectedAll,isEditAll;
    private float goodsAllPrice;
    public AdapterDealCart(Context context, List<DealBean> mDatas) {
        super(context, R.layout.item_cart_dealrv, mDatas);
    }

    public void setGoodsAllPrice(float goodsAllPrice) {
        this.goodsAllPrice = goodsAllPrice;
    }

    @Override
    protected void bindData(BaseViewHolder holder, DealBean dealBean) {
        holder.getCheckBox(R.id.cart_all_checkBox).setOnClickListener(this);
        holder.getTextView(R.id.cart_storename_tv).setText(dealBean.getStoreName());
        holder.getTextView(R.id.cart_ticket_tv).setOnClickListener(this);
        holder.getTextView(R.id.cart_deal_edit_tv).setOnClickListener(this);

        holder.getTextView(R.id.cart_item_free_text)
                .setText("再买"+ ContextUtils.S2places(dealBean.getGoodsFreePrice()-goodsAllPrice)+"元，免运费");
        holder.getTextView(R.id.cart_item_free_addon).setOnClickListener(this);

        mGoodsRv = holder.getRecyclerView(R.id.cart_item_goods_rv);
        mGoodsRv.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        mGoodsRv.addItemDecoration(new ItemIntervalDecoration(0,0,8));
        List<GoodsDealBean> list = new ArrayList<>();
        list.add(new GoodsDealBean(R.mipmap.cart_goods_dfimg,"【实拍原版】春季韩版学院风九分裤毛边高腰宽松直筒阔腿裤","浅蓝",
                "26","S",2,59.50f,85.00f,7f));
        list.add(new GoodsDealBean(R.mipmap.cart_recommend_dfimg,"【实拍原版】春季韩版套装条纹宽松连衣裙","黑白条纹",
                "28","m",1,51.50f,68.00f,6f));
        AdapterItemGoodsDealRv adapterItemGoodsDealRv = new AdapterItemGoodsDealRv(mContext,list);
        mGoodsRv.setAdapter(adapterItemGoodsDealRv);
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
            case R.id.cart_all_checkBox:
                if (v.isSelected()){
                    isSelectedAll = true;
                }else {
                    isSelectedAll =false;
                }
                break;
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
