package com.example.administrator.xiangou.cart;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.cart.adapter.AdapterDealCartRV;
import com.example.administrator.xiangou.cart.model.CartAllCbBean;
import com.example.administrator.xiangou.cart.model.CartItemCbBean;
import com.example.administrator.xiangou.cart.model.CartMergeBean;
import com.example.administrator.xiangou.cart.model.CartMergeItemBean;
import com.example.administrator.xiangou.cart.model.DealBean;
import com.example.administrator.xiangou.cart.model.GoodsDealBean;
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
    private List<CartMergeItemBean> mMergeItemBeanList;
    private List<CartItemCbBean> mItemCbBeanList;

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
        mMergeItemBeanList = new ArrayList<>();
        mItemCbBeanList = new ArrayList<>();

        mAllCountTv = findContentView(R.id.cart_goods_allcount);
        setTextToTv(mAllCountTv,"购物车("+10+")");
        mAllEditTv = findContentView(R.id.cart_edit_all);
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
        mDealRv.addItemDecoration(new ItemIntervalDecoration(0,0,8));

//        mDealRv.setHasFixedSize(true);
        ((SimpleItemAnimator)mDealRv.getItemAnimator()).setSupportsChangeAnimations(false);

        //模拟数据
        //店内商品
        GoodsDealBean goodsDealBean1 = new GoodsDealBean(R.mipmap.cart_goods_dfimg,"【实拍原版】春季韩版学院风九分裤毛边高腰宽松直筒阔腿裤","浅蓝",
                "26","S",2,59.50f,85.00f,7f);
        CartItemCbBean cartItemCbBean1 = new CartItemCbBean();
        cartItemCbBean1.setIscheck(false);
        CartMergeItemBean cartMergeItemBean1 = new CartMergeItemBean(goodsDealBean1,cartItemCbBean1);
        mMergeItemBeanList.add(cartMergeItemBean1);
        GoodsDealBean goodsDealBean2 = new GoodsDealBean(R.mipmap.cart_recommend_dfimg,"【实拍原版】春季韩版套装条纹宽松连衣裙","黑白条纹",
                "28","m",1,51.50f,68.00f,6f);
        CartItemCbBean cartItemCbBean2 = new CartItemCbBean();
        cartItemCbBean2.setIscheck(false);
        CartMergeItemBean cartMergeItemBean2 = new CartMergeItemBean(goodsDealBean2,cartItemCbBean2);
        mMergeItemBeanList.add(cartMergeItemBean2);

        mItemCbBeanList.add(cartItemCbBean1);
        mItemCbBeanList.add(cartItemCbBean2);

        //店铺
        DealBean dealBean1 = new DealBean("皮皮虾",111.00f);
        DealBean dealBean2 = new DealBean("老铁，稳",60.00f);
        DealBean dealBean3 = new DealBean("糖宝",51.50f);

        //根据店铺数量设置
        CartAllCbBean allCbBean1 = new CartAllCbBean();
//        allCbBean1.setText(storeList.get(0).getStoreName());
        allCbBean1.setIsCheck(false);
        allCbBean1.setList(mItemCbBeanList);
        CartAllCbBean allCbBean2 = new CartAllCbBean();
//        allCbBean1.setText(storeList.get(1).getStoreName());
        allCbBean2.setIsCheck(false);
        allCbBean2.setList(mItemCbBeanList);
        CartAllCbBean allCbBean3 = new CartAllCbBean();
//        allCbBean1.setText(storeList.get(2).getStoreName());
        allCbBean3.setIsCheck(false);
        allCbBean3.setList(mItemCbBeanList);

        CartMergeBean cartMergeBean1 = new CartMergeBean(dealBean1,allCbBean1,mMergeItemBeanList);
        CartMergeBean cartMergeBean2 = new CartMergeBean(dealBean2,allCbBean2,mMergeItemBeanList);
        CartMergeBean cartMergeBean3 = new CartMergeBean(dealBean3,allCbBean3,mMergeItemBeanList);
        mMergeBeanList.add(cartMergeBean1);
        mMergeBeanList.add(cartMergeBean2);
        mMergeBeanList.add(cartMergeBean3);


        mAdapterDealCartRV = new AdapterDealCartRV(getContext(),mMergeBeanList);
        mDealRv.setAdapter(mAdapterDealCartRV);
        mAdapterDealCartRV.setOnStroeCbClickListener(new AdapterDealCartRV.OnStroeCbClickListener() {
            @Override
            public void setOnStoreCbClick(boolean isChecked, int position) {
                //保存店铺点击状态
                mMergeBeanList.get(position).getCartAllCbBean().setIsCheck(isChecked);
                //通知全选CheckBox的选择状态
                if (allSelect() == mMergeBeanList.size()){
                    mAllGoodsCb.setChecked(true);
                }else {
                    mAllGoodsCb.setChecked(false);
                }
                if (isChecked){
                    for (int i = 0; i < mMergeBeanList.get(position).getCartAllCbBean().getList().size(); i++) {
                        if (!mMergeBeanList.get(position).getCartAllCbBean().getList().get(i).ischeck()) {
                            mMergeBeanList.get(position).getCartAllCbBean().getList().get(i).setIscheck(true);
                        }
                    }
                }else {
                    // 解决--点击取消选择商品时，店铺全选按钮取消选择状态，会变成全不选的bug
                    if (allItemSelect(position) == mMergeBeanList.get(position).getCartAllCbBean().getList().size()){
                        for (int i = 0; i < mMergeBeanList.get(position).getCartAllCbBean().getList().size(); i++) {
                            if (mMergeBeanList.get(position).getCartAllCbBean().getList().get(i).ischeck()){
                                mMergeBeanList.get(position).getCartAllCbBean().getList().get(i).setIscheck(false);
                            }
                        }
                    }
                }
                UpdateRecyclerView(position);
            }

            @Override
            public void setOnItemCbCheckListener(boolean isItemChecked, int parentposition, int chaildposition) {
                //保存商品点击状态
                mMergeBeanList.get(parentposition).getCartAllCbBean().getList().get(chaildposition).setIscheck(isItemChecked);
                //通知店铺选择的状态
                if ( allItemSelect(parentposition) == mMergeBeanList.get(parentposition).getCartAllCbBean().getList().size()){
                    mMergeBeanList.get(parentposition).getCartAllCbBean().setIsCheck(true);
                }else {
                    mMergeBeanList.get(parentposition).getCartAllCbBean().setIsCheck(false);
                }
                if (mMergeBeanList.get(parentposition).getCartAllCbBean().ischeck()){
                    for (int i = 0; i < mMergeBeanList.get(parentposition).getCartAllCbBean().getList().size(); i++) {
                        if (!mMergeBeanList.get(parentposition).getCartAllCbBean().getList().get(i).ischeck()) {
                            mMergeBeanList.get(parentposition).getCartAllCbBean().getList().get(i).setIscheck(true);
                        }
                    }
                }
                UpdateRecyclerView(parentposition);
            }
        });

        mRecommendationRv = findContentView(R.id.cart_recommendation_rv,true);
        mRecommendationRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
    }


    @Override
    public void onClick(View v) {
        if (v==mAllEditTv){
            showToast("here is cart :"+ mAllEditTv.getText());
        }else if (v==mNewsCountTv||v==mNewsImg){
            showToast("here is cart :you have article"+ mNewsCountTv.getText()+" news");
        }else if (v==mBalanceTv){
            showToast("here is cart :you want to balance /\nthe total = "+mTotalTv.getText());
        }
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            //全选
            for (int i = 0; i < mMergeBeanList.size(); i++) {
                //选择店铺
                if (!mMergeBeanList.get(i).getCartAllCbBean().ischeck()){
                    mMergeBeanList.get(i).getCartAllCbBean().setIsCheck(true);
                }
                //店铺里的商品
                for (int j = 0; j < mMergeBeanList.get(i).getCartAllCbBean().getList().size(); j++) {
                    if (!mMergeBeanList.get(i).getCartAllCbBean().getList().get(j).ischeck()){
                        mMergeBeanList.get(i).getCartAllCbBean().getList().get(j).setIscheck(true);
                    }
                }
            }
            UpdateRecyclerView(0,mMergeBeanList.size()-1);//更新
        }else {
            //全不选时执行
            if (allSelect() == mMergeBeanList.size()) {
                Log.e("allSelect", "here is unAllselect: " +allSelect() );
                for (int i = 0; i < mMergeBeanList.size(); i++) {
                    //选择店铺
                    if (mMergeBeanList.get(i).getCartAllCbBean().ischeck()) {
                        mMergeBeanList.get(i).getCartAllCbBean().setIsCheck(false);
                    }
                    //店铺里的商品
                    for (int j = 0; j < mMergeBeanList.get(i).getCartAllCbBean().getList().size(); j++) {
                        if (mMergeBeanList.get(i).getCartAllCbBean().getList().get(j).ischeck()) {
                            mMergeBeanList.get(i).getCartAllCbBean().getList().get(j).setIscheck(false);
                        }
                    }
                }
                UpdateRecyclerView(0,mMergeBeanList.size()-1);//更新
            }
        }
    }
    /**
     *解决Recycleyview刷新报错问题
     */
    private void UpdateRecyclerView(final int position) {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                mAdapterDealCartRV.notifyItemChanged(position);
            }
        };
        handler.post(r);
    }
    private void UpdateRecyclerView(final int startpos,final int topos) {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                mAdapterDealCartRV.notifyItemRangeChanged(startpos,topos);//更新
            }
        };
        handler.post(r);
    }
    /**
     *计算店铺的选择数量
     */
    private int allSelect(){
        int count=0;
        for (int i = 0; i < mMergeBeanList.size(); i++) {
            if (mMergeBeanList.get(i).getCartAllCbBean().ischeck()){
                count++;
            }
        }
        return count;
    }

    /**
     * 计算每个店铺商品的选择数量
     * @param position 店铺的位置
     * @return
     */
    private int allItemSelect(int position){
        int count=0;
        for (int i = 0; i < mMergeBeanList.get(position).getCartAllCbBean().getList().size(); i++) {
            if (mMergeBeanList.get(position).getCartAllCbBean().getList().get(i).ischeck()) {
                count++;
            }
        }
        return count;
    }
}
