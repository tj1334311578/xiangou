package com.example.administrator.xiangou.nearby.nearbygoods.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.nearby.apimodel.NearbyGoodsDataBean;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.GlideImageLoader;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/13.
 */

public class NearbyGoodsAdapterRV extends RVBaseAdapter<NearbyGoodsDataBean.DataBean.CatelistBean> {
    private static final int BANNER_TYPE = 1;
    private static final int NORMAL_TYPE = 2;
    private RecyclerView mItemRv;
    private GlideImageLoader mImageLoader;
    private ItemAdapterRV mItemAdapterRV;
    private ItemIntervalDecoration mItemIntervalDecoration;
    private boolean isFirst = true;

    private NearbyGoodsItemCall mNearbyGoodsItemCall;
    public interface NearbyGoodsItemCall{
        void setOnNearbyGoodsItemCall(View view, int parentposition, int childposition);
    }
    public void setOnNearbyGoodsItemClickListener(NearbyGoodsItemCall mNearbyGoodsItemCall){
        this.mNearbyGoodsItemCall = mNearbyGoodsItemCall;
    }

    public NearbyGoodsAdapterRV(Context context, List<NearbyGoodsDataBean.DataBean.CatelistBean> mDatas) {
        super(context, mDatas);
        mImageLoader = new GlideImageLoader();
        mItemIntervalDecoration = new ItemIntervalDecoration(0,8,0,8);
    }

    @Override
    public int getItemCount() {
        return mDatas.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return BANNER_TYPE;
            default:
                return NORMAL_TYPE;
        }
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER_TYPE:
                setLayoutResId(R.layout.banner_nearbygoods_item);
                break;
            case NORMAL_TYPE:
                setLayoutResId(R.layout.nearby_goods_item);
                break;
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        switch (position){
            case 0:
                bindBannerData(holder);
                break;
            default:
                bindNormalData(holder,position-1);
                break;
        }
    }

    private void bindBannerData(RVBaseViewHolder holder) {
        Banner mBanner = holder.getBanner(R.id.advs_nearbygoods_banner);
//        mOnItemViewHolderListener.bindItemViewHolder(mBanner,holder,0);
        mOnItemViewClickListener.setOnItemViewClick(mBanner,0);
    }

    private void bindNormalData(RVBaseViewHolder holder, final int position) {
        NearbyGoodsDataBean.DataBean.CatelistBean catelistBean = getItem(position);
        ImageView mIcon = holder.getImageView(R.id.icon_child_nearbygoods);
        TextView mText = holder.getTextView(R.id.text_child_nearbygoods);
        Log.e("catelist", "bindData: " + catelistBean.toString());

        if (catelistBean.getImage()!=null) {
            mImageLoader.displayImage(mContext, XianGouApiService.IMGBASEURL +catelistBean.getImage(),mIcon);
        }
        mText.setText(catelistBean.getCate_name());

        mItemRv = holder.getRecyclerView(R.id.child_goods_nearby_rv);
        mItemRv.setLayoutManager(new GridLayoutManager(mItemRv.getContext(),4, GridLayoutManager.VERTICAL,false));
//        mItemRv.setPadding(ContextUtils.dp2px(8),ContextUtils.dp2px(8),ContextUtils.dp2px(8),ContextUtils.dp2px(8));
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
            public void setOnItemViewClick(View view, int pos) {
                mNearbyGoodsItemCall.setOnNearbyGoodsItemCall(view,position,pos);
            }
        });
        mItemRv.setAdapter(mItemAdapterRV);
    }


    @Override
    protected void bindData(RVBaseViewHolder holder, NearbyGoodsDataBean.DataBean.CatelistBean catelistBean, final int position) {}

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
