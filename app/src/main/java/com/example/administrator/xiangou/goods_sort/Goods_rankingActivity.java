package com.example.administrator.xiangou.goods_sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.goods_sort.comprehensive.ComprehensiveFragment;
import com.example.administrator.xiangou.tool.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/15.
 */

public class Goods_rankingActivity extends BaseActivity {
//    @BindView(R.id.tabs_goodsRanking_tab)
    TabLayout mTabLayout;
//    @BindView(R.id.goods_ranking_back)
    ImageView backBtn;
//    @BindView(R.id.tabs_goodsRanking_viewpager)
    ViewPager mViewPager;
    private RankingTabLayoutAdapter mLayoutAdapter;
    private List<Fragment> mTabFragList;
    private String[] tabTitles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.goods_ranking);
//        setContentView(R.layout.goods_details_activity);
        super.onCreate(savedInstanceState);
        mTabLayout = (TabLayout) findViewById(R.id.tabs_goodsRanking_tab);
        backBtn = (ImageView) findViewById(R.id.goods_ranking_back);
        mViewPager = (ViewPager) findViewById(R.id.tabs_goodsRanking_viewpager);
        backBtn.setOnClickListener(this);
        initTabFragViews();
    }

    private void initTabFragViews() {
        tabTitles = new String[]{"综合", "销量", "新品", "价格"};
        mTabFragList = new ArrayList<>();
        mTabFragList.add(new ComprehensiveFragment(0));
        mTabFragList.add(new ComprehensiveFragment(1));
        mTabFragList.add(new ComprehensiveFragment(2));
        mTabFragList.add(new ComprehensiveFragment(3));
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mLayoutAdapter = new RankingTabLayoutAdapter(this, getSupportFragmentManager(), mTabFragList, tabTitles);
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
                            getSupportFragmentManager().beginTransaction()
                                    .show(mTabFragList.get(i))
                                    .commit();
                        }else {
                            getSupportFragmentManager().beginTransaction()
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
                finish();
            default:
                break;
        }

    }
}
