package com.example.administrator.xiangou.nearby.nearbygoods.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.nearby.apimodel.NearbyGoodsDataBean;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.CustomImageView;
import com.example.administrator.xiangou.tool.GlideImageLoader;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/13.
 */

public class ItemAdapterRV extends RVBaseAdapter<NearbyGoodsDataBean.DataBean.CatelistBean.GoodslistBean> {
    private TextView goodsName;
    private CustomImageView goodsImg;
    private GlideImageLoader mImageLoader;

    public ItemAdapterRV(Context context, int mLayoutResId, List<NearbyGoodsDataBean.DataBean.CatelistBean.GoodslistBean> mDatas) {
        super(context, mLayoutResId, mDatas);
        mImageLoader = new GlideImageLoader();
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, NearbyGoodsDataBean.DataBean.CatelistBean.GoodslistBean goodsBean, final int position) {
        holder.getView(R.id.item_nearbygoods_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemViewClickListener!=null)
                mOnItemViewClickListener.setOnItemViewClick(v,position);
            }
        });
        goodsImg = holder.getCustomView(R.id.item_rv_nearbygoods_iv);
        goodsName = holder.getTextView(R.id.item_rv_nearbygoods_tv);

        goodsName.setText(goodsBean.getName());
        mImageLoader.displayImage(mContext, XianGouApiService.BASEURL+goodsBean.getOriginal_img(),goodsImg);
    }
}
