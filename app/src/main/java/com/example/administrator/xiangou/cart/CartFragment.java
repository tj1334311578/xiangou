package com.example.administrator.xiangou.cart;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.cart.adapter.AdapterDealCartRV;
import com.example.administrator.xiangou.cart.model.CartMergeBean;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;
import com.example.administrator.xiangou.tool.ContextUtils;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends MVPBaseFragment<CartContract.View, CartPresenter>
        implements CartContract.View ,View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private TextView mAllCountTv,mAllEditTv,mNewsCountTv,mTotalTv,mBalanceTv;
    private ImageView mNewsImg;
    private CheckBox mAllGoodsCb;
    RecyclerView mDealRv,mRecommendationRv;

    private List<CartMergeBean> mMergeBeanList;
//    private List<CartMergeItemBean> mMergeItemBeanList,mMergeItemBeanList1,mMergeItemBeanList2;

    private float totalPrice=0;
    private AdapterDealCartRV mAdapterDealCartRV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,R.layout.fragment_cart);
    }

    @Override
    public void initView() {
        mMergeBeanList = new ArrayList<>();
//        mMergeItemBeanList = new ArrayList<>();
//        mMergeItemBeanList1 = new ArrayList<>();
//        mMergeItemBeanList2 = new ArrayList<>();

        mAllCountTv = findContentView(R.id.cart_goods_allcount);
        setTextToTv(mAllCountTv,"购物车("+10+")");
        mNewsImg = findContentView(R.id.news_cart_title);
        mNewsCountTv = findContentView(R.id.news_num_cart_title);
        setTextToTv(mNewsCountTv,3);

        mTotalTv = findContentView(R.id.cart_total_tv,false);
        setTextToTv(mTotalTv,"￥ "+ ContextUtils.S2places(totalPrice)+" 元");
        mBalanceTv = findContentView(R.id.cart_balance_tv);
        mAllGoodsCb = findContentView(R.id.cart_allgoods_checkBox,false);
        mAllGoodsCb.setOnCheckedChangeListener(this);//监听全选

        mDealRv = findContentView(R.id.cart_deal_rv);
        mDealRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        mDealRv.addItemDecoration(new ItemIntervalDecoration(0,10,0,0));
        mDealRv.setNestedScrollingEnabled(false);
        mDealRv.setHasFixedSize(true);
        ((SimpleItemAnimator)mDealRv.getItemAnimator()).setSupportsChangeAnimations(false);

        mMergeBeanList = mPresenter.initAdapterData();
        mAdapterDealCartRV = new AdapterDealCartRV(getContext(),mMergeBeanList);
        mDealRv.setAdapter(mAdapterDealCartRV);
        mAdapterDealCartRV.setOnStroeClickListener(new AdapterDealCartRV.OnStroeClickListener() {
            @Override
            public void setOnStoreCbClick(boolean isChecked, int position) {
                mPresenter.dealStoreCheckBox(isChecked,position,mAllGoodsCb);
            }

            @Override
            public void setOnItemCbCheckListener(boolean isItemChecked, final int parentposition, final int chaildposition) {
                mPresenter.dealGoodsCheckBox(isItemChecked, parentposition, chaildposition);
            }

            @Override
            public void setOnEditStoreGoods(TextView v, final int position) {
                mPresenter.setOnEditStoreGoods(v, position);
            }

            @Override
            public void setOnDeleteGoodsClick(TextView tv, final int parentposition, final int chaildposition) {
                mPresenter.setOnDeleteGoodsClick(tv, parentposition, chaildposition);

            }

            @Override
            public void setOnDecreaseGoodsClick(final ImageView iv, final int parentposition, final int chaildposition, TextView goodsCountTv) {
                mPresenter.setOnDecreaseGoodsClick(iv, parentposition, chaildposition, goodsCountTv);
            }

            @Override
            public void setOnAddGoodsClick(ImageView iv, int parentposition, int chaildposition, TextView goodsCountTv) {
                mPresenter.setOnAddGoodsClick(iv, parentposition, chaildposition, goodsCountTv);
            }

            @Override
            public void setOnEditGoodsClick(ImageView view, int parentposition, int chaildposition) {
//                showToast("编辑商品属性！");
                mPresenter.setOnEditGoodsClick(view, parentposition, chaildposition);
            }
        });


        // TODO: 2017/4/17 推荐模块
        mRecommendationRv = findContentView(R.id.cart_recommendation_rv,true);
        mRecommendationRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mPresenter.dealAllCheckBox(buttonView, isChecked);
//        Log.e("全选", "onCheckedChanged:全选 " +isChecked);
//        if (isChecked){
//            //全选时执行
//            for (int i = 0; i < mMergeBeanList.size(); i++) {
//                //选择店铺
//                if (!mMergeBeanList.get(i).getStoreStatusBean().ischeck()){
//                    mMergeBeanList.get(i).getStoreStatusBean().setIsCheck(true);
//                }
//                //店铺里的商品
//                for (int j = 0; j < mMergeBeanList.get(i).getMergeItemBeanList().size(); j++) {
//                    if (!mMergeBeanList.get(i).getMergeItemBeanList().get(j).getItemStatusBean().ischeck()){
//                        mMergeBeanList.get(i).getMergeItemBeanList().get(j).getItemStatusBean().setIscheck(true);
//                    }
//                }
//            }
//            toUpdataView(0,mMergeBeanList.size());//更新
//        }else {
//            //全不选时执行
//            if (allSelect() == mMergeBeanList.size()) {
//                Log.e("unallSelect", "here is unAllselect: " +allSelect() );
//                for (int i = 0; i < mMergeBeanList.size(); i++) {
//                    //选择店铺
//                    if (mMergeBeanList.get(i).getStoreStatusBean().ischeck()) {
//                        mMergeBeanList.get(i).getStoreStatusBean().setIsCheck(false);
//                    }
//                    //店铺里的商品
//                    for (int j = 0; j < mMergeBeanList.get(i).getMergeItemBeanList().size(); j++) {
//                        if (mMergeBeanList.get(i).getMergeItemBeanList().get(j).getItemStatusBean().ischeck()) {
//                            mMergeBeanList.get(i).getMergeItemBeanList().get(j).getItemStatusBean().setIscheck(false);
//                        }
//                    }
//                }
//                toUpdataView(0,mMergeBeanList.size());//更新
//            }
//        }
    }
    /**
     *解决Recycleyview刷新报错问题
     */
//    private void toUpdataView(final int startpos, final int count) {
//        Handler handler = new Handler();
//        final Runnable r = new Runnable() {
//            public void run() {
//                mAdapterDealCartRV.notifyItemRangeChanged(startpos,count);//更新
//                calculateTotalPrice();
//                setTextToTv(mTotalTv,"￥ "+ ContextUtils.S2places(totalPrice)+" 元");
//            }
//        };
//        handler.post(r);
//    }
//    private void calculateTotalPrice(){
//        totalPrice = 0;//重置合计
//        for (int i = 0; i < mMergeBeanList.size(); i++) {
//            for (int j = 0; j < mMergeBeanList.get(i).getMergeItemBeanList().size(); j++) {
//                if (mMergeBeanList.get(i).getMergeItemBeanList().get(j).getItemStatusBean().ischeck()){
//                    totalPrice += (mMergeBeanList.get(i).getMergeItemBeanList().get(j).getGoodsDealBean().getGoodsPrice()
//                        * mMergeBeanList.get(i).getMergeItemBeanList().get(j).getGoodsDealBean().getGoodsCount());
//                }
//            }
//        }
//    }
//
//    /**
//     *计算店铺的选择数量
//     */
//    private int allSelect(){
//        int count=0;
//        for (int i = 0; i < mMergeBeanList.size(); i++) {
//            if (mMergeBeanList.get(i).getStoreStatusBean().ischeck()){
//                count++;
//            }
//        }
//        return count;
//    }
//
//    /**
//     * 计算每个店铺商品的选择数量
//     * @param position 店铺的位置
//     * @return
//     */
//    private int allItemSelect(int position){
//        int count=0;
//        for (int i = 0; i < mMergeBeanList.get(position).getMergeItemBeanList().size(); i++) {
//            if (mMergeBeanList.get(position).getMergeItemBeanList().get(i).getItemStatusBean().ischeck()) {
//                count++;
//            }
//        }
//        return count;
//    }



    @Override
    public void onClick(View v) {
        if (v==mNewsCountTv||v==mNewsImg){
            showToast("here is cart :you have article"+ mNewsCountTv.getText()+" news");
        }else if (v==mBalanceTv){
            showToast("here is cart :you want to balance /\nthe total = "+mTotalTv.getText());
        }
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void toUpdataView(final int startpos, final int count, final String text) {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                mAdapterDealCartRV.notifyItemRangeChanged(startpos,count);//更新
//                calculateTotalPrice();
                setTextToTv(mTotalTv,text);
            }
        };
        handler.post(r);
    }
}
