package com.example.administrator.xiangou.goods_sort.storehome.storehome;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.goods_sort.comprehensive.ComprehensiveFragment;
import com.example.administrator.xiangou.goods_sort.storehome.HomePageBean;
import com.example.administrator.xiangou.goods_sort.storehome.storehome.homestore.HomeStoreFragment;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.DrawableTextView;
import com.example.administrator.xiangou.tool.GlideImageLoader;
import com.example.administrator.xiangou.tool.SelectImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StoreHomeFragment extends MVPBaseFragment<StoreHomeContract.View, StoreHomePresenter> implements StoreHomeContract.View {
     TabLayout mTabLayout;
     ViewPager mViewPager;
     ImageView backBtn;
     RelativeLayout background;
     SelectImageView storeImg;
     TextView storeName,salesVolume,follow;
     RatingBar ratingBar;
     DrawableTextView showstore;
    private List<Fragment> mTabFragList;
    private String[] tabTitles;
    private StoreHomeTabLayoutAdapter mLayoutAdapter;
    private HomeStoreFragment homeStoreFragment;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,R.layout.goods_storehome);
    }

    @Override
    public void onClick(View v) {
        if (v==backBtn)
            getActivity().finish();

    }

    private void initTabFragViews() {
//        backBtn= (ImageView) mContextView.findViewById(R.id.goods_storehome_head).findViewById(R.id.home_store_head_Return);
        mTabLayout=findContentView(R.id.tabs_goodsstorehome_tab,false);
        mViewPager=findContentView(R.id.tabs_goodsstorehome_viewpager,false);
        backBtn=findContentView(R.id.home_store_head_Return);
        background=findContentView(R.id.home_store_head_background,false);
        storeImg=findContentView(R.id.home_store_head_storeImage,false);
        storeName=findContentView(R.id.home_store_headStoreName,false);
        ratingBar=findContentView(R.id.home_store_ratingbar,false);
        salesVolume=findContentView(R.id.home_store_head_salesVolume,false);
        follow=findContentView(R.id.home_store_head_follow,false);
        showstore=findContentView(R.id.home_store_head_showStore,false);
//        background=findContentView(R.id.home_store_head_background,false);
//        storeImg=findContentView(R.id.home_store_head_storeImage,false);
//        storeName=findContentView(R.id.home_store_headStoreName,false);
//        ratingBar=findContentView(R.id.home_store_ratingbar,false);
//        salesVolume=findContentView(R.id.home_store_head_salesVolume,false);
//        follow=findContentView(R.id.home_store_head_follow,false);
//        showstore=findContentView(R.id.home_store_head_showStore,false);
//        findContentView(R.id.home_store_head_Return,true);
        //网络获取数据
        int store_id=getActivity().getIntent().getIntExtra("store_id",0);
        mPresenter.dealStoreHomeCall(store_id,getUser().getUser_id());
    }

    private void initHeadView(HomePageBean dataBean) {

        GlideImageLoader imageLoader = new GlideImageLoader();
        imageLoader.displayImage(getContext(), XianGouApiService.IMGBASEURL +dataBean.getData().getLogo(),storeImg);
        storeName.setText(dataBean.getData().getName());
        ratingBar.setRating((float) Double.parseDouble(dataBean.getData().getScore()));
        salesVolume.setText("销量"+dataBean.getData().getTotal_sale());
    //      new GlideImageLoader().displayImage(getContext(),XianGouApiService.IMGBASEURL+dataBean.getData().getBanner(),background);
//        background= (RelativeLayout) mContextView.findViewById(R.id.goods_storehome_head).findViewById(R.id.home_store_head_background);
//                findContentView(R.id.home_store_head_background,false);
//        if (background!=null)
        Bitmap[] bitmap=(GlideImageLoader.setbackground(getContext(),XianGouApiService.IMGBASEURL+ dataBean.getData().getBanner()));
        Log.e("bitmap", "initHeadView: "+bitmap.toString() );
//        background.setBackground(new BitmapDrawable(bitmap[0]));
        follow.setText("关注"+dataBean.getData().getFollow());
        mContextView.findViewById(R.id.goods_storehome_head).setVisibility(View.VISIBLE);
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void initView() {
        initTabFragViews();
    }
//
    @Override
    public void sendDataBeanToView(HomePageBean dataBean) {
        Log.e("sendDataBeanToView", "sendDataBeanToView: "+dataBean.toString() );
        //传递数据
        initHeadView(dataBean);
        tabTitles=new String[]{"店铺首页","所有宝贝"};
        mTabFragList =new ArrayList<>();
        homeStoreFragment = new HomeStoreFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("databean",dataBean);
        homeStoreFragment.setArguments(bundle);

        mTabFragList.add(homeStoreFragment);
        mTabFragList.add(new ComprehensiveFragment());
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mLayoutAdapter = new StoreHomeTabLayoutAdapter(getContext(), getChildFragmentManager(), mTabFragList,tabTitles);
        mViewPager.setAdapter(mLayoutAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < tabTitles.length; i++) {
            final TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(mLayoutAdapter.getTabItemView(i));
                if (tab.getCustomView() != null) {
                    final View tabView = (View) tab.getCustomView().getParent();
                    tabView.setTag("tab" + i);
                    tabView.setOnClickListener(this);
                }
            }
        }

        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);
                for (int i=0; i<mTabFragList.size(); i++) {
                    if (mTabFragList.get(i).isAdded()) {
                        if (i==position) {
                            getChildFragmentManager().beginTransaction()
                                    .show(mTabFragList.get(i))
                                    .commit();
                        }else {
                            getChildFragmentManager().beginTransaction()
                                    .hide(mTabFragList.get(i))
                                    .commit();
                        }
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
