package com.example.administrator.xiangou.nearby.nearbygoods.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.BaseViewHolder;
import com.example.administrator.xiangou.base.SimpleAdapter;
import com.example.administrator.xiangou.nearby.nearbygoods.GoodsBean;
import com.example.administrator.xiangou.tool.CustomImageView;

import java.util.List;


/**
 * Created by zhouzongyao on 2017/3/13.
 */

public class MakeupAdapter extends SimpleAdapter<GoodsBean> {
    private TextView goodsName;
    private CustomImageView goodsImg;

    public MakeupAdapter(Context context, int mLayoutResId, List<GoodsBean> mDatas) {
        super(context, mLayoutResId, mDatas);
    }


    @Override
    protected void bindData(BaseViewHolder holder, GoodsBean goodsBean) {
        goodsImg = holder.getCustomView(R.id.item_makeup_img);
        goodsName = holder.getTextView(R.id.item_makeup_text);
        goodsImg.setImageResource(goodsBean.getGoodsImg());
        goodsName.setText(goodsBean.getGoodsName());
    }
}
