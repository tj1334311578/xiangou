package com.example.administrator.xiangou.mine.mystore.couponmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： tj on 2017/5/27.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class CouponManagerFragment extends MVPBaseFragment<CouponManagerContract.View,CouponManagerPresenter> implements CouponManagerContract.View {
    private RecyclerView recycle;
    int tag;
    public CouponManagerFragment(int tag){
        this.tag=tag;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container, R.layout.recycleview_style_graybg);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void initView() {
        List<CouponManageBean> data=new ArrayList<>();
        data.add(new CouponManageBean("订单满99元可用","满99可用","开始时间：2017-06-23","结束时间：2017-06-23","剩余张数：220张","5元"));
        data.add(new CouponManageBean("订单满199元可用","满199可用","开始时间：2017-06-23","结束时间：2017-06-23","剩余张数：220张","10元"));
        data.add(new CouponManageBean("订单满299元可用","满299可用","开始时间：2017-06-23","结束时间：2017-06-23","剩余张数：220张","15元"));

        recycle=findContentView(R.id.recycleview_style_recycle);
        recycle.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recycle.setAdapter(new CouponManagerAdapter(getContext(),data,tag));
        recycle.addItemDecoration(new ItemIntervalDecoration(0,-5,0,0));
    }
}
