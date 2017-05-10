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
import com.example.administrator.xiangou.goods_details.storehome.HomePageBean;
import com.example.administrator.xiangou.tool.ContextUtils;
import com.example.administrator.xiangou.tool.GlideImageLoader;
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
    HomePageBean dataBean;
    public mHomeStoreAdapter(Context context, HomePageBean dataBean) {
//        super(context);
        this.context=context;
        this.dataBean=dataBean;
    }

    @Override
    public int getItemCount() {
//        return super.getItemCount();
        return 2;
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
//        List<Treasure> lists=new ArrayList<>();
//        lists.add(new Treasure("套装透气宽松连衣裙帅气的高大上的，你值得拥有",R.mipmap.girl_h,45,310.00,329.00,false));
//        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,364.00,true));
//        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,380.00,true));
//        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,360.00,true));
//        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,500.00,true));
//        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,420.00,true));
//        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,420.00,true));
//        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,420.00,true));
//        lists.add(new Treasure("套装透气宽松连衣裙",R.mipmap.girl_h,45,310.00,420.00,true));
        treasureRecycler=holder.getRecycleView(R.id.home_store_treasure_item_recycle);
        treasureRecycler.setLayoutManager(new GridLayoutManager(context,2));
        treasureRecycler.addItemDecoration(new ItemIntervalDecoration(4,10,5,5));
        treasureRecycler.setAdapter(new TreasureAdapter(context,dataBean.getData().getGoods_list()));
    }
    private void bindHolder1(ViewHolder holder) {
//        List<Coupon> lists=new ArrayList<>();
//        lists.add(new Coupon(5,"满99元立减"));
//        lists.add(new Coupon(10,"满199元立减"));
//        lists.add(new Coupon(15,"满299元立减"));
//        lists.add(new Coupon(20,"满399元立减"));
        couponRecycle=holder.getRecycleView(R.id.home_store_coupon_item_recycle);
        couponRecycle.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        couponRecycle.addItemDecoration(new ItemIntervalDecoration(4,0,4,14));
        couponRecycle.setAdapter(new CouponAdapter(context,dataBean.getData().getCoupon()));
    }

    @Override
    public void setOnItemViewClick(View view, int pos) {

    }

    private class CouponAdapter extends AutoRVAdapter {
        List<HomePageBean.DataBean.CouponBean> lists;
        public CouponAdapter(Context context, List<HomePageBean.DataBean.CouponBean> lists) {
            super(context,lists);
            this.lists=lists;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.home_store_coupon_item_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
//            holder.getTextView(R.id.home_store_currentent).setText(""+lists.get(position).getValue());
//            holder.getTextView(R.id.home_store_description).setText(lists.get(position).getDescription());
            double money= Double.parseDouble(lists.get(position).getMoney());
            holder.getTextView(R.id.home_store_currentent).setText(""+(int)money );
            double condition=Double.parseDouble(lists.get(position).getCondition());
            holder.getTextView(R.id.home_store_description).setText("满"+(int)condition+"元立减");
        }
    }

    private class TreasureAdapter extends AutoRVAdapter {
        private List<HomePageBean.DataBean.GoodsListBean> lists;
        public TreasureAdapter(Context context, List<HomePageBean.DataBean.GoodsListBean> lists) {
            super(context,lists);
            this.lists=lists;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.home_store_treasure_item_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //设置网络图片到指定view中显示
            GlideImageLoader imageLoader = new GlideImageLoader();
            imageLoader.displayImage(context,"http://192.168.0.106"+lists.get(position).getOriginal_img(),holder.getImgeView(R.id.home_store_treasure_item_img));
//            holder.getImgeView(R.id.home_store_treasure_item_img).setImageResource(lists.get(position).get());
            holder.getTextView(R.id.home_store_treasure_item_description).setText(lists.get(position).getGoods_name());
            holder.getTextView(R.id.home_store_treasure_item_buyers).setText("已有"+lists.get(position).getSales_sum()+"人购买");
            holder.getTextView(R.id.home_store_treasure_item_discountPrice).setText("￥"+lists.get(position).getShop_price());
            TextView oldTv=holder.getTextView(R.id.home_store_treasure_item_oldPrice);
            oldTv.setText("￥"+lists.get(position).getMarket_price());
            oldTv.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);//加中线并加清晰
            //是否为新品
            if (lists.get(position).getIs_new()==1){
                holder.getTextView(R.id.home_store_treasure_item_status).setVisibility(View.VISIBLE);
            }else{
                holder.getTextView(R.id.home_store_treasure_item_status).setVisibility(View.GONE);
            }
        }
    }
}
