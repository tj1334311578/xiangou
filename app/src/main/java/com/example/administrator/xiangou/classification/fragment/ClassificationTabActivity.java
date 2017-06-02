package com.example.administrator.xiangou.classification.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.classification.bean.FirstLevelBean;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.tool.BaseFragmentActivity;
import com.example.administrator.xiangou.tool.ContextUtils;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

/**
 * Created by Administrator on 2017/4/17.
 */

public class ClassificationTabActivity extends BaseFragmentActivity {
    private TextView[] tvList;
    private View[] lines;
    private View[] views;
    private LayoutInflater inflater;
    private ScrollView scrollView;
    private ViewPager viewpager;
    private int currentItem = 0;
    private ShopAdapter shopAdapter;
    private Fragment fragment;
    private List<String> lists;
    private static List<Integer> lists_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_classification_fragment);
        scrollView = (ScrollView) findViewById(R.id.tools_scrlllview);
        shopAdapter = new ShopAdapter(getSupportFragmentManager());
        inflater = LayoutInflater.from(this);
        findContentView(R.id.goods_classification_fragment_return,true);
        callClassification();
    }

    private void callClassification() {
        addSubscription(mApiService.callClassification(0),
                new BaseSubscriber<FirstLevelBean>(this.getBaseContext()) {
                    @Override
                    public void onNext(FirstLevelBean classificationBean) {
                            if (classificationBean.getState().getCode()==200){
                                showToolsView(classificationBean);
                                initPager();
                            }
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("onError", "onError: "+e.toString() );
                    }
                });
    }

    /**
     * 动态生成显示items中的textview
     * @param data
     */
    private void showToolsView(FirstLevelBean data) {
        lists = new ArrayList<>();
        lists_id=new ArrayList<>();
        for (int j = 0; j <data.getData().getAll_cate().size() ; j++) {
            if (j==0&&!data.getData().getAll_cate().get(0).equals("推荐")){
                lists.add("推荐");
            }
            lists.add(data.getData().getAll_cate().get(j).getName());
            lists_id.add(data.getData().getAll_cate().get(j).getCat_id());
        }
        LinearLayout toolsLayout = (LinearLayout) findViewById(R.id.tools);
        tvList = new TextView[lists.size()];
        views = new View[lists.size()];
        lines=new View[lists.size()];
        for (int i = 0; i < lists.size(); i++) {
            View view = inflater.inflate(R.layout.item_list_layout, null);
            view.setId(i);
            view.setOnClickListener(toolsItemListener);
            TextView textView = (TextView) view.findViewById(R.id.text);
            View line=  view.findViewById(R.id.left_id);
            textView.setText(lists.get(i));
            toolsLayout.addView(view);
            lines[i]=line;
            tvList[i] = textView;
            views[i] = view;
        }
        changeTextColor(0);
    }

    private View.OnClickListener toolsItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewpager.setCurrentItem(v.getId());
        }
    };

    /**
     * initPager<br/>
     * 初始化ViewPager控件相关内容
     */
    private void initPager() {
        viewpager = (ViewPager) findViewById(R.id.goods_pager);
        viewpager.setAdapter(shopAdapter);
        viewpager.setOnPageChangeListener(onPageChangeListener);
    }

    /**
     * OnPageChangeListener<br/>
     * 监听ViewPager选项卡变化事的事件
     */
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int arg0) {
            if (viewpager.getCurrentItem() != arg0)
                viewpager.setCurrentItem(arg0);
            if (currentItem != arg0) {
                changeTextColor(arg0);
                changeTextLocation(arg0);
            }
            currentItem = arg0;
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goods_classification_fragment_return:
                finish();
                break;
            default:
                break;
        }
    }


    /**
     * ViewPager 加载选项卡
     *
     * @author Administrator
     *
     */
    private class ShopAdapter extends FragmentPagerAdapter {
        public ShopAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int index) {
            if (index==0){
            fragment=new ClassificationFragment1();
        }else{
            fragment = new ClassificationFragment(lists_id.get(index-1));
        }
            Bundle bundle = new Bundle();
            bundle.putInt("index", index);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return lists.size();
        }
    }

    /**
     * 改变textView的颜色
     *
     * @param id
     */
    private void changeTextColor(int id) {
        for (int i = 0; i < tvList.length; i++) {
            if (i != id) {
                tvList[i].setBackgroundColor(0x00000000);
                tvList[i].setTextColor(0xFF000000);
                lines[i].setVisibility(GONE);
            }
        }
        tvList[id].setBackgroundColor(getResources().getColor(R.color.white));
        tvList[id].setTextColor(getResources().getColor(R.color.textcolor_pink));
        lines[id].setVisibility(View.VISIBLE);
    }

    /**
     * 改变栏目位置
     *
     * @param clickPosition
     */
    private void changeTextLocation(int clickPosition) {
        int x = (views[clickPosition].getTop());
        DisplayMetrics dm=new DisplayMetrics();
        ((WindowManager)this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
        //屏幕高度
        int y= ContextUtils.px2dp(dm.heightPixels) ;
        //控件高
        int height=views[clickPosition].getHeight();
        int toY=x-y/2-height;
        scrollView.smoothScrollTo(0, toY);
    }
}
