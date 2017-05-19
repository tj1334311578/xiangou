package com.example.administrator.xiangou.goods_sort.storehome.storehome;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.example.administrator.xiangou.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */
public class StoreHomeTabLayoutAdapter extends FragmentPagerAdapter{
    private String[] tabs ;
    private Context mContext;
    private List<Fragment> list_fragment;
    private TextView mTabTv;
    public StoreHomeTabLayoutAdapter(Context mContext, FragmentManager fm, List<Fragment> list_fragment,String[] tabs) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.tab_item_ranking,null);
        mTabTv = (TextView) view.findViewById(R.id.tv_title_tab);
        mTabTv.setText(tabs[position]);
        //给TextView画下划线
        mTabTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        return view;
    }
}
