package com.example.administrator.xiangou.nearby.nearbygoods;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.SimpleGoodsDetailsActivity;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;
import com.example.administrator.xiangou.nearby.apimodel.NearbyGoodsDataBean;
import com.example.administrator.xiangou.nearby.nearbygoods.adapter.NearbyGoodsAdapterRV;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.GlideImageLoader;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class NearbyGoodsFragment extends MVPBaseFragment<NearbyGoodsContract.View, NearbyGoodsPresenter> implements NearbyGoodsContract.View {

    private RecyclerView mGoodsRv;
    private Banner mBanner;
    private ArrayList<String> imgUrls;
//    private NearbyGoodsDataBean.DataBean mDataBean;
    private List<NearbyGoodsDataBean.DataBean.CatelistBean> mDataBeanList;
    private NearbyGoodsAdapterRV mAdapter;
    private int nearyDistance;//附近距离

    public void setNearyDistance(int nearyDistance) {
        this.nearyDistance = nearyDistance;
    }

    public int getNearyDistance() {
        return nearyDistance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        initData();
        return setContextView(inflater,container,R.layout.fragment_nearby_goods);
    }

    private void initData() {
    }

    @Override
    public void initView() {
        //网络请求附近商品页的数据
        mPresenter.dealNearbyGoodsCall(null,null,getNearyDistance());
        mGoodsRv = findContentView(R.id.goods_nearby_rv,false);
        mGoodsRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        mGoodsRv.addItemDecoration(new ItemIntervalDecoration(0,0,0,5));

    }

    @Override
    public void sendFialRequest(String message) {
        showToast(message);
    }

    @Override
    public void onClick(View v) {

    }

    private void initBanner(ArrayList<String> imgUrls, ArrayList<String> titles) {
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(imgUrls);
//        设置指示器位置（当banner模式中有指示器时）
//        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Default);
        mBanner.setDelayTime(2500);
        mBanner.isAutoPlay(true);
        if (titles!=null)
            mBanner.setBannerTitles(titles);
        mBanner.start();
    }

    @Override
    public void sendDataBeanToView(NearbyGoodsDataBean.DataBean data) {
//        mDataBean = data;
        imgUrls = new ArrayList<>();
        for (int i = 0; i < data.getBanner().size(); i++) {
            imgUrls.add(XianGouApiService.IMGBASEURL +data.getBanner().get(i).getAd_code());
        }
        mDataBeanList = data.getCatelist();
//        initBanner(imgUrls,null);
        mAdapter = new NearbyGoodsAdapterRV(getContext(), mDataBeanList);
        mAdapter.setOnItemViewClickListener(new RVBaseAdapter.OnItemViewClickListener() {
            @Override
            public void setOnItemViewClick(View view, int pos) {
                mBanner = (Banner) view;
                initBanner(imgUrls,null);
                mBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        showToast("点击了第 "+position+" 个");
                    }
                });
            }
        });
        mAdapter.setOnNearbyGoodsItemClickListener(new NearbyGoodsAdapterRV.NearbyGoodsItemCall() {
            @Override
            public void setOnNearbyGoodsItemCall(View view, int parentposition, int childposition) {
                showToast("mItemRv "+ childposition +((TextView)((LinearLayout)view).getChildAt(1)).getText().toString());
                // TODO: 2017/5/9 这里应该跳到商品详情页，现在就先直接添加到购物车
                Intent intent=new Intent(getContext(), SimpleGoodsDetailsActivity.class);
                intent.putExtra("goods_id",mDataBeanList.get(parentposition).getGoodslist().get(childposition).getGoods_id());
                startActivity(intent);
//                startNewUICarryStr(null,"goods_id",mDataBeanList.get(parentposition).getGoodslist().get(childposition).getGoods_id());
//                startNewUICarryStr(CartFragment.class,"goods_id",mDataBeanList.get(parentposition).getGoodslist().get(childposition).getGoods_id());
            }
        });
        mGoodsRv.setAdapter(mAdapter);
    }
}
