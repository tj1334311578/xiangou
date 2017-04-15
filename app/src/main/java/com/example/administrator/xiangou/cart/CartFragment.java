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
import com.example.administrator.xiangou.cart.model.CartAllCbBean;
import com.example.administrator.xiangou.cart.model.CartItemCbBean;
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

    private List<CartAllCbBean> mAllCbBeanList;
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
        mAllCbBeanList = new ArrayList<>();
        mItemCbBeanList = new ArrayList<>();

        mAllCountTv = findContentView(R.id.cart_goods_allcount);
        mAllCountTv.setText("购物车("+10+")");
        mAllEditTv = findContentView(R.id.cart_edit_all);
        mNewsImg = findContentView(R.id.news_cart_title);
        mNewsCountTv = findContentView(R.id.news_num_cart_title);
        mNewsCountTv.setText(3+"");

        mTotalTv = findContentView(R.id.cart_total_tv,false);
        setTextToTv(mTotalTv,"￥ "+ ContextUtils.S2places(totalPrice)+" 元");
        mBalanceTv = findContentView(R.id.cart_balance_tv);
        mAllGoodsCb = findContentView(R.id.cart_allgoods_checkBox,false);
        mAllGoodsCb.setOnCheckedChangeListener(this);//监听全选

        mDealRv = findContentView(R.id.cart_deal_rv);
        mDealRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        mDealRv.addItemDecoration(new ItemIntervalDecoration(0,0,8));

        mDealRv.setHasFixedSize(true);
        ((SimpleItemAnimator)mDealRv.getItemAnimator()).setSupportsChangeAnimations(false);

        //模拟数据
        //店内商品
        List<GoodsDealBean> goodsList = new ArrayList<>();
        goodsList.add(new GoodsDealBean(R.mipmap.cart_goods_dfimg,"【实拍原版】春季韩版学院风九分裤毛边高腰宽松直筒阔腿裤","浅蓝",
                "26","S",2,59.50f,85.00f,7f));
        goodsList.add(new GoodsDealBean(R.mipmap.cart_recommend_dfimg,"【实拍原版】春季韩版套装条纹宽松连衣裙","黑白条纹",
                "28","m",1,51.50f,68.00f,6f));
        //店铺
        List<DealBean> storeList = new ArrayList<>();
        storeList.add(new DealBean("皮皮虾",111.00f,goodsList));
        storeList.add(new DealBean("老铁，稳",60.00f,goodsList));
        storeList.add(new DealBean("糖宝",51.50f,goodsList));

        //根据店内商品数量设置
        for (int i = 0; i < goodsList.size(); i++) {
            CartItemCbBean item = new CartItemCbBean();
            item.setIscheck(false);
            mItemCbBeanList.add(item);
        }
        //根据店铺数量设置
        CartAllCbBean allCbBean1 = new CartAllCbBean();
//        allCbBean1.setText(storeList.get(0).getStoreName());
        allCbBean1.setIsCheck(false);
        allCbBean1.setList(mItemCbBeanList);
        CartAllCbBean allCbBean2 = new CartAllCbBean();
//        allCbBean1.setText(storeList.get(1).getStoreName());
        allCbBean1.setIsCheck(false);
        allCbBean1.setList(mItemCbBeanList);
        CartAllCbBean allCbBean3 = new CartAllCbBean();
//        allCbBean1.setText(storeList.get(2).getStoreName());
        allCbBean1.setIsCheck(false);
        allCbBean1.setList(mItemCbBeanList);
        mAllCbBeanList.add(allCbBean1);
        mAllCbBeanList.add(allCbBean2);
        mAllCbBeanList.add(allCbBean3);

        mAdapterDealCartRV = new AdapterDealCartRV(getContext(),storeList,mAllCbBeanList);
        mDealRv.setAdapter(mAdapterDealCartRV);
        mAdapterDealCartRV.setOnStroeCbClickListener(new AdapterDealCartRV.OnStroeCbClickListener() {
            @Override
            public void setOnStoreCbClick(boolean isChecked, int position) {
                //保存店铺点击状态
                mAllCbBeanList.get(position).setIsCheck(isChecked);
                //通知全选CheckBox的选择状态
                if (allSelect() == mAllCbBeanList.size()){
                    mAllGoodsCb.setChecked(true);
                }else {
                    mAllGoodsCb.setChecked(false);
                }

                if (isChecked){
                    for (int i = 0; i < mAllCbBeanList.get(position).getList().size(); i++) {
                        mAllCbBeanList.get(position).getList().get(i).setIscheck(true);
                    }
                }else {

                    if (allItemSelect(position) == mAllCbBeanList.get(position).getList().size()){
                        for (int i = 0; i < mAllCbBeanList.get(position).getList().size(); i++) {
                            // 解决点击取消选择商品时，
                            // 店铺全选按钮取消选择状态，不会不变成全不选
                            if (mAllCbBeanList.get(position).getList().get(i).ischeck()){
                                mAllCbBeanList.get(position).getList().get(i).setIscheck(false);
                            }
                        }
                    }
                }
                mAdapterDealCartRV.notifyItemChanged(position);
            }

            @Override
            public void setOnItemCbCheckListener(boolean isItemChecked, int parentposition, int chaildposition) {
                //保存商品点击状态
                mAllCbBeanList.get(parentposition).getList().get(chaildposition).setIscheck(isItemChecked);
                //通知店铺选择的状态
                if ( allItemSelect(parentposition) == mAllCbBeanList.get(parentposition).getList().size()){
                    mAllCbBeanList.get(parentposition).setIsCheck(true);
                }else {
                    mAllCbBeanList.get(parentposition).setIsCheck(false);
                }
                mAdapterDealCartRV.notifyItemChanged(parentposition);
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
            for (int i = 0; i < mAllCbBeanList.size(); i++) {
                //选择店铺
                if (!mAllCbBeanList.get(i).ischeck()){
                    mAllCbBeanList.get(i).setIsCheck(true);
                }
                //店铺里的商品
                for (int j = 0; j < mAllCbBeanList.get(i).getList().size(); j++) {
                    if (!mAllCbBeanList.get(i).getList().get(j).ischeck()){
                        mAllCbBeanList.get(i).getList().get(j).setIscheck(true);
                    }
                }
            }
        }else {
            //全不选
            for (int i = 0; i < mAllCbBeanList.size(); i++) {
                //选择店铺
                if (!mAllCbBeanList.get(i).ischeck()){
                    mAllCbBeanList.get(i).setIsCheck(false);
                }
                //店铺里的商品
                for (int j = 0; j < mAllCbBeanList.get(i).getList().size(); j++) {
                    if (!mAllCbBeanList.get(i).getList().get(j).ischeck()){
                        mAllCbBeanList.get(i).getList().get(j).setIscheck(false);
                    }
                }
            }
        }
//        UpdateRecyclerView();
        mAdapterDealCartRV.notifyItemRangeChanged(0,mAllCbBeanList.size()-1);//更新
    }
    /**
     *解决Recycleyview刷新报错问题
     */
    private void UpdateRecyclerView() {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                mAdapterDealCartRV.notifyDataSetChanged();
            }
        };
        handler.post(r);
    }
    /**
     *计算店铺的选择数量
     */
    private int allSelect(){
        int count=0;
        for (int i = 0; i < mAllCbBeanList.size(); i++) {
            if (mAllCbBeanList.get(i).ischeck()){
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
        for (int i = 0; i < mAllCbBeanList.get(position).getList().size(); i++) {
            count++;
        }
        return count;
    }
}
