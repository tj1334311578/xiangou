package com.example.administrator.xiangou.goods_details.storehome.storehome.homestore;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HomeStoreFragment extends MVPBaseFragment<HomeStoreContract.View, HomeStorePresenter> implements HomeStoreContract.View {
     RecyclerView mHomeStoreRecycle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,R.layout.goods_ranking_gride);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void initView() {
        mHomeStoreRecycle= (RecyclerView) mContextView.findViewById(R.id.goods_ranking_recycle);
        mHomeStoreRecycle.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        List<String> list=new ArrayList<>();
        list.add("jefi");
        list.add("ijfe");
        mHomeStoreRecycle.setAdapter(new mHomeStoreAdapter(getContext(),list));
    }
}
