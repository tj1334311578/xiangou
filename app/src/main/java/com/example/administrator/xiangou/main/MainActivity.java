package com.example.administrator.xiangou.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.cart.CartFragment;
import com.example.administrator.xiangou.home.HomeFragment;
import com.example.administrator.xiangou.mine.MineFragment;
import com.example.administrator.xiangou.nearby.NearbyFragment;
import com.example.administrator.xiangou.tool.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private FragmentTabHost mFragmentTabHost;
    private LayoutInflater mInflater;
    private List<Tab> mTabs;
    private TabHost.TabSpec mTabSpec;
    private int mCurrentNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTabHost();
    }

    //初始化底栏的tabs
    private void initTabHost() {
        Tab home = new Tab(R.string.home,R.drawable.icon_home_select,HomeFragment.class);
        Tab nearby = new Tab(R.string.nearby,R.drawable.icon_nearby_select,NearbyFragment.class);
        Tab cart = new Tab(R.string.shopping_cart,R.drawable.icon_cart_select,CartFragment.class);
        Tab mine = new Tab(R.string.mine,R.drawable.icon_mine_select,MineFragment.class);
        mTabs = new ArrayList<>();
        mTabs.add(home);
        mTabs.add(nearby);
        mTabs.add(cart);
        mTabs.add(mine);

        mInflater = LayoutInflater.from(this);
        mFragmentTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        mFragmentTabHost.setup(this,getSupportFragmentManager(),R.id.fragsgroup);
        for (Tab tab:mTabs
                ) {
            mTabSpec = mFragmentTabHost.newTabSpec(getString(tab.getTitle()));
            mTabSpec.setIndicator(buildIndicator(tab));
            mFragmentTabHost.addTab(mTabSpec,tab.getFrag(),null);
            mCurrentNum++;
            if (tab.getTitle()==R.string.mine){
                findViewById(R.id.divider_main).setVisibility(View.GONE);
            }
        }
        //去掉分隔线
        mFragmentTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        //开始默认选中第一个tab
        mFragmentTabHost.setCurrentTab(0);
    }

    //初始化底栏的tab
    private View buildIndicator(Tab tab) {
        View view =mInflater.inflate(R.layout.tab_indicator,null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab_indicator);
        TextView text = (TextView) view.findViewById(R.id.text_tab_indicator);

        img.setImageResource(tab.getIcon());
        text.setText(tab.getTitle());
        return view;
    }
}
