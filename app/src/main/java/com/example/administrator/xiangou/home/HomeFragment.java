package com.example.administrator.xiangou.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter> implements HomeContract.View,View.OnClickListener {
    TextView mAddrTv;
    TextView mSearchTv;
    TextView mNewsTv;
    ImageView mSearchIv;
    ImageView mScanIv;
    ImageView mNewsIv;
    private RecyclerView mRvHome;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取首页数据
        mPresenter.getHomeData("","",0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,R.layout.fragment_home);
    }

    @Override
    public void initView() {
        mRvHome = findContentView(R.id.rv_frag_home,false);
        LinearLayoutManager glm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        mRvHome.setLayoutManager(glm);
        mAddrTv = findContentView(R.id.addrtv_topbar);
        mNewsTv = findContentView(R.id.news_num_topbar_tv);
        mNewsIv = findContentView(R.id.news_topbar_iv);
        mScanIv = findContentView(R.id.scan_topbar_iv);
        mSearchIv = findContentView(R.id.search_topbar_iv);
        mSearchTv = findContentView(R.id.serach_topbar_tv);
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
        HomeAdapterRV adapterHomeRv = new HomeAdapterRV(getContext(),data);
        mRvHome.setAdapter(adapterHomeRv);
        adapterHomeRv.setDealItemClick(new HomeAdapterRV.DealItemClick() {
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
