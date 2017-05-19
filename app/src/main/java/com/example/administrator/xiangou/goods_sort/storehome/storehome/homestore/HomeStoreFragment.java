package com.example.administrator.xiangou.goods_sort.storehome.storehome.homestore;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.goods_sort.storehome.HomePageBean;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HomeStoreFragment extends MVPBaseFragment<HomeStoreContract.View, HomeStorePresenter> implements HomeStoreContract.View {
    private RecyclerView mHomeStoreRecycle;
    private HomePageBean homePageBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,R.layout.recycleview_style);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void initView() {
        this.homePageBean= (HomePageBean) getArguments().getSerializable("databean");
        mHomeStoreRecycle= (RecyclerView) mContextView.findViewById(R.id.recycleview_style_recycle);
        mHomeStoreRecycle.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (homePageBean!=null) {
            mPresenter.dealHomeStoreCall(homePageBean);
        }
    }

    @Override
    public void sendDataBeanToView(HomePageBean dataBean) {
        mHomeStoreRecycle.setAdapter(new mHomeStoreAdapter(getContext(),dataBean));
    }
}
