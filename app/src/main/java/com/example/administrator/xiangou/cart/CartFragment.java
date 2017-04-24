package com.example.administrator.xiangou.cart;


import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.administrator.xiangou.cart.model.CartMergeBean;
import com.example.administrator.xiangou.cart.model.CartMergeItemBean;
import com.example.administrator.xiangou.cart.model.GoodsDealBean;
import com.example.administrator.xiangou.cart.model.ItemStatusBean;
import com.example.administrator.xiangou.cart.model.StoreDealBean;
import com.example.administrator.xiangou.cart.model.StoreStatusBean;
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
    private List<CartMergeItemBean> mMergeItemBeanList,mMergeItemBeanList1,mMergeItemBeanList2;

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
        mMergeItemBeanList1 = new ArrayList<>();
        mMergeItemBeanList2 = new ArrayList<>();

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

        //模拟数据
        //店内商品
        GoodsDealBean goodsDealBean1 = new GoodsDealBean(R.mipmap.cart_goods_dfimg,"【实拍原版】春季韩版学院风九分裤毛边高腰宽松直筒阔腿裤","浅蓝",
                "26","S",2,59.50f,85.00f);
        ItemStatusBean itemStatusBean1 = new ItemStatusBean();
        itemStatusBean1.setIscheck(false);
        CartMergeItemBean cartMergeItemBean1 = new CartMergeItemBean(goodsDealBean1, itemStatusBean1);
        mMergeItemBeanList.add(cartMergeItemBean1);
        GoodsDealBean goodsDealBean2 = new GoodsDealBean(R.mipmap.cart_recommend_dfimg,"【实拍原版】春季韩版套装条纹宽松连衣裙","黑白条纹",
                "28","m",1,51.50f,68.00f);
        ItemStatusBean itemStatusBean2 = new ItemStatusBean();
        itemStatusBean2.setIscheck(false);
        CartMergeItemBean cartMergeItemBean2 = new CartMergeItemBean(goodsDealBean2, itemStatusBean2);
        mMergeItemBeanList.add(cartMergeItemBean2);

        GoodsDealBean goodsDealBean3 = new GoodsDealBean(R.mipmap.cart_goods_dfimg,"【实拍原版】春季英伦风七分裤","卡其色",
                "26","S",2,59.50f,85.00f);
        ItemStatusBean itemStatusBean3 = new ItemStatusBean();
        itemStatusBean3.setIscheck(false);
        CartMergeItemBean cartMergeItemBean3 = new CartMergeItemBean(goodsDealBean3, itemStatusBean3);
        mMergeItemBeanList1.add(cartMergeItemBean3);
        GoodsDealBean goodsDealBean4 = new GoodsDealBean(R.mipmap.cart_recommend_dfimg,"【实拍原版】春季韩版套装条纹宽松连衣裙","黑白条纹",
                "28","m",1,51.50f,68.00f);
        ItemStatusBean itemStatusBean4 = new ItemStatusBean();
        itemStatusBean4.setIscheck(false);
        CartMergeItemBean cartMergeItemBean4 = new CartMergeItemBean(goodsDealBean4, itemStatusBean4);
        mMergeItemBeanList1.add(cartMergeItemBean4);
        GoodsDealBean goodsDealBean7 = new GoodsDealBean(R.mipmap.cart_goods_dfimg,"【实拍原版】春季英伦风七分裤","卡其色",
                "29","L",1,69.50f,89.00f);
        ItemStatusBean itemStatusBean7 = new ItemStatusBean();
        itemStatusBean7.setIscheck(false);
        CartMergeItemBean cartMergeItemBean7 = new CartMergeItemBean(goodsDealBean7, itemStatusBean7);
        mMergeItemBeanList1.add(cartMergeItemBean7);

        GoodsDealBean goodsDealBean5 = new GoodsDealBean(R.mipmap.cart_recommend_dfimg,"【实拍原版】秋季韩版套装宽松连衣裙","白蓝边",
                "28","m",1,51.50f,68.00f);
        ItemStatusBean itemStatusBean5 = new ItemStatusBean();
        itemStatusBean5.setIscheck(false);
        CartMergeItemBean cartMergeItemBean5 = new CartMergeItemBean(goodsDealBean5, itemStatusBean5);
        mMergeItemBeanList2.add(cartMergeItemBean5);
        GoodsDealBean goodsDealBean6 = new GoodsDealBean(R.mipmap.cart_goods_dfimg,"【实拍原版】夏季复古风直筒阔腿裤","深蓝",
                "26","S",2,59.50f,85.00f);
        ItemStatusBean itemStatusBean6 = new ItemStatusBean();
        itemStatusBean6.setIscheck(false);
        CartMergeItemBean cartMergeItemBean6 = new CartMergeItemBean(goodsDealBean6, itemStatusBean6);
        mMergeItemBeanList2.add(cartMergeItemBean6);

        //店铺
        StoreDealBean storeDealBean1 = new StoreDealBean("皮皮虾");
        StoreDealBean storeDealBean2 = new StoreDealBean("老铁，稳");
        StoreDealBean storeDealBean3 = new StoreDealBean("糖豆豆");

        //根据店铺数量设置
        StoreStatusBean allCbBean1 = new StoreStatusBean();
        StoreStatusBean allCbBean2 = new StoreStatusBean();
        StoreStatusBean allCbBean3 = new StoreStatusBean();

        CartMergeBean cartMergeBean1 = new CartMergeBean(storeDealBean1,allCbBean1,mMergeItemBeanList);
        CartMergeBean cartMergeBean2 = new CartMergeBean(storeDealBean2,allCbBean2,mMergeItemBeanList1);
        CartMergeBean cartMergeBean3 = new CartMergeBean(storeDealBean3,allCbBean3,mMergeItemBeanList2);
        mMergeBeanList.add(cartMergeBean1);
        mMergeBeanList.add(cartMergeBean2);
        mMergeBeanList.add(cartMergeBean3);

        mAdapterDealCartRV = new AdapterDealCartRV(getContext(),mMergeBeanList);
        mDealRv.setAdapter(mAdapterDealCartRV);
        mAdapterDealCartRV.setOnStroeClickListener(new AdapterDealCartRV.OnStroeClickListener() {
            @Override
            public void setOnStoreCbClick(boolean isChecked, int position) {
                Log.e("店铺", "enter店铺"  );
                //保存店铺点击状态
                mMergeBeanList.get(position).getStoreStatusBean().setIsCheck(isChecked);
                //通知全选CheckBox的选择状态
                if (allSelect() == mMergeBeanList.size() && !mAllGoodsCb.isChecked()){
                    Log.e("店铺", "通知全选true"  );
                    mAllGoodsCb.setChecked(true);
                }else if (allSelect() != mMergeBeanList.size() && mAllGoodsCb.isChecked()) {
                    Log.e("店铺", "通知全选false"  );
                    mAllGoodsCb.setChecked(false);
                }
                if (mMergeBeanList.get(position).getStoreStatusBean().ischeck()){
                    for (int i = 0; i < mMergeBeanList.get(position).getMergeItemBeanList().size(); i++) {
                        if (!mMergeBeanList.get(position).getMergeItemBeanList().get(i).getItemStatusBean().ischeck()) {
                            mMergeBeanList.get(position).getMergeItemBeanList().get(i).getItemStatusBean().setIscheck(true);
                        }
                    }
                }else {
                    //解决--点击取消选择商品时，店铺全选按钮取消选择状态，会变成全不选的bug
                    if (allItemSelect(position) == mMergeBeanList.get(position).getMergeItemBeanList().size()){
                        Log.e("店铺", "店铺:"+position+" all to false"  );
                        for (int i = 0; i < mMergeBeanList.get(position).getMergeItemBeanList().size(); i++) {
                            if (mMergeBeanList.get(position).getMergeItemBeanList().get(i).getItemStatusBean().ischeck()){
                                mMergeBeanList.get(position).getMergeItemBeanList().get(i).getItemStatusBean().setIscheck(false);
                            }
                        }
                    }
                }

                toUpdataView(position,1);
            }

            @Override
            public void setOnItemCbCheckListener(boolean isItemChecked, final int parentposition, final int chaildposition) {
                //保存商品点击状态
                mMergeBeanList.get(parentposition).getMergeItemBeanList().get(chaildposition).getItemStatusBean().setIscheck(isItemChecked);
                //通知店铺选择的状态
                if ( allItemSelect(parentposition) == mMergeBeanList.get(parentposition).getMergeItemBeanList().size()){
                    Log.e("商品:","店铺="+parentposition+":all商品 check..."+allItemSelect(parentposition));
                    mMergeBeanList.get(parentposition).getStoreStatusBean().setIsCheck(true);
                }else {
                    mMergeBeanList.get(parentposition).getStoreStatusBean().setIsCheck(false);
                }

                toUpdataView(parentposition,1);
            }

            @Override
            public void setOnEditStoreGoods(TextView v, final int position) {
                if ( !mMergeBeanList.get(position).getStoreStatusBean().isHasToEditStore()){
                    v.setText("完成");
                    mMergeBeanList.get(position).getStoreStatusBean().setHasToEditStore(true);
                    for (int i = 0; i < mMergeBeanList.get(position).getMergeItemBeanList().size(); i++) {
                        mMergeBeanList.get(position).getMergeItemBeanList().get(i).getItemStatusBean()
                                .setHasToEditGoods(true);
                    }
                }else {
                    v.setText("编辑");
                    mMergeBeanList.get(position).getStoreStatusBean().setHasToEditStore(false);
                    for (int i = 0; i < mMergeBeanList.get(position).getMergeItemBeanList().size(); i++) {
                        mMergeBeanList.get(position).getMergeItemBeanList().get(i).getItemStatusBean()
                                .setHasToEditGoods(false);
                    }
                }
//                toUpdataView(position,1);
                toUpdataView(0,mMergeBeanList.size());
            }

            @Override
            public void setOnDeleteGoodsClick(TextView tv, final int parentposition, final int chaildposition) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(mActivity);
                                    dialog.setTitle("购物车编辑").setMessage("是否要从购物车移除此商品").setPositiveButton("是", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            mMergeBeanList.get(parentposition).getMergeItemBeanList().remove(chaildposition);
//                                            toUpdataView(parentposition,1);
                                        }
                                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).create().show();

            }

            @Override
            public void setOnDecreaseGoodsClick(final ImageView iv, final int parentposition, final int chaildposition, TextView goodsCountTv) {
                if (mMergeBeanList.get(parentposition).getMergeItemBeanList().get(chaildposition).getGoodsDealBean().getGoodsCount()<2) {
                    iv.setClickable(false);
                }else {
                    int count = mMergeBeanList.get(parentposition).getMergeItemBeanList().get(chaildposition).getGoodsDealBean().getGoodsCount() -1;
                    mMergeBeanList.get(parentposition).getMergeItemBeanList().get(chaildposition).getGoodsDealBean().setGoodsCount( count );
                    setTextToTv(goodsCountTv,count);
                }
//                toUpdataView(parentposition,1);//更新
            }

            @Override
            public void setOnAddGoodsClick(ImageView iv, int parentposition, int chaildposition, TextView goodsCountTv) {
                int count = mMergeBeanList.get(parentposition).getMergeItemBeanList().get(chaildposition).getGoodsDealBean().getGoodsCount() +1;
                mMergeBeanList.get(parentposition).getMergeItemBeanList().get(chaildposition).getGoodsDealBean().setGoodsCount(count);
                setTextToTv(goodsCountTv,count);
//                toUpdataView(parentposition,1);
            }

            @Override
            public void setOnEditGoodsClick(ImageView view, int parentposition, int chaildposition) {
                showToast("编辑商品属性！");
            }
        });


        // TODO: 2017/4/17 推荐模块
        mRecommendationRv = findContentView(R.id.cart_recommendation_rv,true);
        mRecommendationRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.e("全选", "onCheckedChanged:全选 " +isChecked);
//        totalPrice = 0;//重置合计
        if (isChecked){
            //全选时执行
            for (int i = 0; i < mMergeBeanList.size(); i++) {
                //选择店铺
                if (!mMergeBeanList.get(i).getStoreStatusBean().ischeck()){
                    mMergeBeanList.get(i).getStoreStatusBean().setIsCheck(true);
                }
                //店铺里的商品
                for (int j = 0; j < mMergeBeanList.get(i).getMergeItemBeanList().size(); j++) {
                    if (!mMergeBeanList.get(i).getMergeItemBeanList().get(j).getItemStatusBean().ischeck()){
                        mMergeBeanList.get(i).getMergeItemBeanList().get(j).getItemStatusBean().setIscheck(true);
                    }
//                    totalPrice += (mMergeBeanList.get(i).getMergeItemBeanList().get(j).getGoodsDealBean().getGoodsPrice()
//                            * mMergeBeanList.get(i).getMergeItemBeanList().get(j).getGoodsDealBean().getGoodsCount());
                }
            }
            toUpdataView(0,mMergeBeanList.size());//更新
        }else {
            //全不选时执行
            if (allSelect() == mMergeBeanList.size()) {
                Log.e("unallSelect", "here is unAllselect: " +allSelect() );
                for (int i = 0; i < mMergeBeanList.size(); i++) {
                    //选择店铺
                    if (mMergeBeanList.get(i).getStoreStatusBean().ischeck()) {
                        mMergeBeanList.get(i).getStoreStatusBean().setIsCheck(false);
                    }
                    //店铺里的商品
                    for (int j = 0; j < mMergeBeanList.get(i).getMergeItemBeanList().size(); j++) {
                        if (mMergeBeanList.get(i).getMergeItemBeanList().get(j).getItemStatusBean().ischeck()) {
                            mMergeBeanList.get(i).getMergeItemBeanList().get(j).getItemStatusBean().setIscheck(false);
                        }
                    }
                }
                toUpdataView(0,mMergeBeanList.size());//更新
            }
        }
    }
    /**
     *解决Recycleyview刷新报错问题
     */
    private void toUpdataView(final int startpos, final int count) {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                mAdapterDealCartRV.notifyItemRangeChanged(startpos,count);//更新
                calculateTotalPrice();
                setTextToTv(mTotalTv,"￥ "+ ContextUtils.S2places(totalPrice)+" 元");
            }
        };
        handler.post(r);
    }
    private void calculateTotalPrice(){
        totalPrice = 0;//重置合计
        for (int i = 0; i < mMergeBeanList.size(); i++) {
            for (int j = 0; j < mMergeBeanList.get(i).getMergeItemBeanList().size(); j++) {
                if (mMergeBeanList.get(i).getMergeItemBeanList().get(j).getItemStatusBean().ischeck()){
                    totalPrice += (mMergeBeanList.get(i).getMergeItemBeanList().get(j).getGoodsDealBean().getGoodsPrice()
                        * mMergeBeanList.get(i).getMergeItemBeanList().get(j).getGoodsDealBean().getGoodsCount());
                }
            }
        }
    }

    /**
     *计算店铺的选择数量
     */
    private int allSelect(){
        int count=0;
        for (int i = 0; i < mMergeBeanList.size(); i++) {
            if (mMergeBeanList.get(i).getStoreStatusBean().ischeck()){
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
        for (int i = 0; i < mMergeBeanList.get(position).getMergeItemBeanList().size(); i++) {
            if (mMergeBeanList.get(position).getMergeItemBeanList().get(i).getItemStatusBean().ischeck()) {
                count++;
            }
        }
        return count;
    }



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
                calculateTotalPrice();
                setTextToTv(mTotalTv,"￥ "+ ContextUtils.S2places(totalPrice)+" 元");
            }
        };
        handler.post(r);
    }
}
