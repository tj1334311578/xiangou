package com.example.administrator.xiangou.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;

import java.util.List;


/**
 * Created by zhouzongyao on 2017/2/7.
 */

public class CustomTabLayoutAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragment;
    private String[] tabs ;
    private Context mContext;
    private TextView mTabTv;
    private ImageView mTabIv;
    private View mTabV;

    public CustomTabLayoutAdapter(Context mContext, FragmentManager fm, List<Fragment> list_fragment, String[] tabs) {
        super(fm);
        this.mContext = mContext;
        this.list_fragment =list_fragment;
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }

    //用自定义的布局换掉tablayout的tab布局
    public View getTabItemView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tab_item_nearby,null);
        mTabTv = (TextView) view.findViewById(R.id.tv_title_tab);
        mTabTv.setText(tabs[position]);
        mTabV = view.findViewById(R.id.title_tab_line);
        mTabV.setTag(position);
//        mTabTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        if (position!=0){
            mTabIv = (ImageView) view.findViewById(R.id.iv_title_tab);
            mTabIv.setVisibility(View.GONE);
        }
        return view;
    }
}


