package com.example.administrator.xiangou.nearby.nearbystore;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;
import com.example.administrator.xiangou.nearby.apimodel.NearbyStoreApiDataBean;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.List;

public class NearbyStoreFragment extends MVPBaseFragment<NearbyStoreContract.View, NearbyStorePresenter> implements NearbyStoreContract.View {
    private RecyclerView mNearbyStoreRv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,R.layout.fragment_nearby_store);
    }

    @Override
    public void initView() {
        mNearbyStoreRv = findContentView(R.id.store_nearby_rv,false);
        mNearbyStoreRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        mNearbyStoreRv.addItemDecoration(new ItemIntervalDecoration(0,0,0,10));
        mPresenter.dealNearbyStoreCall(null,null,0,0);
    }

    @Override
    public void sendFialRequest(String message) {
        showToast(message);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void sendStoreDataToView(List<NearbyStoreApiDataBean.DataBean> dataList) {
        StoreAdapterRV adapter = new StoreAdapterRV(getContext(),dataList);
        mNearbyStoreRv.setAdapter(adapter);
    }
}
