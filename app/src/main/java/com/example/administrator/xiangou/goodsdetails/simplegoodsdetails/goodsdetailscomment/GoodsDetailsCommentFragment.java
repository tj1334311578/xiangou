package com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsdetailscomment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean.commenttempBean;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsdetailscomment.goodsdetailscommentitem.GoodsDetailsCommentItemFragment;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsdetailscomment.goodsdetailscommentitem.GoodsDetailsCommentPagAdapter;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class GoodsDetailsCommentFragment extends MVPBaseFragment<GoodsDetailsCommentContract.View, GoodsDetailsCommentPresenter> implements GoodsDetailsCommentContract.View {
    private TabLayout mTabLayout;
    private ImageView backBtn;
    private ViewPager mViewPager;
    private List<Fragment> mTabfragments;
    private String[]tabTitles;
    private List<Integer> TitleValues=new ArrayList<>();
    private RelativeLayout carts;
    private GoodsDetailsCommentPagAdapter mlayoutAdapter;
    private static int currentNum=0,page_no=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,R.layout.simple_goodsdetails_comment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goods_details_comment_left:
                getActivity().finish();
                break;
        }
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void initView() {
        mTabLayout=findContentView(R.id.goods_details_comment_tab,false);

        mViewPager=findContentView(R.id.goods_details_comment_viewpage,false);
        backBtn=findContentView(R.id.goods_details_comment_left);
        carts=findContentView(R.id.goods_details_comment_right);
        //进行网络请求
        callComment(currentNum);

        initTabFragmentViews();
    }

    //网络请求数据
    private void callComment(int currentNum) {
        List<String> condition=new ArrayList<>();
        condition.add(null);
        condition.add("nice");
        condition.add("ordinary");
        condition.add("poor");
        Log.e("goods_id", "callComment: "+ getActivity().getIntent().getIntExtra("goods_id",0));
        mPresenter.dealCallComment(getActivity().getIntent().getIntExtra("goods_id",0),page_no,condition.get(currentNum));
    }
    //网络请求数据布局搭建
//    private void initTabFragmentViews(CommentBean commentBean) {
    private void initTabFragmentViews() {
        tabTitles=new String[]{"全部评价","好评","中评","差评"};
//        TitleValues.add(commentBean.getData().getCount().getC0());
//        TitleValues.add(commentBean.getData().getCount().getC1());
//        TitleValues.add(commentBean.getData().getCount().getC2());
//        TitleValues.add(commentBean.getData().getCount().getC3());
          TitleValues.add(200);
          TitleValues.add(250);
          TitleValues.add(420);
          TitleValues.add(40);
        mTabfragments=new ArrayList<>();
        for (int i =0 ; i < tabTitles.length; i++) {
            GoodsDetailsCommentItemFragment fm=new GoodsDetailsCommentItemFragment();
            mTabfragments.add(fm);
        }
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mlayoutAdapter=new GoodsDetailsCommentPagAdapter(getContext(),getChildFragmentManager(),mTabfragments,tabTitles,TitleValues);
        mViewPager.setAdapter(mlayoutAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        //将自定义的tab布局加载到tablayout上
        for (int i = 0; i <tabTitles.length ; i++) {
            final TabLayout.Tab tab=mTabLayout.getTabAt(i);
            if (tab!=null){
                tab.setCustomView(mlayoutAdapter.getTabItemView(i));
                if (tab.getCustomView()!=null){
                   View tabView= (View) tab.getCustomView().getParent();
                    tabView.setTag("tab"+i);
                    tabView.setOnClickListener(this);
                }
            }
        }
        //设置初始状态
        mViewPager.setCurrentItem(0);
        mTabLayout.getTabAt(currentNum).getCustomView().findViewById(R.id.comment_tab_line).setVisibility(View.VISIBLE);
        ((TextView)(mTabLayout.getTabAt(currentNum).getCustomView().findViewById(R.id.comment_title_tab))).setTextColor(getResources().getColor(R.color.textcolor_pink));
        ((TextView)(mTabLayout.getTabAt(currentNum).getCustomView().findViewById(R.id.comment_title_value))).setTextColor(getResources().getColor(R.color.textcolor_pink));

        mViewPager.setCurrentItem(currentNum);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentNum=position;
                mViewPager.setCurrentItem(position);
                //动态设置tab的下划线
                for (int i = 0; i <tabTitles.length ; i++) {
                    View tabline=mTabLayout.getTabAt(i).getCustomView().findViewById(R.id.comment_tab_line);

                    //设置数据统计显示
                        TextView tabValue = (TextView) mTabLayout.getTabAt(i).getCustomView().findViewById(R.id.comment_title_value);
                        TextView tabTv = (TextView) mTabLayout.getTabAt(i).getCustomView().findViewById(R.id.comment_title_tab);

                    if ((int)(tabline.getTag())==position){
                        tabline.setVisibility(View.VISIBLE);
                        tabValue.setTextColor(getResources().getColor(R.color.textcolor_pink));
                        tabTv.setTextColor(getResources().getColor(R.color.textcolor_pink));
                    }else{
                        tabline.setVisibility(View.INVISIBLE);
                        tabValue.setTextColor(getResources().getColor(R.color.textcolor_gray));
                        tabTv.setTextColor(getResources().getColor(R.color.textcolor_gray));
                    }
                }
                //fragment的相应切换
                for (int i = 0; i <tabTitles.length ; i++) {
                    if (mTabfragments.get(i).isAdded()){
                        if (i==position){
                            getChildFragmentManager().beginTransaction()
                                .show(mTabfragments.get(i))
                                .commit();
                        }else{
                            getChildFragmentManager().beginTransaction()
                                    .hide(mTabfragments.get(i))
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
    public void sendDataBeanToView(commenttempBean commentBean) {
        Log.e("commentBean", "sendDataBeanToView: Ok\n"+commentBean.toString());
        if (commentBean.getState().getCode()==200);
//            initTabFragmentViews(commentBean);
    }
}
