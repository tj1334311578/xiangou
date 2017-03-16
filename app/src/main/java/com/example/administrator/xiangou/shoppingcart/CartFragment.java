package com.example.administrator.xiangou.shoppingcart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.BaseAdapter;
import com.example.administrator.xiangou.home.ChildHomeBean;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzongyao on 2017/2/28.
 */

public class CartFragment extends Fragment implements View.OnClickListener{
    private TextView mAllCountTv,mAllEditTv,mNewsCountTv;
    private ImageView mNewsImg;
    RecyclerView mDealRv,mRecommendationRv;
    private View mFragView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragView = inflater.inflate(R.layout.fragment_cart,container,false);
        initView();
        return mFragView;

    }

    private void initView() {
//        mAllCountTv = (TextView) mFragView.findViewById(R.id.cart_goods_allcount);
//        mAllCountTv.setOnClickListener(this);
//        mAllEditTv = (TextView) mFragView.findViewById(R.id.cart_edit_all);
//        mAllEditTv.setOnClickListener(this);
        mAllCountTv = getMyView(R.id.cart_goods_allcount);
        mAllCountTv.setText("购物车("+10+")");
        mAllEditTv = getMyView(R.id.cart_edit_all);
        mNewsImg = getMyView(R.id.news_cart_title);
        mNewsCountTv = getMyView(R.id.news_num_cart_title);
        mNewsCountTv.setText(3+"");
        mDealRv = (RecyclerView) mFragView.findViewById(R.id.cart_deal_rv);
        mDealRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mDealRv.addItemDecoration(new ItemIntervalDecoration(0,0,8));

        List<DealBean> beanList = new ArrayList<>();
        beanList.add(new DealBean("皮皮虾",88.00f));
        beanList.add(new DealBean("老铁，稳",68.00f));
        AdapterDealCart adapter = new AdapterDealCart(getContext(),beanList);
        mDealRv.setAdapter(adapter);

        mRecommendationRv = getMyView(R.id.cart_recommendation_rv);
        mRecommendationRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }
    private <T extends View> T getMyView(int id){
        View view = mFragView.findViewById(id);
        view.setOnClickListener(this);
        return (T) view;
    }


    @Override
    public void onClick(View v) {
        if (v==mAllEditTv){
            Toast.makeText(getContext(), "here is cart :"+ mAllEditTv.getText(), Toast.LENGTH_SHORT).show();
        }else if (v==mNewsCountTv||v==mNewsImg){
            Toast.makeText(getContext(), "here is cart :you have article"+ mNewsCountTv.getText()+" news", Toast.LENGTH_SHORT).show();
        }
//        else if (v==mDealRv){}else if (v==mRecommendationRv){}
    }
}
