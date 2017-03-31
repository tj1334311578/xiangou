package com.example.administrator.xiangou.nearby.nearbygoods;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;
import com.example.administrator.xiangou.nearby.ChildType;
import com.example.administrator.xiangou.nearby.nearbygoods.adapter.NearbyGoodsAdapter;

import java.util.ArrayList;
import java.util.List;

public class NearbyGoodsFragment extends MVPBaseFragment<NearbyGoodsContract.View, NearbyGoodsPresenter> implements NearbyGoodsContract.View {
    public static final int TYPE_CLOTHING = 1;
    public static final int TYPE_MAKEUP = 2;
    public static final int TYPE_HOUSEHOLDS = 3;

    private RecyclerView mGoodsRv;
    private List<ChildType> mChildTypes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby_goods,container,false);
        mGoodsRv = (RecyclerView) view.findViewById(R.id.goods_nearby_rv);
        mGoodsRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        mChildTypes = new ArrayList<>();
        mChildTypes.add(new ChildType(TYPE_CLOTHING));
        mChildTypes.add(new ChildType(TYPE_MAKEUP));
        mChildTypes.add(new ChildType(TYPE_HOUSEHOLDS));
        NearbyGoodsAdapter adapter = new NearbyGoodsAdapter(getContext(),mChildTypes);
        mGoodsRv.setAdapter(adapter);
        return view;
    }

    @Override
    public void sendFialRequest(String message) {

    }
}