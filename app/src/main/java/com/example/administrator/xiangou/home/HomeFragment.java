package com.example.administrator.xiangou.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.administrator.xiangou.goods_sort.storehome.StoreHomeActivity;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.SimpleGoodsDetailsActivity;
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
        Log.e("homefg", "onCreate: " );
        //获取首页数据
        mPresenter.getHomeData("","",0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("homefg", "onCreateView: " );
        return setContextView(inflater,container,R.layout.fragment_home);
    }

    @Override
    public void initView() {
        Log.e("homefg", "initView: "  );
        mRvHome = findContentView(R.id.rv_frag_home,false);
        mRvHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
//        mRecommendRv = findContentView(R.id.recommend_home_rv,false);
//        mRecommendRv.setLayoutManager(new GridLayoutManager(getContext(),2, GridLayoutManager.VERTICAL,false));
        mRvHome.addItemDecoration(new ItemIntervalDecoration(0,8,0,0));
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
        Log.e("homefg", "onStart: ");
        super.onStart();
        if (mDataBean!=null){
            initRv(mDataBean);
        }
    }

    @Override
    public void onResume() {
        Log.e("homefg", "onResume: " );
        super.onResume();
    }

    @Override
    public void onStop() {
        Log.e("homefg", "onStop: " );
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("homefg", "onDestroy: " );
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
        Log.e("homefg", "getHomeDataSuccess: data "  );
        mDataBean = data;
        initRv(mDataBean);
    }

    private void initRv(final HomeDataBean.DataBean dataBean) {
        mAdapterHomeRv = new HomeAdapterRV(getContext(),dataBean);
        mRvHome.setAdapter(mAdapterHomeRv);
        mAdapterHomeRv.setDealItemClick(new HomeAdapterRV.DealItemClick() {
            @Override
            public void dealBoutique(View view, int pos) {
                showToast("mBoutiqueRv "+ pos +" 甭点了，木有彩蛋");
                //跳转到店铺
                Intent intent=new Intent(getContext(), StoreHomeActivity.class);
                intent.putExtra("store_id",dataBean.getStore_list().get(pos).getDid());
                startActivity(intent);
            }

            @Override
            public void dealReferrals(View view, int pos) {
                showToast("mReferralsRv "+ pos +" 甭点了，木有彩蛋");
                Intent intent=new Intent(getContext(), SimpleGoodsDetailsActivity.class);
                intent.putExtra("goods_id",dataBean.getGoods_perfect().get(pos).getGoods_id());
                getContext().startActivity(intent);
            }

            @Override
            public void dealTopic(View view, int pos) {
                showToast("mTopicRv "+ pos +" 甭点了，木有彩蛋");
                Intent intent=new Intent(getContext(), SimpleGoodsDetailsActivity.class);
                intent.putExtra("goods_id",dataBean.getGoods_toptics().get(pos).getGoods_id());
                getContext().startActivity(intent);
            }

            @Override
            public void dealRecommened(View view, int pos) {
                showToast("mRecommendRv "+ pos +" 甭点了，木有彩蛋");
                Intent intent=new Intent(getContext(), SimpleGoodsDetailsActivity.class);
                intent.putExtra("goods_id",dataBean.getRecommened_list().get(pos).getGoods_id());
                getContext().startActivity(intent);
            }

        });
//
//        RecommendAdapterRv recommend = new RecommendAdapterRv(getContext(),R.layout.item_recommend_home_recycle,dataBean.getRecommened_list());
//        mRecommendRv.setAdapter(recommend);
//        recommend.setOnItemViewClickListener(new RVBaseAdapter.OnItemViewClickListener() {
//            @Override
//            public void setOnItemViewClick(View view, int pos) {
//                showToast("这是第 "+pos+" 个");
//            }
//        });
    }
}
