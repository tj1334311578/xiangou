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
import com.example.administrator.xiangou.base.BaseAdapter;
import com.example.administrator.xiangou.base.BaseViewHolder;
import com.example.administrator.xiangou.nearby.ChildType;
import com.example.administrator.xiangou.nearby.nearbygoods.GoodsBean;
import com.example.administrator.xiangou.tool.ContextUtils;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/13.
 */

public class NearbyGoodsAdapter extends BaseAdapter<ChildType> implements BaseAdapter.OnMineItemClickListener{
//    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_CLOTHING = 1;
    public static final int TYPE_MAKEUP = 2;
    public static final int TYPE_HOUSEHOLDS = 3;

    private RecyclerView mClothingRv,mMakeupRv,mHouseholdsRv;

    public NearbyGoodsAdapter(Context context, List<ChildType> mDatas) {
        super(context, mDatas);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
    protected void bindData(BaseViewHolder holder, ChildType childType, int position) {
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

    public void bindClothing(BaseViewHolder holder) {
        ImageView mAdvsIv = holder.getImageView(R.id.advs_goods_nearby);
        ImageView mIcon = holder.getImageView(R.id.icon_child_nearbygoods);
        TextView mText = holder.getTextView(R.id.text_child_nearbygoods);
        TextView mDescribe = holder.getTextView(R.id.describe_child_nearby);
        mAdvsIv.setImageResource(R.mipmap.nearby_goods_advs);
        mIcon.setImageResource(R.mipmap.nearby_goods_clothing_icon);
        mText.setText("服装类");
        mDescribe.setText("当前主流潮服");
        holder.getImageView(R.id.img_child_nearbygoods).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "瞧一瞧，看一看了啊+++++1", Toast.LENGTH_SHORT).show();
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
        ClothingAdapter goodsAdapter = new ClothingAdapter(mContext,R.layout.child_clothing_nearbygoods,list);
        goodsAdapter.setOnMineItemClickListener(this);
        mClothingRv.setAdapter(goodsAdapter);
    }
    public void bindMakeup(BaseViewHolder holder) {
        ImageView mIcon = holder.getImageView(R.id.icon_child_nearbygoods);
        TextView mText = holder.getTextView(R.id.text_child_nearbygoods);
        TextView mDescribe = holder.getTextView(R.id.describe_child_nearby);
        mIcon.setImageResource(R.mipmap.nearby_goods_makeup_icon);
        mText.setText("化妆类");
        mDescribe.setText("必备神器");
        holder.getImageView(R.id.img_child_nearbygoods).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "瞧一瞧，看一看了啊+++2", Toast.LENGTH_SHORT).show();
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
        MakeupAdapter makeupAdapter = new MakeupAdapter(mContext,R.layout.child_makeup_nearbygoods,list);
        makeupAdapter.setOnMineItemClickListener(this);
        mMakeupRv.setAdapter(makeupAdapter);
    }
    public void bindHouseholds(BaseViewHolder holder) {
        ImageView mIcon = holder.getImageView(R.id.icon_child_nearbygoods);
        TextView mText = holder.getTextView(R.id.text_child_nearbygoods);
        TextView mDescribe = holder.getTextView(R.id.describe_child_nearby);
        mIcon.setImageResource(R.mipmap.nearby_goods_households_icon);
        mText.setText("家居家电类");
        mDescribe.setText("生活好帮手");
        holder.getImageView(R.id.img_child_nearbygoods).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "瞧一瞧，看一看了啊3", Toast.LENGTH_SHORT).show();
            }
        });

        mHouseholdsRv = holder.getRecyclerView(R.id.households_goods_nearby_rv);
        mHouseholdsRv.setLayoutManager(new GridLayoutManager(mHouseholdsRv.getContext(),3, GridLayoutManager.HORIZONTAL,false));
        mHouseholdsRv.setPadding(ContextUtils.dp2px(8),ContextUtils.dp2px(8),ContextUtils.dp2px(8),ContextUtils.dp2px(8));
        mHouseholdsRv.addItemDecoration(new ItemIntervalDecoration(8,0,0));
        List<GoodsBean> list = new ArrayList<>();
        //test data ,here,you can update
        for (int i=0;i<12;i++) {
            list.add(new GoodsBean(R.mipmap.nearby_goods_clothing_dfimg,"生活好帮手"));
        }
        HouseHoldsAdapter houseHoldsAdapter = new HouseHoldsAdapter(mContext,R.layout.child_households_nearbygoods,list);
        houseHoldsAdapter.setOnMineItemClickListener(this);
        mHouseholdsRv.setAdapter(houseHoldsAdapter);
    }



    @Override
    public void onMineItemClick(View view, int position) {
        if (view.getParent() == mClothingRv) {
            Toast.makeText(mContext, "mClothingRv "+position+" 瞧一瞧，看一看了啊", Toast.LENGTH_SHORT).show();
        }else if (view.getParent() == mMakeupRv) {
            Toast.makeText(mContext, "mMakeupRv "+position+" 绝对是假的", Toast.LENGTH_SHORT).show();
        }else if (view.getParent() == mHouseholdsRv) {
            Toast.makeText(mContext, "mHouseholdsRv "+position+" 巴适得板", Toast.LENGTH_SHORT).show();
        }
    }

}
