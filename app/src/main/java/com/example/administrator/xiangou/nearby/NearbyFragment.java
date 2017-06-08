package com.example.administrator.xiangou.nearby;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.classification.fragment.ClassificationTabActivity;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;
import com.example.administrator.xiangou.nearby.nearbygoods.NearbyGoodsFragment;
import com.example.administrator.xiangou.nearby.nearbypreferential.NearbyPreferentialFragment;
import com.example.administrator.xiangou.nearby.nearbystore.NearbyStoreFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NearbyFragment extends MVPBaseFragment<NearbyContract.View, NearbyPresenter>
        implements NearbyContract.View{
    @BindView(R.id.classify_nearby_iv)
    ImageView mClassifyIv;
    @BindView(R.id.news_num_nearby_tv)
    TextView mNewsCountTv;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private NearbyTabLayoutAdapter mLayoutAdapter;
    private List<Fragment> mTabFragList;
    private String[]tabTitles;
    private List<TextView> mPpwTvs;
    private PopupWindow mPopupWindow;
    private int nearyDistance;//附近距离
    private int currentNum,lastPpwTvPos;//当前页号

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mContextView!=null){
            ViewGroup parent = (ViewGroup) mContextView.getParent();
            if (parent !=null){
                parent.removeView(mContextView);
            }
            return mContextView;
        }
        return setContextView(inflater,container,R.layout.fragment_nearby);
    }

    @Override
    public void initView() {
        ButterKnife.bind(this,mContextView);
        mClassifyIv.setOnClickListener(this);
        mNewsCountTv.setOnClickListener(this);
        initTabFragViews(mContextView);
    }

    private void initTabFragViews(View view) {
        tabTitles = new String[]{"附近商品","附近商店","附近优惠"};
        mTabFragList = new ArrayList<>();
        mTabFragList.add(new NearbyGoodsFragment());
        mTabFragList.add(new NearbyStoreFragment());
        mTabFragList.add(new NearbyPreferentialFragment());
        mViewPager = (ViewPager) view.findViewById(R.id.frag_nearby_vp);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabs_nearby_tl);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mLayoutAdapter = new NearbyTabLayoutAdapter(getContext(),getChildFragmentManager(),mTabFragList,tabTitles);
        mViewPager.setAdapter(mLayoutAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        //将自定义的tab布局加载到tablayout上
        for (int i=0; i<tabTitles.length; i++){
            final TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab!=null){
                tab.setCustomView(mLayoutAdapter.getTabItemView(i));
                if (tab.getCustomView() !=null) {
                    final View tabView = (View) tab.getCustomView().getParent();
                    tabView.setTag("tab"+i);
                    tabView.setOnClickListener(this);
                }
            }
        }

        mTabLayout.getTabAt(currentNum).getCustomView().findViewById(R.id.title_tab_line).setVisibility(View.VISIBLE);
        mViewPager.setCurrentItem(currentNum);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                currentNum = position;
                mViewPager.setCurrentItem(position);
                //动态设置tab的下划线
                for (int i = 0; i < tabTitles.length; i++) {
                    View tabline = mTabLayout.getTabAt(i).getCustomView().findViewById(R.id.title_tab_line);
                    if (((int)tabline.getTag()) == position) {
                        tabline.setVisibility(View.VISIBLE);
                    }else {
                        tabline.setVisibility(View.INVISIBLE);
                    }
                }
                //fragment的相应切换
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

        switch (v.getId()) {
            case R.id.classify_nearby_iv:
                toastShow("看分类了");
                startNewUI(ClassificationTabActivity.class);
                break;
            case R.id.news_num_nearby_tv:
                toastShow("clicked news!");
                break;
            default:
                switch ((String) v.getTag()) {
                    case "tab0":
                        if (mViewPager.getCurrentItem()==0) {
                            showPopupwindow(v);
                        }
                        Toast.makeText(getContext(), "商品", Toast.LENGTH_SHORT).show();
                        break;
                    case "tab1":
                        Toast.makeText(getContext(), "Store", Toast.LENGTH_SHORT).show();
                        break;
                    case "tab2":
                        Toast.makeText(getContext(), "mon mon", Toast.LENGTH_SHORT).show();
                        break;
                    case "ppw0":
                        nearyDistance = 200000;
                        // TODO: 2017/3/13
                        setPopupWindowCurrentItem(v,nearyDistance);

                        break;
                    case "ppw1":
                        nearyDistance = 1000;
                        setPopupWindowCurrentItem(v,nearyDistance);
                        break;
                    case "ppw2":
                        nearyDistance = 2000;
                        setPopupWindowCurrentItem(v,nearyDistance);
                        break;
                    case "ppw3":
                        nearyDistance = 3000;
                        setPopupWindowCurrentItem(v,nearyDistance);
                        break;
                    case "ppw4":
                        nearyDistance = 4000;
                        setPopupWindowCurrentItem(v,nearyDistance);
                        break;
                    case "ppw5":
                        nearyDistance = 5000;
                        setPopupWindowCurrentItem(v,nearyDistance);
                        break;
                }
        }
    }
    private void setPopupWindowCurrentItem(View v, int nearyDistance){
        for (int i = 0; i < mPpwTvs.size(); i++) {
            if (mPpwTvs.get(i)==v) {
                ((NearbyGoodsFragment) mTabFragList.get(0)).setNearyDistance(nearyDistance);
                Toast.makeText(getContext(), "mon true" + v.getId(), Toast.LENGTH_SHORT).show();
                lastPpwTvPos = i;
            }
        }
        mPopupWindow.dismiss();
    }
    private void showPopupwindow(View view) {
        View mContentView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_nearby_goods,null);
        mPpwTvs = new ArrayList<>();
        mPpwTvs.add((TextView) mContentView.findViewById(R.id.all_ppw));
        mPpwTvs.add((TextView) mContentView.findViewById(R.id.one_ppw));
        mPpwTvs.add((TextView) mContentView.findViewById(R.id.two_ppw));
        mPpwTvs.add((TextView) mContentView.findViewById(R.id.three_ppw));
        mPpwTvs.add((TextView) mContentView.findViewById(R.id.four_ppw));
        mPpwTvs.add((TextView) mContentView.findViewById(R.id.five_ppw));
        for (int i = 0; i< mPpwTvs.size(); i++) {
            mPpwTvs.get(i).setTag("ppw"+i);
            mPpwTvs.get(i).setOnClickListener(this);
        }
        mPpwTvs.get(lastPpwTvPos).setSelected(true);
        mPopupWindow = new PopupWindow(mContentView, view.getWidth(), ViewPager.LayoutParams.WRAP_CONTENT,true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.mipmap.popupwindow_bg));
        mPopupWindow.showAsDropDown(view);
    }

    @Override
    public void sendFialRequest(String message) {
        showToast(message);
    }
}
