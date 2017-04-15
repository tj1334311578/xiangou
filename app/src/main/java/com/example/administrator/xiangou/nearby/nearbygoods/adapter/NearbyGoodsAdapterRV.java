package com.example.administrator.xiangou.nearby.nearbygoods.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.nearby.ChildType;
import com.example.administrator.xiangou.nearby.nearbygoods.GoodsBean;
import com.example.administrator.xiangou.tool.ContextUtils;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/13.
 */

public class NearbyGoodsAdapterRV extends RVBaseAdapter<ChildType> implements RVBaseAdapter.OnItemViewClickListener {
//    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_CLOTHING = 1;
    public static final int TYPE_MAKEUP = 2;
    public static final int TYPE_HOUSEHOLDS = 3;

    private RecyclerView mClothingRv,mMakeupRv,mHouseholdsRv;

    public NearbyGoodsAdapterRV(Context context, List<ChildType> mDatas) {
        super(context, mDatas);
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_CLOTHING:
                setLayoutResId(R.layout.nearby_goods_clothings);
                break;
            case TYPE_MAKEUP:
                setLayoutResId(R.layout.nearby_goods_makeup);
                break;
            case TYPE_HOUSEHOLDS:
                setLayoutResId(R.layout.nearby_goods_households);
                break;
        }
        return super.onCreateViewHolder(parent, viewType);
    }
    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return TYPE_CLOTHING;
            case 1:
                return TYPE_MAKEUP;
            case 2:
                return TYPE_HOUSEHOLDS;
            default:
                return 0;
        }
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
                    switch ( getItemViewType(position) ) {
                        case TYPE_CLOTHING:
                        case TYPE_MAKEUP:
                        case TYPE_HOUSEHOLDS:
                            return gridLayoutManager.getSpanCount();
                        default:
                            return 1;
                    }

                }
            });
        }
    }


    @Override
    protected void bindData(RVBaseViewHolder holder, ChildType childType, int position) {
        switch (childType.getChildType()){
            case TYPE_CLOTHING:
                bindClothing(holder);
                break;
            case TYPE_MAKEUP:
                bindMakeup(holder);
                break;
            case TYPE_HOUSEHOLDS:
                bindHouseholds(holder);
                break;
        }
    }

    public void bindClothing(RVBaseViewHolder holder) {
        ImageView mAdvsIv = holder.getImageView(R.id.advs_goods_nearby);
        ImageView mIcon = holder.getImageView(R.id.icon_child_nearbygoods);
        TextView mText = holder.getTextView(R.id.text_child_nearbygoods);
        TextView mDescribe = holder.getTextView(R.id.describe_child_nearby);
        mAdvsIv.setImageResource(R.mipmap.nearby_goods_advs);
        mAdvsIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/4/15
                Toast.makeText(mContext, "瞧一瞧，附近商品广告图片", Toast.LENGTH_SHORT).show();
            }
        });
        mIcon.setImageResource(R.mipmap.nearby_goods_clothing_icon);
        mText.setText("服装类");
        mText.setClickable(false);
        mDescribe.setText("当前主流潮服");
        mDescribe.setClickable(false);
        holder.getImageView(R.id.img_child_nearbygoods).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "瞧一瞧，看一看了啊-----服装类", Toast.LENGTH_SHORT).show();
            }
        });

        mClothingRv = holder.getRecyclerView(R.id.clothing_goods_nearby_rv);
        mClothingRv.setLayoutManager(new GridLayoutManager(mClothingRv.getContext(),4, GridLayoutManager.VERTICAL,false));
        mClothingRv.setPadding(ContextUtils.dp2px(8),ContextUtils.dp2px(8),ContextUtils.dp2px(8),ContextUtils.dp2px(8));
        mClothingRv.addItemDecoration(new ItemIntervalDecoration(8,0,0));
        List<GoodsBean> list = new ArrayList<>();
        //test data ,here,you can update
        for (int i=0;i<8;i++) {
            list.add(new GoodsBean(R.mipmap.nearby_goods_clothing_dfimg,"休闲女装"));
        }
        ClothingAdapterRV goodsAdapter = new ClothingAdapterRV(mContext,R.layout.child_clothing_nearbygoods,list);
        goodsAdapter.setOnItemViewClickListener(this);
        mClothingRv.setAdapter(goodsAdapter);
    }
    public void bindMakeup(RVBaseViewHolder holder) {
        ImageView mIcon = holder.getImageView(R.id.icon_child_nearbygoods);
        TextView mText = holder.getTextView(R.id.text_child_nearbygoods);
        TextView mDescribe = holder.getTextView(R.id.describe_child_nearby);
        mIcon.setImageResource(R.mipmap.nearby_goods_makeup_icon);
        mText.setText("化妆类");
        mDescribe.setText("必备神器");
        holder.getImageView(R.id.img_child_nearbygoods).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "瞧一瞧，看一看了啊-----化妆类", Toast.LENGTH_SHORT).show();
            }
        });

        mMakeupRv = holder.getRecyclerView(R.id.makeup_goods_nearby_rv);
        mMakeupRv.setLayoutManager(new GridLayoutManager(mMakeupRv.getContext(),4, GridLayoutManager.VERTICAL,false));
        mMakeupRv.setPadding(ContextUtils.dp2px(8),ContextUtils.dp2px(8),ContextUtils.dp2px(8),ContextUtils.dp2px(8));
        mMakeupRv.addItemDecoration(new ItemIntervalDecoration(8,0,0));
        List<GoodsBean> list = new ArrayList<>();
        //test data ,here,you can update
        for (int i=0;i<4;i++) {
            list.add(new GoodsBean(R.mipmap.nearby_goods_makeup_dfimg,"必备神器"));
        }
        MakeupAdapterRV makeupAdapter = new MakeupAdapterRV(mContext,R.layout.child_makeup_nearbygoods,list);
        makeupAdapter.setOnItemViewClickListener(this);
        mMakeupRv.setAdapter(makeupAdapter);
    }
    public void bindHouseholds(RVBaseViewHolder holder) {
        ImageView mIcon = holder.getImageView(R.id.icon_child_nearbygoods);
        TextView mText = holder.getTextView(R.id.text_child_nearbygoods);
        TextView mDescribe = holder.getTextView(R.id.describe_child_nearby);
        mIcon.setImageResource(R.mipmap.nearby_goods_households_icon);
        mText.setText("家居家电类");
        mDescribe.setText("生活好帮手");
        holder.getImageView(R.id.img_child_nearbygoods).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "瞧一瞧，看一看了啊-----家居家电类", Toast.LENGTH_SHORT).show();
            }
        });

        mHouseholdsRv = holder.getRecyclerView(R.id.households_goods_nearby_rv);
        mHouseholdsRv.setLayoutManager(new GridLayoutManager(mHouseholdsRv.getContext(),3, GridLayoutManager.VERTICAL,false));
        mHouseholdsRv.setPadding(ContextUtils.dp2px(8),ContextUtils.dp2px(8),ContextUtils.dp2px(8),ContextUtils.dp2px(8));
        mHouseholdsRv.addItemDecoration(new ItemIntervalDecoration(8,0,0));
        List<GoodsBean> list = new ArrayList<>();
        //test data ,here,you can update
        for (int i=0;i<12;i++) {
            list.add(new GoodsBean(R.mipmap.nearby_goods_clothing_dfimg,"生活好帮手"));
        }
        HouseHoldsAdapterRV houseHoldsAdapter = new HouseHoldsAdapterRV(mContext,R.layout.child_households_nearbygoods,list);
        houseHoldsAdapter.setOnItemViewClickListener(this);
        mHouseholdsRv.setAdapter(houseHoldsAdapter);
    }

    @Override
    public void setOnItemViewClick(View view, int position) {
        if (view.getParent() == mClothingRv) {
            Toast.makeText(mContext, "mClothingRv "+position+" 瞧一瞧，看一看了啊", Toast.LENGTH_SHORT).show();
        }else if (view.getParent() == mMakeupRv) {
            Toast.makeText(mContext, "mMakeupRv "+position+" 绝对是假的", Toast.LENGTH_SHORT).show();
        }else if (view.getParent() == mHouseholdsRv) {
            Toast.makeText(mContext, "mHouseholdsRv "+position+" 巴适得板", Toast.LENGTH_SHORT).show();
        }
    }
}
