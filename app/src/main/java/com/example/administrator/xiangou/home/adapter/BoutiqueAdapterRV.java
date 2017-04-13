package com.example.administrator.xiangou.home.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.home.model.ChildHomeBean;
import com.example.administrator.xiangou.tool.CustomImageView;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/7.
 */

public class BoutiqueAdapterRV extends RVBaseAdapter<ChildHomeBean> {
    public BoutiqueAdapterRV(Context context, int mLayoutResId, List<ChildHomeBean> mDatas) {
        super(context, mLayoutResId, mDatas);
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, ChildHomeBean childHomeBean, int position) {
            CustomImageView mCustomImageView = holder.getCustomView(R.id.civ_item_boutique_recycle);
            mCustomImageView.setImageResource(childHomeBean.getImgSrc());
            TextView mTextView = holder.getTextView(R.id.tv_item_boutique_recycle);
            mTextView.setText(childHomeBean.getTitle());
    }
}
