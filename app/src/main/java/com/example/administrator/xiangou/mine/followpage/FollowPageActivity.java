package com.example.administrator.xiangou.mine.followpage;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.CustomTabLayoutAdapter;
import com.example.administrator.xiangou.mine.followpage.followgoods.FollowGoodsFragment;
import com.example.administrator.xiangou.mine.followpage.followstore.FollowStoreFragment;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class FollowPageActivity extends MVPBaseActivity<FollowPageContract.View, FollowPagePresenter>
        implements FollowPageContract.View{
//        ,FollowStoreFragment.CallStoresFollowChanged,FollowGoodsFragment.CallGoodsFollowChanged{

    private int mCurrentPosition =0;
    private TextView TitleTv,SaveTv;
    private Button cancelFollowBtn;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private CustomTabLayoutAdapter mLayoutAdapter;
    private List<Fragment> mTabFragList;
    private String[] tabTitles;
    private boolean[] isEdit ;
    private FollowGoodsFragment mGoodsFragment;
    private FollowStoreFragment mStoreFragment;

    public interface CallEditGoodsFoolow{
        void notifyEditGoodsFoolow(boolean toEdit);
        void notifyRemoveGoods();
    }
    public CallEditGoodsFoolow mCallEditGoodsFoolow;
    public interface CallEditStoresFoolow{
        void notifyEditStoresFoolow(boolean toEdit);
        void notifyRemoveStore();
    }
    public CallEditStoresFoolow mCallEditStoresFoolow;

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
        cancelFollowBtn = findContentView(R.id.cancel_follow_btn);
        TitleTv.setText("我的关注");
        SaveTv.setText("编辑");
        SaveTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        mTabLayout=findContentView(R.id.followpage_tab,false);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager=findContentView(R.id.followpage_viewpager,false);

        tabTitles = new String[]{"商品", "店铺"};
        isEdit = new boolean[]{false,false};
        mTabFragList = new ArrayList<>();
        mGoodsFragment = new FollowGoodsFragment();
        mStoreFragment = new FollowStoreFragment();
        initTabFragViews();
    }

    private void initTabFragViews() {
        mTabFragList.add(mGoodsFragment);
        mTabFragList.add(mStoreFragment);
        mLayoutAdapter = new CustomTabLayoutAdapter(getContext(), getSupportFragmentManager(), mTabFragList, tabTitles);
        mViewPager.setAdapter(mLayoutAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < tabTitles.length; i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(mLayoutAdapter.getTabItemView(i,false));
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
                    cancelFollowBtn.setVisibility(View.VISIBLE);
                    cancelFollowBtn.setClickable(true);
                }else {
                    SaveTv.setText("编辑");
                    cancelFollowBtn.setVisibility(View.GONE);
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
        showToast("error:"+message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_head_back:
                finish();
                break;
            case R.id.setting_head_right:
                switch (mCurrentPosition){
                    case 0:
                        if (mGoodsFragment.isHasData()){
                            changeEidtStatus();
                        }else {
                            showToast("数据加载中请稍等！");
                        }
                        break;
                    case 1:
                        if (mStoreFragment.isHasData()){
                            changeEidtStatus();
                        }else {
                            showToast("数据加载中请稍等！");
                        }
                        break;
                }
                break;
            case R.id.cancel_follow_btn:
                switch (mCurrentPosition){
                    case 0:
                        String goodsIds = mGoodsFragment.getGoodsIds();
                        if (goodsIds!=null)
                            mPresenter.cancelCollectGoodsApi(1,goodsIds);
                        break;
                    case 1:
                        String storeIds = mStoreFragment.getStoreIds();
                        if (storeIds!=null)
                            mPresenter.cancelCollectStoresApi(1,storeIds);
                        break;
                }
                break;
        }
    }

    private void changeEidtStatus(){
        isEdit[mCurrentPosition] = !isEdit[mCurrentPosition];
        if (isEdit[mCurrentPosition]){
            SaveTv.setText("完成");
            cancelFollowBtn.setVisibility(View.VISIBLE);
            cancelFollowBtn.setClickable(true);
        }else {
            SaveTv.setText("编辑");
            cancelFollowBtn.setVisibility(View.GONE);
        }
        switch (mCurrentPosition){
            case 0:
                if (mCallEditGoodsFoolow!=null)
                    mCallEditGoodsFoolow.notifyEditGoodsFoolow(isEdit[mCurrentPosition]);
                break;
            case 1:
                if (mCallEditStoresFoolow!=null)
                    mCallEditStoresFoolow.notifyEditStoresFoolow(isEdit[mCurrentPosition]);
                break;
        }
    }

    @Override
    public void cancelGoodsSuccess(String msg) {
    mCallEditGoodsFoolow.notifyRemoveGoods();
        showToast("cancelGoodsSuccess:"+msg);
    }

    @Override
    public void cancelStoresSuccess(String msg) {
        mCallEditStoresFoolow.notifyRemoveStore();
        showToast("cancelStoresSuccess:"+msg);
    }

    /**
     * the call of activity for fragment
     * @param fragment who need to implements the callback
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
