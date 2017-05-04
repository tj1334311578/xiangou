package com.example.administrator.xiangou.mine.myorder.myorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.goods_details.RankingTabLayoutAdapter;
import com.example.administrator.xiangou.mine.myorder.myorder.waitdelivery.WaitDeliveryFragment;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyOrderFragment extends MVPBaseFragment<MyOrderContract.View, MyOrderPresenter> implements MyOrderContract.View {
    private int position=0;
    private TextView TitleTv,SaveTv;
    @BindView(R.id.myorder_tab)
    TabLayout mTabLayout;
    ImageView backBtn;
    @BindView(R.id.myorder_pager)
    ViewPager mViewPager;
    private RankingTabLayoutAdapter mLayoutAdapter;
    private List<Fragment> mTabFragList;
    private String[] tabTitles;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container, R.layout.myorder);
    }

    @Override
    public void onClick(View v) {
        if (v==backBtn){
            getActivity().finish();
        }
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void initView() {
        backBtn= (ImageView) mContextView.findViewById(R.id.myorder_head).findViewById(R.id.setting_head_back);
        TitleTv= (TextView) mContextView.findViewById(R.id.myorder_head).findViewById(R.id.setting_head_center);
        SaveTv= (TextView) mContextView.findViewById(R.id.myorder_head).findViewById(R.id.setting_head_right);
        TitleTv.setText("我的订单");
        SaveTv.setVisibility(View.GONE);
        ButterKnife.bind(this,mContextView);
        backBtn.setOnClickListener(this);
        initTabFragViews();
    }

    private void initTabFragViews() {
        position=Integer.parseInt(getActivity().getIntent().getStringExtra("position"));
        tabTitles = new String[]{"全部", "待付款", "待发货", "待收货","待评价"};
        mTabFragList = new ArrayList<>();

//        mTabFragList.add(new ComprehensiveFragment());
//        mTabFragList.add(new ComprehensiveFragment());
//        mTabFragList.add(new ComprehensiveFragment());
//        mTabFragList.add( new WaitDeliveryFragment());
//        mTabFragList.add(new WaitDeliveryFragment());
//        List<DeliveryBean> lists=new ArrayList<>();
//        List<WaitDeliveryFragment> fts=new ArrayList<>();
        for (int i=0;i<tabTitles.length;i++){
           WaitDeliveryFragment ft=new WaitDeliveryFragment();
//            fts.add(ft);
            ft.setPosition(i);
            mTabFragList.add(ft);
        }
//        for (int i = fts.size()-1; i >=0 ; i--) {
//            if (i!=0) {
//                Log.e("initTabFragViews", "initTabFragViews: "+ fts.get(i).getLists());
//                lists.addAll(fts.get(i).getLists());
//            }else{
//                fts.get(i).setLists(lists);
//            }
//        }

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mLayoutAdapter = new RankingTabLayoutAdapter(getContext(), getChildFragmentManager(), mTabFragList, tabTitles);
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
        mViewPager.setCurrentItem(position);
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
                        }
                        else {
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
