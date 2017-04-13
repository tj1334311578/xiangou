package com.example.administrator.xiangou.nearby.nearbygoods.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.nearby.nearbygoods.GoodsBean;
import com.example.administrator.xiangou.tool.CustomImageView;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/13.
 */

public class ClothingAdapterRV extends RVBaseAdapter<GoodsBean> {
    private TextView goodsName;
    private CustomImageView goodsImg;

    public ClothingAdapterRV(Context context, int mLayoutResId, List<GoodsBean> mDatas) {
        super(context, mLayoutResId, mDatas);
    }


    @Override
    protected void bindData(RVBaseViewHolder holder, GoodsBean goodsBean, int position) {
        goodsImg = holder.getCustomView(R.id.item_clothing_img);
        goodsName = holder.getTextView(R.id.item_clothing_text);
        goodsImg.setImageResource(goodsBean.getGoodsImg());
        goodsName.setText(goodsBean.getGoodsName());
    }
}
