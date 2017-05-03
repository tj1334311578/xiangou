package com.example.administrator.xiangou.nearby.nearbygoods.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.nearby.apimodel.NearbyGoodsDataBean;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.ContextUtils;
import com.example.administrator.xiangou.tool.GlideImageLoader;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/13.
 */

public class NearbyGoodsAdapterRV extends RVBaseAdapter<NearbyGoodsDataBean.DataBean.CatelistBean> {
    private RecyclerView mItemRv;
    private GlideImageLoader mImageLoader;
    private ItemAdapterRV mItemAdapterRV;
    private ItemIntervalDecoration mItemIntervalDecoration;
    private boolean isFirst = true;

    public NearbyGoodsAdapterRV(Context context, List<NearbyGoodsDataBean.DataBean.CatelistBean> mDatas) {
        super(context, mDatas);
        setLayoutResId(R.layout.nearby_goods_item);
        mImageLoader = new GlideImageLoader();
        mItemIntervalDecoration = new ItemIntervalDecoration(0,8,0,8);
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, NearbyGoodsDataBean.DataBean.CatelistBean catelistBean, int position) {
        ImageView mIcon = holder.getImageView(R.id.icon_child_nearbygoods);
        TextView mText = holder.getTextView(R.id.text_child_nearbygoods);
        Log.e("catelist", "bindData: " + catelistBean.toString());
        if (catelistBean.getImage()!=null) {
            mImageLoader.displayImage(mContext, XianGouApiService.BASEURL+catelistBean.getImage(),mIcon);
        }
        mText.setText(catelistBean.getCate_name());

        mItemRv = holder.getRecyclerView(R.id.child_goods_nearby_rv);
        mItemRv.setLayoutManager(new GridLayoutManager(mItemRv.getContext(),4, GridLayoutManager.VERTICAL,false));
        mItemRv.setPadding(ContextUtils.dp2px(8),ContextUtils.dp2px(8),ContextUtils.dp2px(8),ContextUtils.dp2px(8));
        if (isFirst) {
            isFirst =false;
        }else {
            mItemRv.removeItemDecoration(mItemIntervalDecoration);
        }
        mItemRv.addItemDecoration(mItemIntervalDecoration);
        mItemRv.setNestedScrollingEnabled(false);

        if (catelistBean.getGoodslist()!=null) {
            mItemAdapterRV = new ItemAdapterRV(mContext, R.layout.item_rv_nearbygoods, catelistBean.getGoodslist());
        }
        mItemAdapterRV.setOnItemViewClickListener(new OnItemViewClickListener() {
            @Override
            public void setOnItemViewClick(View view, int position) {
                Toast.makeText(mContext, "mItemRv "+position+((TextView)((LinearLayout)view).getChildAt(1)).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        mItemRv.setAdapter(mItemAdapterRV);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                            return gridLayoutManager.getSpanCount();
                }
            });
        }
    }

}
