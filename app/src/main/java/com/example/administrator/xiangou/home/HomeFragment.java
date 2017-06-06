package com.example.administrator.xiangou.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.home.adapter.HomeAdapterRV;
import com.example.administrator.xiangou.home.model.HomeDataBean;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter> implements HomeContract.View,View.OnClickListener {
    TextView mAddrTv;
    TextView mSearchTv;
    TextView mNewsTv;
    ImageView mSearchIv;
    ImageView mScanIv;
    ImageView mNewsIv;
    private RecyclerView mRvHome,mRecommendRv;

    private HomeDataBean.DataBean mDataBean;
    private HomeAdapterRV mAdapterHomeRv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("date", "onCreate: " );
        //获取首页数据
        mPresenter.getHomeData("","",0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("date", "onCreateView: " );
        return setContextView(inflater,container,R.layout.fragment_home);
    }

    @Override
    public void initView() {
        Log.e("date", "initView: "  );
        mRvHome = findContentView(R.id.rv_frag_home,false);
        mRecommendRv = findContentView(R.id.recommend_home_rv,false);
        mRvHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        mRecommendRv.setLayoutManager(
                new GridLayoutManager(getContext(),2, GridLayoutManager.VERTICAL,false));
        mRecommendRv.addItemDecoration(new ItemIntervalDecoration(8,0,0,0));
//        mRecommendRv.setNestedScrollingEnabled(true);
        mAddrTv = findContentView(R.id.addrtv_topbar);
        mNewsTv = findContentView(R.id.news_num_topbar_tv);
        mNewsIv = findContentView(R.id.news_topbar_iv);
        mScanIv = findContentView(R.id.scan_topbar_iv);
        mSearchIv = findContentView(R.id.search_topbar_iv);
        mSearchTv = findContentView(R.id.serach_topbar_tv);
    }

    @Override
    public void onStart() {
        Log.e("date", "onStart: ");
        super.onStart();
        if (mDataBean!=null){
            initRv(mDataBean);
        }
    }

    @Override
    public void onResume() {
        Log.e("date", "onResume: " );
        super.onResume();
    }

    @Override
    public void onStop() {
        Log.e("date", "onStop: " );
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("date", "onDestroy: " );
    }

    @Override
    public void onClick(View v) {
        if (v == mAddrTv){
            //在这里通过mPresenter处理数据
            Toast.makeText(getContext(), "四海为家，浪迹天涯", Toast.LENGTH_SHORT).show();
        }else if (v==mSearchIv || v==mSearchTv){
            Toast.makeText(getContext(), "众里寻他千百度", Toast.LENGTH_SHORT).show();
        }else if (v == mScanIv){
            Toast.makeText(getContext(), "扫一扫，红包跑不了", Toast.LENGTH_SHORT).show();
        }else if (v==mNewsIv || v==mNewsTv){
            Toast.makeText(getContext(), "机事不密是为祸", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void sendFialRequest(String message) {
        showToast(message);
    }

    @Override
    public void getHomeDataSuccess(HomeDataBean.DataBean data) {
        Log.e("date", "getHomeDataSuccess: "  );
        mDataBean = data;
        initRv(mDataBean);
    }

    private void initRv(HomeDataBean.DataBean dataBean) {
        mAdapterHomeRv = new HomeAdapterRV(getContext(),dataBean);
        mRvHome.setAdapter(mAdapterHomeRv);
        mAdapterHomeRv.setDealItemClick(new HomeAdapterRV.DealItemClick() {
            @Override
            public void dealBoutique(View view, int pos) {
                showToast("mBoutiqueRv "+ pos +" 甭点了，木有彩蛋");
            }

            @Override
            public void dealReferrals(View view, int pos) {
                showToast("mReferralsRv "+ pos +" 甭点了，木有彩蛋");
            }

            @Override
            public void dealTopic(View view, int pos) {
                showToast("mTopicRv "+ pos +" 甭点了，木有彩蛋");
            }

            @Override
            public void dealRecommened(View view, int pos) {
                showToast("mRecommendRv "+ pos +" 甭点了，木有彩蛋");
            }

        });
    }
}
