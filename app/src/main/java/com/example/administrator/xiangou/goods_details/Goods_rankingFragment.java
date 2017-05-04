package com.example.administrator.xiangou.goods_details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.goods_details.comprehensive.ComprehensiveFragment;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;
import com.example.administrator.xiangou.nearby.NearbyContract;
import com.example.administrator.xiangou.nearby.NearbyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/15.
 */

public class Goods_rankingFragment extends MVPBaseFragment<NearbyContract.View, NearbyPresenter> implements NearbyContract.View{
    @BindView(R.id.tabs_goodsRanking_tab)
    TabLayout mTabLayout;
    @BindView(R.id.goods_ranking_back)
    ImageView backBtn;
    @BindView(R.id.tabs_goodsRanking_viewpager)
    ViewPager mViewPager;
    private RankingTabLayoutAdapter mLayoutAdapter;
    private List<Fragment> mTabFragList;
    private String[] tabTitles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,R.layout.goods_ranking);
    }

    @Override
    public void initView() {
        ButterKnife.bind(this,mContextView);
        backBtn.setOnClickListener(this);
        initTabFragViews();
    }

    private void initTabFragViews() {
        tabTitles = new String[]{"综合", "销量", "新品", "价格"};
        mTabFragList = new ArrayList<>();
        mTabFragList.add(new ComprehensiveFragment());
        mTabFragList.add(new ComprehensiveFragment());
        mTabFragList.add(new ComprehensiveFragment());
        mTabFragList.add(new ComprehensiveFragment());
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goods_ranking_back:
                getActivity().finish();
            default:
                break;
        }

    }


    @Override
    public void sendFialRequest(String message) {
    }
}
