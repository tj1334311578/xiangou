package com.example.administrator.xiangou.home.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.home.model.HomeDataBean;
import com.example.administrator.xiangou.tool.CustomImageView;
import com.example.administrator.xiangou.tool.GlideImageLoader;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/8.
 */

public class HomeAdapterRV extends RVBaseAdapter<HomeDataBean.DataBean> implements RVBaseAdapter.OnItemViewClickListener {
    //不同recycle的type
    public static final int TYPE_BANNER = 0;
    public static final int TYPE_BOUTIQUE = 1;
    public static final int TYPE_REFERRALS = 2;
    public static final int TYPE_ADVS = 3;
    public static final int TYPE_TOPIC = 4;
//    public static final int TYPE_DEFAULT = 5;
    
    private RecyclerView mBoutiqueRv;
    private RecyclerView mReferralsRv;
    private RecyclerView mTopicRv;
    private RecyclerView mRecommendRv;

    public HomeAdapterRV(Context context, HomeDataBean.DataBean mData) {
        super(context, mData);
    }

    //数据源是类，所以重写此方法
    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
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
                return -1;
            //                return TYPE_DEFAULT;
        }
    }

//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//        if (layoutManager instanceof GridLayoutManager) {
//            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
//            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                            return gridLayoutManager.getSpanCount();
//
//                }
//            });
//        }
//    }


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
//            case TYPE_DEFAULT:
//                setLayoutResId(R.layout.recycle_default_home);
//                break;
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, HomeDataBean.DataBean homeDataBean, int position) {
        switch (position){
            case TYPE_BANNER:
                List<String> imgUrls = new ArrayList<>();
                for (HomeDataBean.DataBean.AdTopBean url: homeDataBean.getAd_top()
                        ) {
                    imgUrls.add(url.getAd_code());
                }
                bindBannerType(holder,imgUrls);
                break;
            case TYPE_BOUTIQUE:
                bindBoutiqueType(holder,homeDataBean.getStore_list());
                break;
            case TYPE_REFERRALS:
                bindReferralsType(holder,homeDataBean.getGoods_perfect());
                break;
            case TYPE_ADVS:
                bindAdvsType(holder,homeDataBean.getAds_middle().get(0).getAd_code());
                break;
            case TYPE_TOPIC:
                bindTopicType(holder,homeDataBean.getToptics().get(0).getAd_code(),homeDataBean.getGoods_toptics());
                break;
//            case TYPE_DEFAULT:
//                bindDefaultType(holder,homeDataBean.getRecommened_list());
//                break;
        }
    }

//下面是5种不同的Recycleview子布局的bindview
    /**
     * 子布局banner的bindview
     * @param holder
     * @param imgUrls
     */
    private void bindBannerType(RVBaseViewHolder holder, List<String> imgUrls){
        Banner mHomeBanner = holder.getBanner(R.id.homeBanner);
        initBanner(mHomeBanner,imgUrls,null);
//        mHomeBanner.setOnBannerListener(new OnBannerListener() {
//            @Override
//            public void OnBannerClick(int position) {
//                Toast.makeText(mContext, position+"甭点了，木有小视频", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    /**
     * 子布局Boutique的bindview
     * @param store_list
     * @param holder
     * @param store_list
     */
    private void bindBoutiqueType(RVBaseViewHolder holder, List<HomeDataBean.DataBean.StoreListBean> store_list){
        //看全部
//        TextView mAllb = holder.getTextView(R.id.enterseeall_boutique_recycle);
//        mAllb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "客官，进来看美女", Toast.LENGTH_SHORT).show();
//            }
//        });
        mBoutiqueRv = holder.getRecyclerView(R.id.child_boutique_recycle);
        mBoutiqueRv.setLayoutManager(
                new GridLayoutManager(mBoutiqueRv.getContext(),4, GridLayoutManager.VERTICAL,false));
        mBoutiqueRv.setPadding(30,11,30,8);
        mBoutiqueRv.addItemDecoration(
                new ItemIntervalDecoration(16,0,0,0));
        mBoutiqueRv.setNestedScrollingEnabled(false);
//mContext,R.layout.item_boutique_recycle,list
        BoutiqueAdapterRV mAdapter = new BoutiqueAdapterRV(mContext,R.layout.item_boutique_recycle,store_list);
        mAdapter.setOnItemViewClickListener(this);
        mBoutiqueRv.setAdapter(mAdapter);
    }
    private void bindReferralsType(RVBaseViewHolder holder, List<HomeDataBean.DataBean.GoodsPerfectBean> goods_perfect){
//        TextView mAllr = holder.getTextView(R.id.enterseeall_referrals_recycle);
//        mAllr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "来来来，给你看个宝贝", Toast.LENGTH_SHORT).show();
//            }
//        });
        mReferralsRv = holder.getRecyclerView(R.id.child_referrals_recycle);
        mReferralsRv.setLayoutManager(
                new GridLayoutManager(mReferralsRv.getContext(),5, GridLayoutManager.VERTICAL,false));
        mReferralsRv.setPadding(8,11,8,9);
        mReferralsRv.addItemDecoration(
                new ItemIntervalDecoration(5,0,0,0));
        mReferralsRv.setNestedScrollingEnabled(false);

        ReferralsAdapterRV mAdapter = new ReferralsAdapterRV(mContext,R.layout.item_referrals_recycle,goods_perfect);
        mAdapter.setOnItemViewClickListener(this);
        mReferralsRv.setAdapter(mAdapter);
    }
    private void bindAdvsType(RVBaseViewHolder holder, String ad_code){
        CustomImageView mAdvsCiv = holder.getCustomView(R.id.civ_item_advs_recycle);
        if (ad_code!=null && mAdvsCiv!=null)
            loadImg(ad_code,mAdvsCiv);
//        mAdvsCiv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "广告你也点？甭点了，木有彩蛋", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    private void bindTopicType(RVBaseViewHolder holder, String toptics, List<HomeDataBean.DataBean.GoodsTopticsBean> goods_toptics){
//        TextView mAllt = holder.getTextView(R.id.enterseeall_topic);
//        mAllt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "我们的主题是世界和平！", Toast.LENGTH_SHORT).show();
//            }
//        });
        CustomImageView mTopicCiv = holder.getCustomView(R.id.advs_topic_civ);
        mTopicRv = holder.getRecyclerView(R.id.child_topic_recycle);
        if (mTopicCiv!=null && toptics!=null){
            loadImg(toptics,mTopicCiv);
//            mTopicCiv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext, "广告你也点？你是不是傻", Toast.LENGTH_SHORT).show();
//                }
//            });
        }
        mTopicRv.setLayoutManager(
                new GridLayoutManager(mTopicRv.getContext(),6, GridLayoutManager.VERTICAL,false));
        mTopicRv.setPadding(8,8,8,5);
        mTopicRv.addItemDecoration(new ItemIntervalDecoration(7,0,0,0));
        mTopicRv.setNestedScrollingEnabled(true);

        TopicAdapterRV mAdapter = new TopicAdapterRV(mContext,R.layout.item_topic_recycle,goods_toptics);
        mAdapter.setOnItemViewClickListener(this);
        mTopicRv.setAdapter(mAdapter);
    }
    private void bindDefaultType(RVBaseViewHolder holder, List<HomeDataBean.DataBean.RecommenedListBean> recommened_list){
        mRecommendRv = holder.getRecyclerView(R.id.recommend_home_rv);
        mRecommendRv.setLayoutManager(
                new GridLayoutManager(mContext,2, GridLayoutManager.VERTICAL,false));
        mRecommendRv.addItemDecoration(new ItemIntervalDecoration(8,0,0,0));
        mRecommendRv.setNestedScrollingEnabled(true);

        RecommendAdapterRv recommend = new RecommendAdapterRv(mContext,R.layout.item_recommend_home_recycle,recommened_list);
        mRecommendRv.setAdapter(recommend);
        recommend.setOnItemViewClickListener(this);
    }

    public interface DealItemClick{
//        void dealBanner();
        void dealBoutique(View view, int pos);
        void dealReferrals(View view, int pos);
        void dealTopic(View view, int pos);
        void dealRecommened(View view, int pos);

    }
    protected DealItemClick mDealItemClick;
    public void setDealItemClick(DealItemClick mDealItemClick){
        this.mDealItemClick = mDealItemClick;
    }

    @Override
    public void setOnItemViewClick(View view, int pos) {
        if (view.getParent() == mBoutiqueRv) {
            mDealItemClick.dealBoutique(view,pos);
        }else if (view.getParent() == mReferralsRv) {
            mDealItemClick.dealReferrals(view,pos);
        }else if (view.getParent() == mTopicRv) {
            mDealItemClick.dealTopic(view,pos);
        }else if (view.getParent() == mRecommendRv){
            mDealItemClick.dealRecommened(view,pos);
        }
    }

    public Banner initBanner(Banner mBanner, List<String> imgUrls,List<String> titles) {
        //设置banner样式
        if (titles!=null) {
            mBanner.setBannerTitles(titles);
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        }else {
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        }
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER)
                .setImageLoader(new GlideImageLoader())//设置图片加载器为Glide
                .setImages(imgUrls)
                .setBannerAnimation(Transformer.Default)//设置banner动画效果
                .setDelayTime(3000)
                .isAutoPlay(true)
                .start();
        return mBanner;
    }
}
