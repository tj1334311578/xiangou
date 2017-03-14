package com.example.administrator.xiangou.nearby.frag;

import android.content.Context;
import android.widget.TextView;


import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.BaseViewHolder;
import com.example.administrator.xiangou.base.SimpleAdapter;
import com.example.administrator.xiangou.tool.CustomImageView;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/13.
 */

public class ClothingAdapter extends SimpleAdapter<GoodsBean> {
    private TextView goodsName;
    private CustomImageView goodsImg;

    public ClothingAdapter(Context context, int mLayoutResId, List<GoodsBean> mDatas) {
        super(context, mLayoutResId, mDatas);
    }


    @Override
    protected void bindData(BaseViewHolder holder, GoodsBean goodsBean) {
        goodsImg = holder.getCustomView(R.id.item_clothing_img);
        goodsName = holder.getTextView(R.id.item_clothing_text);
        goodsImg.setImageResource(goodsBean.getGoodsImg());
        goodsName.setText(goodsBean.getGoodsName());
    }
}
