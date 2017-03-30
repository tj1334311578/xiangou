package com.example.administrator.xiangou.home.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.BaseViewHolder;
import com.example.administrator.xiangou.base.SimpleAdapter;
import com.example.administrator.xiangou.home.model.ChildHomeBean;
import com.example.administrator.xiangou.tool.CustomImageView;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/7.
 */

public class BoutiqueAdapter extends SimpleAdapter<ChildHomeBean> {
    public BoutiqueAdapter(Context context, int mLayoutResId, List<ChildHomeBean> mDatas) {
        super(context, mLayoutResId, mDatas);
    }

    @Override
    protected void bindData(BaseViewHolder holder, ChildHomeBean childHomeBean) {
            CustomImageView mCustomImageView = holder.getCustomView(R.id.civ_item_boutique_recycle);
            mCustomImageView.setImageResource(childHomeBean.getImgSrc());
            TextView mTextView = holder.getTextView(R.id.tv_item_boutique_recycle);
            mTextView.setText(childHomeBean.getTitle());
    }
}
