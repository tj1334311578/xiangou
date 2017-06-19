package com.example.administrator.xiangou.mine.mystore.couponmanager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.goods_sort.RankingTabLayoutAdapter;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CouponManagerActivity extends MVPBaseActivity<CouponManagerContract.View, CouponManagerPresenter> implements CouponManagerContract.View {
    private ImageView backBtn;
    private TextView addCouponBtn;
    private TabLayout mtablayout;
    private ViewPager mviewpager;
    private List<Fragment> mTabFragList;
    private RankingTabLayoutAdapter mLayoutAdapter;
    private String[] tabTitles;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_center_coupon);
        initView();
    }
    //找控件
    private void initView() {
        backBtn=findContentView(R.id.seller_center_coupon_backBtn);
        addCouponBtn=findContentView(R.id.seller_center_coupon_add);
        mtablayout=findContentView(R.id.seller_center_coupon_tab,false);
        mviewpager=findContentView(R.id.seller_center_coupon_recycle);
        initTabFragViews();
    }
    //tablayout与fragment关联
    private void initTabFragViews() {
        tabTitles = new String[]{"未开始(2)", "进行中(2)", "已失效(2)"};
        mTabFragList = new ArrayList<>();
        Bundle b=new Bundle();
        b.putInt("tag",1);
        CouponManagerFragment fragment1=new CouponManagerFragment();
        fragment1.setArguments(b);
        mTabFragList.add(fragment1);

        b.putInt("tag",2);
        CouponManagerFragment fragment2=new CouponManagerFragment();
        fragment2.setArguments(b);
        mTabFragList.add(fragment2);

        b.putInt("tag",3);
        CouponManagerFragment fragment3=new CouponManagerFragment();
        fragment3.setArguments(b);
        mTabFragList.add(fragment3);

        mtablayout.setTabMode(TabLayout.MODE_FIXED);
        mLayoutAdapter = new RankingTabLayoutAdapter(this, getSupportFragmentManager(), mTabFragList, tabTitles);
        mviewpager.setAdapter(mLayoutAdapter);
        mtablayout.setupWithViewPager(mviewpager);
        for (int i = 0; i < tabTitles.length; i++) {
            final TabLayout.Tab tab = mtablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(mLayoutAdapter.getTabItemView(i));
                if (tab.getCustomView() != null) {
                    final View tabView = (View) tab.getCustomView().getParent();
                    tabView.setTag("tab" + i);
                    tabView.setOnClickListener(this);
                }
            }
        }
        mviewpager.setCurrentItem(0);
        mviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mviewpager.setCurrentItem(position);
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
            case R.id.seller_center_coupon_backBtn://返回按钮
                finish();
                break;
            case R.id.seller_center_coupon_add://添加按钮
                startNewUI(NewCreateCouponActivity.class);
                break;
        }
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void dataToView(CouponBean couponDatas) {

    }

    @Override
    public void addCouponSuccess() {

    }
}
