package com.example.administrator.xiangou.mine.myorder.myorder.waitdelivery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WaitDeliveryFragment extends MVPBaseFragment<WaitDeliveryContract.View, WaitDeliveryPresenter> implements WaitDeliveryContract.View {
    private RecyclerView mWaitDeliveryRecycle;
    private List<DeliveryBean> lists;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private int position=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("onCreateView", "onCreateView: +++++++++++++++++" );
        return setContextView(inflater,container, R.layout.goods_ranking_gride);
    }
    @Override
    public void onClick(View v) {

    }


    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("onCreate", "onCreate: "+"----------------");
        super.onCreate(savedInstanceState);
        //可进行网络数据获取
        lists=new ArrayList<>();
        List<DeliveryItemBean> items=new ArrayList<>();
        items.add(new DeliveryItemBean(R.mipmap.girl_h,"修身纠纷和UIiOS藕粉发顺丰判罚 南非农夫三金佛山念佛是积分搜哦积分是你佛","S","白色+灰色",179.10,100.00,1));
        items.add(new DeliveryItemBean(R.mipmap.girl_h,"修身纠纷和UIiOS藕粉发顺丰判罚 南非农夫三金佛山念佛是积分搜哦积分是你佛","S","白色+灰色",179.10,100.00,2));
        items.add(new DeliveryItemBean(R.mipmap.girl_h,"修身纠纷和UIiOS藕粉发顺丰判罚 南非农夫三金佛山念佛是积分搜哦积分是你佛","S","白色+灰色",179.10,100.00,1));
        lists.add(new DeliveryBean("呆呆画质品牌店","交易成功",items,2,171.01,10.00));
        lists.add(new DeliveryBean("呆呆画质品牌店","等待付款",items,2,171.01,10.00));
        lists.add(new DeliveryBean("呆呆画质品牌店","买家已付款",items,2,171.01,10.00));
        lists.add(new DeliveryBean("呆呆画质品牌店","卖家已发货",items,2,171.01,10.00));
        lists.add(new DeliveryBean("平爱书品店","交易成功",items,2,171.01,10.00));
        lists.add(new DeliveryBean("呆呆画质品牌店","卖家已发货",items,2,171.01,10.00));
        lists.add(new DeliveryBean("呆呆画质品牌店","等待付款",items,2,171.01,10.00));
    }

    @Override
    public void initView() {
        mWaitDeliveryRecycle= (RecyclerView) mContextView.findViewById(R.id.goods_ranking_recycle);
        mWaitDeliveryRecycle.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mWaitDeliveryRecycle.addItemDecoration(new ItemIntervalDecoration(0,0,10));
        mWaitDeliveryAdapter adapter=new mWaitDeliveryAdapter(getContext(), lists,position);
        mWaitDeliveryRecycle.setAdapter(adapter);
        adapter.setOnitemClickListener(new mWaitDeliveryAdapter.ViewClickListener() {
            @Override
            public void itemviewclick(View view, int position) {
                Log.e("itemviewclick", "itemviewclick: "+lists.get(position).toString()+lists.size()+"\n\n"+position);
                showToast(((TextView)view).getText().toString()+position);
            }
        });

    }
}
