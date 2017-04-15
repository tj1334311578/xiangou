package com.example.administrator.xiangou.cart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.cart.adapter.AdapterDealCartRV;
import com.example.administrator.xiangou.cart.model.DealBean;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends MVPBaseFragment<CartContract.View, CartPresenter>
        implements CartContract.View ,View.OnClickListener {
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
//        mFragView = inflater.inflate(R.layout.fragment_cart,container,false);
//        initView();
//        return mFragView;
        return setContextView(inflater,container,R.layout.fragment_cart);

    }

    @Override
    public void initView() {
        mAllCountTv = findContentView(R.id.cart_goods_allcount,true);
        mAllCountTv.setText("购物车("+10+")");
        mAllEditTv = findContentView(R.id.cart_edit_all,true);
        mNewsImg = findContentView(R.id.news_cart_title,true);
        mNewsCountTv = findContentView(R.id.news_num_cart_title,true);
        mNewsCountTv.setText(3+"");
        mDealRv = findContentView(R.id.cart_deal_rv);
        mDealRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        mDealRv.addItemDecoration(new ItemIntervalDecoration(0,0,8));
        ((SimpleItemAnimator)mDealRv.getItemAnimator()).setSupportsChangeAnimations(false);

        List<DealBean> beanList = new ArrayList<>();
        beanList.add(new DealBean("皮皮虾",111.00f));
        beanList.add(new DealBean("老铁，稳",60.00f));
        beanList.add(new DealBean("糖宝",51.50f));
        final AdapterDealCartRV adapter = new AdapterDealCartRV(getContext(),beanList);
        mDealRv.setAdapter(adapter);
        //        adapter.setOnMineItemClickListener(new RVBaseAdapter.OnMineItemClickListener() {
        //            @Override
        //            public void onMineItemClick(View view, int position) {
        //                if (mDealRv.getScrollState()==RecyclerView.SCROLL_STATE_IDLE&&((RecyclerView)view).getScrollState()==RecyclerView.SCROLL_STATE_IDLE){
        //                    adapter.notifyItemChanged(position);
        //                }
        //            }
        //
        //            @Override
        //            public void onMineItemLongClick(View view, int position) {
        //
        //            }
        //        });

        mRecommendationRv = findContentView(R.id.cart_recommendation_rv,true);
        mRecommendationRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
    }


    @Override
    public void onClick(View v) {
        if (v==mAllEditTv){
            Toast.makeText(getContext(), "here is cart :"+ mAllEditTv.getText(), Toast.LENGTH_SHORT).show();
        }else if (v==mNewsCountTv||v==mNewsImg){
            Toast.makeText(getContext(), "here is cart :you have article"+ mNewsCountTv.getText()+" news", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void sendFialRequest(String message) {

    }

}
