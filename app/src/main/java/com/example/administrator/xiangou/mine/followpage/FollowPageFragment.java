package com.example.administrator.xiangou.mine.followpage;


import android.graphics.Paint;
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
import com.example.administrator.xiangou.goods_sort.RankingTabLayoutAdapter;
import com.example.administrator.xiangou.mine.followpage.followgoods.FollowGoodsFragment;
import com.example.administrator.xiangou.mine.followpage.followstore.FollowStoreFragment;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FollowPageFragment extends MVPBaseFragment<FollowPageContract.View, FollowPagePresenter> implements FollowPageContract.View {
    private int position=0;
    private TextView TitleTv,SaveTv;
//    @BindView(R.id.followpage_tab)
    TabLayout mTabLayout;
    ImageView backBtn;
//    @BindView(R.id.followpage_viewpager)
    ViewPager mViewPager;
    private RankingTabLayoutAdapter mLayoutAdapter;
    private List<Fragment> mTabFragList;
    private String[] tabTitles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,R.layout.followpage);
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void initView() {
        mTabLayout=findContentView(R.id.followpage_tab,false);
        mViewPager=findContentView(R.id.followpage_viewpager,false);
        backBtn= (ImageView) mContextView.findViewById(R.id.followpage_head).findViewById(R.id.setting_head_back);
        TitleTv= (TextView) mContextView.findViewById(R.id.followpage_head).findViewById(R.id.setting_head_center);
        SaveTv= (TextView) mContextView.findViewById(R.id.followpage_head).findViewById(R.id.setting_head_right);
        TitleTv.setText("我的关注");
        SaveTv.setText("编辑");
        SaveTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
//        ButterKnife.bind(this,mContextView);
        backBtn.setOnClickListener(this);
        initTabFragViews();
    }

    private void initTabFragViews() {
        position=0;
        tabTitles = new String[]{"商品", "店铺"};
        mTabFragList = new ArrayList<>();
        mTabFragList.add(new FollowGoodsFragment());
        mTabFragList.add(new FollowStoreFragment());

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

    @Override
    public void onClick(View v) {
        if (v==backBtn){
            getActivity().finish();
        }
    }
}
