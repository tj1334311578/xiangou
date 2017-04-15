package com.example.administrator.xiangou.home.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.home.model.BannerLoader;
import com.example.administrator.xiangou.home.model.ChildHomeBean;
import com.example.administrator.xiangou.home.model.HomeChildBean;
import com.example.administrator.xiangou.tool.CustomImageView;
import com.example.administrator.xiangou.tool.GlideImageLoader;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/8.
 */

public class HomeAdapterRV extends RVBaseAdapter<HomeChildBean> implements RVBaseAdapter.OnItemViewClickListener {
    //不同recycle的type
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_BANNER = 1;
    public static final int TYPE_BOUTIQUE = 2;
    public static final int TYPE_REFERRALS = 3;
    public static final int TYPE_ADVS = 4;
    public static final int TYPE_TOPIC = 5;
    private RecyclerView mBoutiqueRv;
    private RecyclerView mReferralsRv;
    private RecyclerView mTopicRv;

    public HomeAdapterRV(Context context, List<HomeChildBean> mDatas) {
        super(context, mDatas);
    }

    /**
     * 因为我们recyclerview里嵌套不同的item布局，正所谓一个萝卜一个坑，因此我们要根据不同的viewtype去获取不同的viewholder，
     * so，再重写onCreateViewHolder方法咯，我们是根据布局ID创建viewholder的，所以在super前设置LayoutResId。
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_BANNER:
                setLayoutResId(R.layout.recycle_banner_home);
                break;
            case TYPE_BOUTIQUE:
                setLayoutResId(R.layout.recycle_boutique_home);
                break;
            case TYPE_REFERRALS:
                setLayoutResId(R.layout.recycle_referrals_home);
                break;
            case TYPE_ADVS:
                setLayoutResId(R.layout.item_advs_recycle);
                break;
            case TYPE_TOPIC:
                setLayoutResId(R.layout.recycle_topic_home);
                break;
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return TYPE_BANNER;
            case 1:
                return TYPE_BOUTIQUE;
            case 2:
                return TYPE_REFERRALS;
            case 3:
                return TYPE_ADVS;
            case 4:
                return TYPE_TOPIC;
            default:
                return TYPE_DEFAULT;
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
                        case TYPE_BANNER:
                        case TYPE_ADVS:
                        case TYPE_BOUTIQUE:
                        case TYPE_REFERRALS:
                        case TYPE_TOPIC:
                            return gridLayoutManager.getSpanCount();
                        default:
                            return 1;
                    }

                }
            });
        }
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, HomeChildBean homeChildBean, int position) {
        switch (homeChildBean.getChildType()){
            case TYPE_BANNER:
                bindBannerType(holder);
                break;
            case TYPE_BOUTIQUE:
                bindBoutiqueType(holder);
                break;
            case TYPE_REFERRALS:
                bindReferralsType(holder);
                break;
            case TYPE_ADVS:
                bindAdvsType(holder);
                break;
            case TYPE_TOPIC:
                bindTopicType(holder);
                break;
        }
    }

    /**
     * 下面是5种不同的Recycleview子布局的bindview
     * @param holder
     */
    private void bindBannerType(RVBaseViewHolder holder){
        final Banner mHomeBanner = holder.getBanner(R.id.homeBanner);
        BannerLoader homeBanner = new BannerLoader(mHomeBanner);
        homeBanner.initBanner();
        mHomeBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(mContext, position+"甭点了，木有小视频", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindBoutiqueType(RVBaseViewHolder holder){
        TextView mAllb = holder.getTextView(R.id.enterseeall_boutique_recycle);
        mAllb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "客官，进来看美女", Toast.LENGTH_SHORT).show();
            }
        });
        mBoutiqueRv = holder.getRecyclerView(R.id.child_boutique_recycle);
        mBoutiqueRv.setLayoutManager(
                new GridLayoutManager(mBoutiqueRv.getContext(),4, GridLayoutManager.VERTICAL,false));
        mBoutiqueRv.setPadding(30,11,30,8);
        mBoutiqueRv.addItemDecoration(
                new ItemIntervalDecoration(16,0,0));
        mBoutiqueRv.setNestedScrollingEnabled(false);
        List<ChildHomeBean> list = new ArrayList<>();
        //test data ,here,you can update
        for (int i=0;i<4;i++) {
            list.add(new ChildHomeBean(R.mipmap.girl_v,"街角的美女"));
        }
//mContext,R.layout.item_boutique_recycle,list
        BoutiqueAdapterRV mAdapter = new BoutiqueAdapterRV(mContext,R.layout.item_boutique_recycle,list);
        mAdapter.setOnItemViewClickListener(this);
        mBoutiqueRv.setAdapter(mAdapter);
    }
    private void bindReferralsType(RVBaseViewHolder holder){
        TextView mAllr = holder.getTextView(R.id.enterseeall_referrals_recycle);
        mAllr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "来来来，给你看个宝贝", Toast.LENGTH_SHORT).show();
            }
        });
        mReferralsRv = holder.getRecyclerView(R.id.child_referrals_recycle);
        mReferralsRv.setLayoutManager(
                new GridLayoutManager(mReferralsRv.getContext(),5, GridLayoutManager.VERTICAL,false));
        mReferralsRv.setPadding(8,11,8,9);
        mReferralsRv.addItemDecoration(
                new ItemIntervalDecoration(5,0,0));
        mReferralsRv.setNestedScrollingEnabled(false);
        List<ChildHomeBean> list = new ArrayList<>();
        //test data ,here,you can update
        for (int i=0;i<5;i++) {
            list.add(new ChildHomeBean(R.mipmap.girl_v,"齐B小短裙"));
        }
        ReferralsAdapterRV mAdapter = new ReferralsAdapterRV(mContext,R.layout.item_referrals_recycle,list);
        mAdapter.setOnItemViewClickListener(this);
        mReferralsRv.setAdapter(mAdapter);
    }
    private void bindAdvsType(RVBaseViewHolder holder){
        CustomImageView mAdvsCiv = holder.getCustomView(R.id.civ_item_advs_recycle);
        //test data ,here,you can update
        mAdvsCiv.setImageResource(R.mipmap.banner_home);
        mAdvsCiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "广告你也点？甭点了，木有彩蛋", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void bindTopicType(RVBaseViewHolder holder){
        TextView mAllt = holder.getTextView(R.id.enterseeall_topic);
        mAllt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "我们的主题是世界和平！", Toast.LENGTH_SHORT).show();
            }
        });
        mTopicRv = holder.getRecyclerView(R.id.child_topic_recycle);
        CustomImageView mTopicCiv = holder.getCustomView(R.id.advs_topic_civ);
        mTopicRv.setLayoutManager(
                new GridLayoutManager(mTopicRv.getContext(),6, GridLayoutManager.VERTICAL,false));
        mTopicRv.setPadding(8,8,8,5);
        mTopicRv.addItemDecoration(new ItemIntervalDecoration(7,0,0));
        mTopicRv.setNestedScrollingEnabled(true);
        List<ChildHomeBean> list = new ArrayList<>();
        //test data ,here,you can update
        for (int i=0;i<6;i++) {
            list.add(new ChildHomeBean(R.mipmap.girl_v,"￥199.00"));
        }
        if (mTopicCiv!=null){
            String imgUrl = "http://img03.sogoucdn.com/app/a/100520024/55c66d7db6c9abbadfe66e0c243ffa54";
            GlideImageLoader imageLoader = new GlideImageLoader();
            imageLoader.displayImage(mContext,imgUrl,mTopicCiv);
            mTopicCiv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "广告你也点？你是不是傻", Toast.LENGTH_SHORT).show();
                }
            });
        }
        TopicAdapterRV mAdapter = new TopicAdapterRV(mContext,R.layout.item_topic_recycle,list);
        mAdapter.setOnItemViewClickListener(this);
        mTopicRv.setAdapter(mAdapter);
    }
    private void bindDefaultType(RVBaseViewHolder holder){
        String imgUrl = "http://img03.sogoucdn.com/app/a/100520024/55c66d7db6c9abbadfe66e0c243ffa54";
        CustomImageView mCustomImageView = holder.getCustomView(R.id.civ_default_recycle);
        TextView mTextView = holder.getTextView(R.id.tv_default_recycle);
        mTextView.setText("专题");
        mCustomImageView.setType(CustomImageView.TYPE_ROUND);
        GlideImageLoader imageLoader = new GlideImageLoader();
        imageLoader.displayImage(mContext,imgUrl,mCustomImageView);
    }

    @Override
    public void setOnItemViewClick(View view, int position) {
        if (view.getParent() == mBoutiqueRv) {
            Toast.makeText(mContext, "mBoutiqueRv "+position+" 甭点了，木有彩蛋", Toast.LENGTH_SHORT).show();
        }else if (view.getParent() == mReferralsRv) {
            Toast.makeText(mContext, "mReferralsRv "+position+" 甭点了，木有彩蛋", Toast.LENGTH_SHORT).show();
        }else if (view.getParent() == mTopicRv) {
            Toast.makeText(mContext, "mTopicRv "+position+" 甭点了，木有彩蛋", Toast.LENGTH_SHORT).show();
        }
    }
}
