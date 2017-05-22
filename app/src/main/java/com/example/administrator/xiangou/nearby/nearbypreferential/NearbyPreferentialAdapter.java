package com.example.administrator.xiangou.nearby.nearbypreferential;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.AutoRVAdapter;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.SimpleGoodsDetailsActivity;
import com.example.administrator.xiangou.nearby.apimodel.NearbyBenifitDataBean;

import java.util.List;


/**
 * Created by Administrator on 2017/3/15.
 */

public class NearbyPreferentialAdapter extends RVBaseAdapter<NearbyBenifitDataBean.DataBean> {
    RecyclerView dayRecycer,hourRecycler,moreRecycler;

    public static final int TYPE_DATE = 1;
    public static final int TYPE_PREFERENTIAL = 2;
    public static final int TYPE_MORE = 3;
    private NearbyBenifitDataBean.DataBean mDataBean;
    private long mCountDownTime;
    private boolean hasDownTieme;

    public NearbyPreferentialCall mPreferentialCall;
    public interface NearbyPreferentialCall{
        void dealTimeLimitModule(TextView hTv, TextView mTv, TextView sTv);
    }
    public void setNearbyPrefertialCall(NearbyPreferentialCall mPreferentialCall){
        this.mPreferentialCall = mPreferentialCall;
    }

    public NearbyPreferentialAdapter(Context context, List<NearbyBenifitDataBean.DataBean> mDatas) {
        super(context, mDatas);
        mDataBean = mDatas.get(0);
        hasDownTieme = true;
    }

    /**
     *   设置item的类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return TYPE_DATE;
            case 1:
                return TYPE_PREFERENTIAL;
            case 2:
                return TYPE_MORE;
        }
        return 2;
    }

    /**
     * 不同类型item的布局
     * @param viewType
     * @return
     */

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_DATE:
                setLayoutResId(R.layout.nearby_item_day);
                break;
            case TYPE_PREFERENTIAL:
                setLayoutResId(R.layout.nearby_item_hour);
                break;
            case TYPE_MORE:
                setLayoutResId(R.layout.nearby_item_more);
                break;
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, NearbyBenifitDataBean.DataBean dataBean, int position) {

    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        switch(position){
            case 0:
                bindHolder1(holder);
                break;
            case 1:
                bindHolder2(holder);
                break;
            case 2:
                bindHolder3(holder);
                break;
        }
    }

    private void bindHolder3(RVBaseViewHolder holder) {
        moreRecycler=holder.getRecyclerView(R.id.item_more_recycleView);
        moreRecycler.setLayoutManager(new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL,false));
        moreRecycler.setAdapter(new PrefertialAdapter3(mContext,mDataBean.getBehave()));
        moreRecycler.setHasFixedSize(true);
    }

    private void bindHolder2(final RVBaseViewHolder holder) {
        //配置第一级recycle item数据

        mPreferentialCall.dealTimeLimitModule(holder.getTextView(R.id.item_hour_hour),holder.getTextView(R.id.item_hour_min),holder.getTextView(R.id.item_hour_sec));
        //配置第二级recycle item数据
        hourRecycler=holder.getRecyclerView(R.id.item_hour_recycleView);
        hourRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false));
        hourRecycler.setAdapter(new PrefertialAdapter2(mContext, mDataBean.getFlash()));
    }

    private void bindHolder1(RVBaseViewHolder holder) {
//        Log.e("tag", "bindHolder1: "+holder.getRecycleView(R.id.item_day_recycle).getId());
        dayRecycer=holder.getRecyclerView(R.id.item_day_recycle);
        dayRecycer.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
        dayRecycer.setAdapter(new PrefertialAdapter1(mContext, mDataBean.getToday_goods()));


    }

    /**
     * 第二层的recycleView设置adapter所需要的适配器
     */
        public class PrefertialAdapter1 extends AutoRVAdapter{

        List<NearbyBenifitDataBean.DataBean.TodayGoodsBean> mBeanList;
        public PrefertialAdapter1(Context context, List<NearbyBenifitDataBean.DataBean.TodayGoodsBean> list) {
            super(context,list);
            this.mBeanList =list;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.nearby_item_day_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            loadImg(mBeanList.get(position).getOriginal_img(),holder.getImgeView(R.id.item_day_item_img));
            holder.getTextView(R.id.item_day_item_description).setText(mBeanList.get(position).getGoods_name());
            //原价效果设置
            holder.getTextView(R.id.item_day_item_originalprice).setText("￥"+ mBeanList.get(position).getMarket_price());
            //中间加横线、高清
            holder.getTextView(R.id.item_day_item_originalprice).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
            //现价
            holder.getTextView(R.id.item_day_item_presentprice).setText("￥"+ mBeanList.get(position).getShop_price());
            //控件设置监听
            holder.get(R.id.nearby_dayitem_rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点击了今日:"+position, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(mContext, SimpleGoodsDetailsActivity.class);
                    intent.putExtra("goods_id",mBeanList.get(position).getGoods_id());
                    mContext.startActivity(intent);
                }
            });
        }

    }

    private class PrefertialAdapter2 extends AutoRVAdapter {
        NearbyBenifitDataBean.DataBean.FlashBean mFlashBean;
        Context context;
        public PrefertialAdapter2(Context context, NearbyBenifitDataBean.DataBean.FlashBean list) {
            this.context = context;
            this.mFlashBean = list;
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.nearby_item_hour_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            loadImg(mFlashBean.getOriginal_img(),holder.getImgeView(R.id.item_hour_item_img));
            holder.getTextView(R.id.item_hour_item_description1).setText(mFlashBean.getFlash_name());
            holder.getTextView(R.id.item_hour_item_description2).setText(mFlashBean.getGoods_name());
            //原价
            holder.getTextView(R.id.item_hour_item_originalprice).setText("￥ "+ mFlashBean.getShop_price());
            holder.getTextView(R.id.item_hour_item_originalprice).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            holder.get(R.id.item_hour_item_show).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点击查看:"+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class PrefertialAdapter3 extends AutoRVAdapter {
        private  List<NearbyBenifitDataBean.DataBean.BehaveBean> mBeanList;

        public PrefertialAdapter3(Context context, List<NearbyBenifitDataBean.DataBean.BehaveBean> list) {
            super(context, list);
            this.mBeanList = list;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.nearby_item_more_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            loadImg(mBeanList.get(position).getOriginal_img(),holder.getImgeView(R.id.item_more_item_img));
            holder.getTextView(R.id.item_more_item_text).setText(mBeanList.get(position).getGoods_name());
            holder.getTextView(R.id.item_present_price_tv).setText("￥"+mBeanList.get(position).getMarket_price());
            holder.getTextView(R.id.item_old_price_tv).setText("￥"+mBeanList.get(position).getShop_price());
            holder.getTextView(R.id.item_old_price_tv).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
            holder.get(R.id.nearby_moreitem_rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点击了更多:"+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
