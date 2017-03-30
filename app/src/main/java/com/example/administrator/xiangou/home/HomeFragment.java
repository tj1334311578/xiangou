package com.example.administrator.xiangou.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.BaseAdapter;
import com.example.administrator.xiangou.home.adapter.HomeAdapter;
import com.example.administrator.xiangou.home.model.HomeChildBean;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter> implements HomeContract.View,View.OnClickListener {
    @BindView(R.id.addrtv_topbar)
    TextView mAddrTv;
    @BindView(R.id.serach_topbar_tv)
    TextView mSearchTv;
    @BindView(R.id.news_num_topbar_tv)
    TextView mNewsTv;
    @BindView(R.id.search_topbar_iv)
    ImageView mSearchIv;
    @BindView(R.id.scan_topbar_iv)
    ImageView mScanIv;
    @BindView(R.id.news_topbar_iv)
    ImageView mNewsIv;
    //不同recycle的type
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_BANNER = 1;
    public static final int TYPE_BOUTIQUE = 2;
    public static final int TYPE_REFERRALS = 3;
    public static final int TYPE_ADVS = 4;
    public static final int TYPE_TOPIC = 5;
    private View mFragView;
    public static HomeFragment newInstance() {
        HomeFragment homeFrag = new HomeFragment();
        return homeFrag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragView = inflater.inflate(R.layout.fragment_home,container,false);
        ButterKnife.bind(this,mFragView);
        initView();
        return mFragView;
    }

    private void initView() {
        mAddrTv.setOnClickListener(this);
        mNewsTv.setOnClickListener(this);
        mNewsIv.setOnClickListener(this);
        mScanIv.setOnClickListener(this);
        mSearchIv.setOnClickListener(this);
        mSearchTv.setOnClickListener(this);
        RecyclerView rvHome = (RecyclerView) mFragView.findViewById(R.id.rv_frag_home);
        GridLayoutManager glm = new GridLayoutManager(getContext(),60, GridLayoutManager.VERTICAL,false);
        rvHome.setLayoutManager(glm);

        List<HomeChildBean> beanList = new ArrayList<>();
        beanList.add(new HomeChildBean(TYPE_BANNER));
        beanList.add(new HomeChildBean(TYPE_BOUTIQUE));
        beanList.add(new HomeChildBean(TYPE_REFERRALS));
        beanList.add(new HomeChildBean(TYPE_ADVS));
        beanList.add(new HomeChildBean(TYPE_TOPIC));
        HomeAdapter adapterHomeRv = new HomeAdapter(getContext(),beanList);
        adapterHomeRv.setOnMineItemClickListener(new BaseAdapter.OnMineItemClickListener() {
            @Override
            public void onMineItemClick(View view, int position) {

            }

            @Override
            public void onMineItemLongClick(View view, int position) {

            }
        });
        rvHome.setAdapter(adapterHomeRv);
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

    }
}
