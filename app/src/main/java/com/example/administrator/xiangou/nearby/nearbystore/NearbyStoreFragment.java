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
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.ArrayList;
import java.util.List;

public class NearbyStoreFragment extends MVPBaseFragment<NearbyStoreContract.View, NearbyStorePresenter> implements NearbyStoreContract.View {
    private RecyclerView mNearbyStoreRv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby_store,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mNearbyStoreRv = (RecyclerView) view.findViewById(R.id.store_nearby_rv);
        mNearbyStoreRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        mNearbyStoreRv.addItemDecoration(new ItemIntervalDecoration(0,0,8));
        List<StoreBean> beanList = new ArrayList<StoreBean>();
        for (int i = 0; i < 8; i++) {
            beanList.add(
                    new StoreBean(R.mipmap.nearby_store_dflogo,"皮皮虾的店",300,
                            R.mipmap.nearby_store_dfimg,
                            R.mipmap.nearby_store_dfimg,
                            R.mipmap.nearby_store_dfimg,
                            "皮皮虾，我们走，去找一个蓝盆友，吃炸鸡喝啤酒，还能一起拉拉手。皮皮虾拉拉手，以后的路一起走，不想再做单身狗，人家也要捶胸口",
                            10086,520,2333));
        }
        StoreAdapter adapter = new StoreAdapter(getContext(),beanList);
        mNearbyStoreRv.setAdapter(adapter);
    }

    @Override
    public void sendFialRequest(String message) {

    }
}
