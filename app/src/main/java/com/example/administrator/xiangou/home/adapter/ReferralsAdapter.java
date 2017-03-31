package com.example.administrator.xiangou.home.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.BaseViewHolder;
import com.example.administrator.xiangou.base.SimpleAdapter;
import com.example.administrator.xiangou.home.model.ChildHomeBean;
import com.example.administrator.xiangou.tool.CustomImageView;

import java.util.List;


/**
 * Created by zhouzongyao on 2017/3/8.
 */

public class ReferralsAdapter extends SimpleAdapter<ChildHomeBean> {
    public ReferralsAdapter(Context context, int mLayoutResId, List<ChildHomeBean> mDatas) {
        super(context, mLayoutResId, mDatas);
    }

    @Override
    protected void bindData(BaseViewHolder holder, ChildHomeBean childHomeBean) {
        Log.e("childHomeBean", "ReferralsAdapter: "+childHomeBean.toString() );
            CustomImageView mCustomImageView = holder.getCustomView(R.id.civ_item_referrals_recycle);
            mCustomImageView.setImageResource(childHomeBean.getImgSrc());
            TextView mTextView = holder.getTextView(R.id.tv_item_referrals_recycle);
            mTextView.setText(childHomeBean.getTitle());
    }
}