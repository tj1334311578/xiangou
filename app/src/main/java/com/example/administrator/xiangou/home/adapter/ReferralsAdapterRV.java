package com.example.administrator.xiangou.home.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.home.model.HomeDataBean;
import com.example.administrator.xiangou.tool.CustomImageView;

import java.util.List;


/**
 * Created by zhouzongyao on 2017/3/8.
 */

public class ReferralsAdapterRV extends RVBaseAdapter<HomeDataBean.DataBean.GoodsPerfectBean> {
    public ReferralsAdapterRV(Context context, int mLayoutResId, List<HomeDataBean.DataBean.GoodsPerfectBean> mDatas) {
        super(context, mLayoutResId, mDatas);
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, HomeDataBean.DataBean.GoodsPerfectBean goodsPerfectBean, int position) {
        CustomImageView mCustomImageView = holder.getCustomView(R.id.civ_item_referrals_recycle);
        if (goodsPerfectBean.getOriginal_img()!=null)
            loadImg(goodsPerfectBean.getOriginal_img(),mCustomImageView);
        TextView mTextView = holder.getTextView(R.id.tv_item_referrals_recycle);
        mTextView.setText(goodsPerfectBean.getName());
    }

}
