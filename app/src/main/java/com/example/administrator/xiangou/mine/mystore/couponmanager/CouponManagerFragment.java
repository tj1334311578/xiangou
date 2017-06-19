package com.example.administrator.xiangou.mine.mystore.couponmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
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
    public CouponManagerFragment(){
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
        recycle=findContentView(R.id.recycleview_style_recycle);
        switch (tag){
            case 1:
                mPresenter.callFindCoupon(1,null);
                break;
            case 2:
                mPresenter.callFindCoupon(1,"working");
                break;
            case 3:
                mPresenter.callFindCoupon(1,"lose");
                break;
        }
    }

    @Override
    public void dataToView(CouponBean couponDatas) {
        List<CouponBean.CouponsBean> data = new ArrayList<>();
        if (couponDatas.getData().size()==0) {
            data.add(new CouponBean.CouponsBean(1,220,100,"10.00","100.00","2016-06-01","2016-07-2"));
            data.add(new CouponBean.CouponsBean(1,220,100,"10.00","100.00","2016-06-01","2016-07-2"));
            data.add(new CouponBean.CouponsBean(1,220,100,"10.00","100.00","2016-06-01","2016-07-2"));
        }else{
            Log.e("ysss", "dataToView: "+couponDatas.toString() );
            data=couponDatas.getData();
        }
        final CouponManagerAdapter adapter=new CouponManagerAdapter(getContext(),data,tag);
        recycle.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recycle.setAdapter(adapter);
        recycle.addItemDecoration(new ItemIntervalDecoration(0,-5,0,0));
        adapter.setOnLongClickListener(new CouponManagerAdapter.OnLongClickListener() {
            @Override
            public void setOnLongClickListener(View view, int position) {
                showToast("position"+position);
            }
        });

    }

    @Override
    public void addCouponSuccess() {

    }
}
