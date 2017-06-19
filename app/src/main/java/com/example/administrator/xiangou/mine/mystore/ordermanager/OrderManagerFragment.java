package com.example.administrator.xiangou.mine.mystore.ordermanager;


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
import com.example.administrator.xiangou.mine.myorder.myorder.waitdelivery.WaitDeliveryFragment;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class OrderManagerFragment extends MVPBaseFragment<OrderManagerContract.View, OrderManagerPresenter> implements OrderManagerContract.View {
    private int position=0;
    private TextView TitleTv;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    private RankingTabLayoutAdapter mLayoutAdapter;
    private List<Fragment> mTabFragList;
    private String[] tabTitles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,R.layout.mystore_ordermanager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mystore_store_headback:
                getActivity().finish();
                break;
            case R.id.mystore_head_home://大返回
                break;
            case R.id.mystore_head_message://消息按钮
                break;
        }
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void initView() {
        findContentView(R.id.mystore_store_headback);//返回
        findContentView(R.id.mystore_head_home);//大返回  不晓得跳哪去
        findContentView(R.id.mystore_head_message);//消息按钮
        TitleTv=findContentView(R.id.mystore_head_title_tv,false);
        TitleTv.setText("订单管理");
        mTabLayout=findContentView(R.id.mystore_ordermanager_tab,false);
        mViewPager=findContentView(R.id.maystore_ordermanager_pager,false);
        initTabFragViews();

    }

    private void initTabFragViews() {
//        position=Integer.parseInt(getActivity().getIntent().getStringExtra("position"));
        tabTitles = new String[]{"待发货", "待付款", "已发货", "更多状态"};
        mTabFragList = new ArrayList<>();
        for (int i=0;i<tabTitles.length;i++){
            WaitDeliveryFragment ft=new WaitDeliveryFragment();
            ft.setPosition(i);
            mTabFragList.add(ft);
        }
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
