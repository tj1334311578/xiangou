package com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsdetailscomment.goodsdetailscommentitem;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.xiangou.R;

import java.util.List;

/**
 * 作者： tj on 2017/5/23.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class GoodsDetailsCommentPagAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;
    private String [] tabs;
    private Context mContext;
    private List<Integer> values;
    private TextView mTabTv,mTabValue;
    private View mTabV;

    public GoodsDetailsCommentPagAdapter(Context context,FragmentManager fm,List<Fragment> fts,String[] tabs,List<Integer> values) {
        super(fm);
        this.fragments=fts;
        this.tabs=tabs;
        this.mContext=context;
        this.values=values;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    //用自定义的布局换掉tablayout的tab布局
    public View getTabItemView(int position){
        View v= LayoutInflater.from(mContext).inflate(R.layout.tab_item_comment,null);
        mTabTv= (TextView) v.findViewById(R.id.comment_title_tab);
        mTabTv.setText(tabs[position]);
        mTabValue= (TextView) v.findViewById(R.id.comment_title_value);
        mTabValue.setText(values.get(position)+"");
        mTabV = v.findViewById(R.id.comment_tab_line);
        mTabV.setTag(position);
        return v;
    }
}
