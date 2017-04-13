package com.example.administrator.xiangou.cart.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.cart.model.GoodsDealBean;
import com.example.administrator.xiangou.tool.ContextUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouzongyao on 2017/3/7.
 */

public class AdapterItemGoodsDealRvRV extends RVBaseAdapter<GoodsDealBean> implements View.OnClickListener {

    private CheckBox mItemCb;
    private int position;
//    private boolean toCheckItemAll;
//    private boolean isEditAll;
    private float goodsAllPrice;
    private Map<Integer,Boolean> mMapGoodsItemChecked = new HashMap<>();
    public AdapterItemGoodsDealRvRV(Context context, List<GoodsDealBean> mDatas, boolean toCheckItemAll) {
        super(context, R.layout.item_cart_item_goods_rv, mDatas);
//        this.toCheckItemAll = toCheckItemAll;
        initMap();
    }

    private void initMap() {
        for (int i = 0; i < mDatas.size(); i++) {
            mMapGoodsItemChecked.put(i,false);
        }
    }

    public Map<Integer, Boolean> getMapGoodsItemChecked() {
        return mMapGoodsItemChecked;
    }

//    public void setToCheckItemAll(boolean toCheckItemAll) {
//        this.toCheckItemAll = toCheckItemAll;
//    }

    @Override
    protected void bindData(RVBaseViewHolder holder, GoodsDealBean goodsDealBean, final int position) {
        this.position = position;
        holder.getItemView().setTag(position);
        holder.getItemView().setOnClickListener(this);
        goodsAllPrice = goodsDealBean.getGoodsPrice()*goodsDealBean.getGoodsCount();
        mItemCb = holder.getCheckBox(R.id.cart_item_checkBox);
//        mItemCb.setTag(position);
//        mItemCb.setOnCheckedChangeListener(null);
//        mItemCb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mOnItemClickListener!=null){
//                    mOnItemClickListener.onMineItemClick(v, position);
//                }
//            }
//        });

        mItemCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mMapGoodsItemChecked.put(position,isChecked);
            }
        });
        // 设置CheckBox的状态
        if (mMapGoodsItemChecked.get(position) ==null){
            mMapGoodsItemChecked.put(position,false);
        }
        Log.e("mItemCb", "bindData: " +position+"="+mMapGoodsItemChecked.get(position) );
        mItemCb.setChecked(mMapGoodsItemChecked.get(position));

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
    //点击选中CheckBox
    public void setCheckedItem(int pos){
        //对当前状态取反
        if (mMapGoodsItemChecked.get(pos))
            mMapGoodsItemChecked.put(pos,false);
        else
            mMapGoodsItemChecked.put(pos,true);
        notifyItemChanged(pos);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener!=null){
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onMineItemClick(v, (Integer) v.getTag());
        }
//        switch (v.getId()) {
//            case R.id.cart_item_checkBox:
//
//                break;
//        }
    }

}
