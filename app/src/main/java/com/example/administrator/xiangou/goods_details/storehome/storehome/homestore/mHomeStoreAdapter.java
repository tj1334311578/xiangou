package com.example.administrator.xiangou.goods_details.storehome.storehome.homestore;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.AutoRVAdapter;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */
public class mHomeStoreAdapter extends AutoRVAdapter implements RVBaseAdapter.OnItemViewClickListener {
    //附近优惠和镇店之宝recycle  item
    RecyclerView couponRecycle,treasureRecycler;
    Context context;
    public mHomeStoreAdapter(Context context, List<String> list) {
        super(context,list);
        this.context=context;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return TYPE_1;
            case 1:
                return TYPE_2;
            default:
                return TYPE_MAIN;
        }
    }

    @Override
    protected int onCreateViewHolderID(int viewType) {
        switch (viewType){
            case TYPE_1:
                return R.layout.home_store_coupon_item;
            case TYPE_2:
                return R.layout.home_store_treasure_item;
            default:
                return R.layout.home_store_coupon_item;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (position){
            case 0:
                bindHolder1(holder);
                break;
            case 1:
                bindHolder2(holder);
                break;
            default:
                bindHolder1(holder);
                break;
        }
    }

    private void bindHolder2(ViewHolder holder) {
        List<Treasure> lists=new ArrayList<>();
        lists.add(new Treasure("套装透气宽松连衣裙帅气的高大上的，你值得拥有",R.mipmap.girl_h,45,310.00,329.00,false));
        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,364.00,true));
        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,380.00,true));
        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,360.00,true));
        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,500.00,true));
        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,420.00,true));
        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,420.00,true));
        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,420.00,true));
        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,420.00,true));
        treasureRecycler=holder.getRecycleView(R.id.home_store_treasure_item_recycle);
        treasureRecycler.setLayoutManager(new GridLayoutManager(context,2));
        treasureRecycler.addItemDecoration(new ItemIntervalDecoration(0,0,9,15));
        treasureRecycler.setAdapter(new TreasureAdapter(context,lists));
    }
    private void bindHolder1(ViewHolder holder) {
        List<Coupon> lists=new ArrayList<>();
        lists.add(new Coupon(5,"满99元立减"));
        lists.add(new Coupon(10,"满199元立减"));
        lists.add(new Coupon(15,"满299元立减"));
        lists.add(new Coupon(20,"满399元立减"));
        couponRecycle=holder.getRecycleView(R.id.home_store_coupon_item_recycle);
        couponRecycle.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        couponRecycle.addItemDecoration(new ItemIntervalDecoration(4,0,4,14));
        couponRecycle.setAdapter(new CouponAdapter(context,lists));
    }

    @Override
    public void setOnItemViewClick(View view, int pos) {

    }

    private class CouponAdapter extends AutoRVAdapter {
        List<Coupon> lists;
        public CouponAdapter(Context context, List<Coupon> lists) {
            super(context,lists);
            this.lists=lists;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.home_store_coupon_item_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.getTextView(R.id.home_store_currentent).setText(""+lists.get(position).getValue());
            holder.getTextView(R.id.home_store_description).setText(lists.get(position).getDescription());
        }
    }

    private class TreasureAdapter extends AutoRVAdapter {
        private List<Treasure> lists;
        public TreasureAdapter(Context context, List<Treasure> lists) {
            super(context,lists);
            this.lists=lists;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.home_store_treasure_item_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.getImgeView(R.id.home_store_treasure_item_img).setImageResource(lists.get(position).getImg());
            holder.getTextView(R.id.home_store_treasure_item_description).setText(lists.get(position).getGoods_description());
            holder.getTextView(R.id.home_store_treasure_item_buyers).setText("已有"+lists.get(position).getBeBuyCount()+"人购买");
            holder.getTextView(R.id.home_store_treasure_item_discountPrice).setText("￥"+lists.get(position).getPrice());
            TextView oldTv=holder.getTextView(R.id.home_store_treasure_item_oldPrice);
            oldTv.setText("￥"+lists.get(position).getOldprice());
            oldTv.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);//加中线并加清晰
            if (lists.get(position).isNewGoods()){
                holder.getTextView(R.id.home_store_treasure_item_status).setVisibility(View.VISIBLE);
            }else{
                holder.getTextView(R.id.home_store_treasure_item_status).setVisibility(View.GONE);
            }
        }
    }
}
