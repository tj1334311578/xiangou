package com.example.administrator.xiangou.shoppingcart;

import android.content.Context;
import android.util.Log;


import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.BaseViewHolder;
import com.example.administrator.xiangou.base.SimpleAdapter;
import com.example.administrator.xiangou.home.ChildHomeBean;
import com.example.administrator.xiangou.tool.CustomImageView;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/7.
 */

public class CartAdapter extends SimpleAdapter<ChildHomeBean> {
    public CartAdapter(Context context, List<ChildHomeBean> mDatas) {
        super(context, R.layout.item_recycle_cart, mDatas);
    }


    @Override
    protected void bindData(BaseViewHolder holder, ChildHomeBean childHomeBean) {
            CustomImageView customImageView = holder.getCustomView(R.id.civ_cart_recycle);
            customImageView.setType(CustomImageView.TYPE_ROUND);
            customImageView.setImageResource(childHomeBean.getImgSrc());
            holder.getTextView(R.id.tv_cart_recycle).setText(childHomeBean.getTitle());
    }
}
