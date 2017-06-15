package com.example.administrator.xiangou.mine.followpage;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.CustomTabLayoutAdapter;
import com.example.administrator.xiangou.mine.followpage.followgoods.FollowGoodsFragment;
import com.example.administrator.xiangou.mine.followpage.followstore.FollowStoreFragment;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class FollowPageActivity extends MVPBaseActivity<FollowPageContract.View, FollowPagePresenter>
        implements FollowPageContract.View
        ,FollowStoreFragment.CallStoresFollowChanged,FollowGoodsFragment.CallGoodsFollowChanged{

    private int mCurrentPosition =0;
    private TextView TitleTv,SaveTv;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private CustomTabLayoutAdapter mLayoutAdapter;
    private List<Fragment> mTabFragList;
    private String[] tabTitles;
    private boolean[] isEdit ;

    @Override
    public void notifyStoresChaged(int[] goodsIds) {

    }

    @Override
    public void notifyEditStoresFoolow(boolean toEdit) {

    }

    @Override
    public void notifyGoodsChaged(int[] goodsIds) {

    }

    @Override
    public void notifyEditGoodsFoolow(boolean toEdit) {

    }


    public interface CallEditGoodsFoolow{
        void notifyEditGoodsFoolow(boolean toEdit);
    }
    private CallEditGoodsFoolow mCallEditGoodsFoolow;
    public interface CallEditStoresFoolow{
        void notifyEditStoresFoolow(boolean toEdit);
    }
    private CallEditStoresFoolow mCallEditStoresFoolow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.followpage);
        initView();
    }

    public void initView() {

        TitleTv= findContentView(R.id.setting_head_center,false);
        SaveTv= findContentView(R.id.setting_head_right);
        findContentView(R.id.setting_head_back);
        findContentView(R.id.cancel_follow_btn);
        TitleTv.setText("我的关注");
        SaveTv.setText("编辑");
        SaveTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        mTabLayout=findContentView(R.id.followpage_tab,false);
        mViewPager=findContentView(R.id.followpage_viewpager,false);
        initTabFragViews();
    }

    private void initTabFragViews() {
        tabTitles = new String[]{"商品", "店铺"};
        isEdit = new boolean[]{false,false};

        mTabFragList = new ArrayList<>();
        FollowGoodsFragment goodsFragment = new FollowGoodsFragment();
//        goodsFragment.setCallGoodsFollowChanged(new FollowGoodsFragment.CallGoodsFollowChanged() {
//            @Override
//            public void notifyGoodsChaged(int[] goodsIds) {
//
//            }
//        });
        mTabFragList.add(goodsFragment);
        mTabFragList.add(new FollowStoreFragment());

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mLayoutAdapter = new CustomTabLayoutAdapter(getContext(), getSupportFragmentManager(), mTabFragList, tabTitles);
        mViewPager.setAdapter(mLayoutAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < tabTitles.length; i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(mLayoutAdapter.getTabItemView(i));
                if (tab.getCustomView() != null) {
                    View tabView = (View) tab.getCustomView().getParent();
                    tabView.setTag("tab" + i);
                    tabView.setOnClickListener(this);
                }
            }
        }
        mViewPager.setCurrentItem(mCurrentPosition);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);
                mCurrentPosition = position;
                //区别两个fragment对应的saveTv显示状态
                if (isEdit[mCurrentPosition]){
                    SaveTv.setText("完成");
                }else {
                    SaveTv.setText("编辑");
                }
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
            public void onPageScrollStateChanged(int state) {}
        });
    }

    @Override
    public void sendFialRequest(String message) {
        showToast(message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_head_back:
                finish();
                break;
            case R.id.setting_head_right:
                isEdit[mCurrentPosition] = !isEdit[mCurrentPosition];
                if (isEdit[mCurrentPosition]){
                    SaveTv.setText("完成");
                }else {
                    SaveTv.setText("编辑");
                }
                switch (mCurrentPosition){
                    case 0:
                        mCallEditGoodsFoolow.notifyEditGoodsFoolow(isEdit[mCurrentPosition]);
                        break;
                    case 1:
                        mCallEditStoresFoolow.notifyEditStoresFoolow(isEdit[mCurrentPosition]);
                        break;
                }
                break;
            case R.id.cancel_follow_btn:
                break;
        }
    }

    /**
     * the call of activity for fragment
     * @param fragment need to implements the callback
     */
    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof FollowGoodsFragment){
            mCallEditGoodsFoolow = (CallEditGoodsFoolow) fragment;
        }else if (fragment instanceof FollowStoreFragment){
            mCallEditStoresFoolow = (CallEditStoresFoolow) fragment;
        }
        super.onAttachFragment(fragment);
    }
}
