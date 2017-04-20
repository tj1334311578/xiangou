package com.example.administrator.xiangou.goods_details.storehome.storehome;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.goods_details.storehome.storehome.homestore.HomeStoreFragment;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StoreHomeFragment extends MVPBaseFragment<StoreHomeContract.View, StoreHomePresenter> implements StoreHomeContract.View {
    @BindView(R.id.tabs_goodsstorehome_tab)
     TabLayout mTabLayout;
    @BindView(R.id.tabs_goodsstorehome_viewpager)
     ViewPager mViewPager;

    private List<Fragment> mTabFragList;
    private String[] tabTitles;
    private StoreHomeTabLayoutAdapter mLayoutAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,R.layout.goods_storehome);
    }

    @Override
    public void onClick(View v) {

    }

    private void initTabFragViews() {
        tabTitles=new String[]{"店铺首页","所有宝贝"};
        mTabFragList =new ArrayList<>();
        mTabFragList.add(new HomeStoreFragment());
        mTabFragList.add(new HomeStoreFragment());
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

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void initView() {
        ButterKnife.bind(this,mContextView);
        initTabFragViews();
    }
}
