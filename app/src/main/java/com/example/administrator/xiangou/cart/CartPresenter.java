package com.example.administrator.xiangou.cart;


import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.cart.model.CartMergeBean;
import com.example.administrator.xiangou.cart.model.CartMergeItemBean;
import com.example.administrator.xiangou.cart.model.GoodsDealBean;
import com.example.administrator.xiangou.cart.model.ItemStatusBean;
import com.example.administrator.xiangou.cart.model.StoreDealBean;
import com.example.administrator.xiangou.cart.model.StoreStatusBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.tool.ContextUtils;

import java.util.ArrayList;
import java.util.List;

public class CartPresenter extends BasePresenterImpl<CartContract.View> implements CartContract.Presenter{
    private List<CartMergeBean> mMergeBeanList;
    private List<CartMergeItemBean> mMergeItemBeanList,mMergeItemBeanList1,mMergeItemBeanList2;
    private float totalPrice=0;

    @Override
    public void dealAllCheckBox(CompoundButton buttonView, boolean isChecked) {
        Log.e("全选", "onCheckedChanged:全选 " +isChecked);
        totalPrice = 0;//重置合计
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
                    totalPrice += (mMergeBeanList.get(i).getMergeItemBeanList().get(j).getGoodsDealBean().getGoodsPrice()
                            * mMergeBeanList.get(i).getMergeItemBeanList().get(j).getGoodsDealBean().getGoodsCount());
                }
            }
            UpdateRecyclerView(0,mMergeBeanList.size());//更新
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
                UpdateRecyclerView(0,mMergeBeanList.size());//更新
            }
        }
    }

    @Override
    public void dealStoreCheckBox(boolean isChecked, int position, CheckBox mAllGoodsCb) {
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

                    totalPrice += (mMergeBeanList.get(position).getMergeItemBeanList().get(i).getGoodsDealBean().getGoodsPrice()
                            *
                            mMergeBeanList.get(position).getMergeItemBeanList().get(i).getGoodsDealBean().getGoodsCount());
                    Log.e("店铺", "店铺: "+position+" is="+isChecked+" 商品: " +i +"="
                            +mMergeBeanList.get(position).getMergeItemBeanList().get(i).getItemStatusBean().ischeck()+" 现在价格="+totalPrice);

                    mMergeBeanList.get(position).getMergeItemBeanList().get(i).getItemStatusBean().setIscheck(true);
                }
            }
        }else {
            //解决--点击取消选择商品时，店铺全选按钮取消选择状态，会变成全不选的bug
            if (allItemSelect(position) == mMergeBeanList.get(position).getMergeItemBeanList().size()){
                Log.e("店铺", "店铺:"+position+" all to false"  );
                for (int i = 0; i < mMergeBeanList.get(position).getMergeItemBeanList().size(); i++) {
                    if (mMergeBeanList.get(position).getMergeItemBeanList().get(i).getItemStatusBean().ischeck()){

                        totalPrice -= (mMergeBeanList.get(position).getMergeItemBeanList().get(i).getGoodsDealBean().getGoodsPrice()
                                *
                                mMergeBeanList.get(position).getMergeItemBeanList().get(i).getGoodsDealBean().getGoodsCount());
                        Log.e("店铺", "店铺: "+position+" is="+isChecked+" 商品: " +i +"="
                                +mMergeBeanList.get(position).getMergeItemBeanList().get(i).getItemStatusBean().ischeck()+" 现在价格="+totalPrice);
                        mMergeBeanList.get(position).getMergeItemBeanList().get(i).getItemStatusBean().setIscheck(false);
                    }
                }
            }
        }

        UpdateRecyclerView(position,1);
    }

    @Override
    public void dealGoodsCheckBox(boolean isItemChecked, int parentposition, int chaildposition) {
        //保存商品点击状态
        mMergeBeanList.get(parentposition).getMergeItemBeanList().get(chaildposition).getItemStatusBean().setIscheck(isItemChecked);
        //通知店铺选择的状态
        if ( allItemSelect(parentposition) == mMergeBeanList.get(parentposition).getMergeItemBeanList().size()){
            Log.e("商品:","店铺="+parentposition+":all商品 check..."+allItemSelect(parentposition));
            mMergeBeanList.get(parentposition).getStoreStatusBean().setIsCheck(true);
        }else {
            mMergeBeanList.get(parentposition).getStoreStatusBean().setIsCheck(false);
        }
        if (mMergeBeanList.get(parentposition).getMergeItemBeanList().get(chaildposition).getItemStatusBean().ischeck()) {
            totalPrice += (mMergeBeanList.get(parentposition).getMergeItemBeanList().get(chaildposition).getGoodsDealBean().getGoodsPrice()
                    *
                    mMergeBeanList.get(parentposition).getMergeItemBeanList().get(chaildposition).getGoodsDealBean().getGoodsCount());
        } else {
            totalPrice -= (mMergeBeanList.get(parentposition).getMergeItemBeanList().get(chaildposition).getGoodsDealBean().getGoodsPrice()
                    *
                    mMergeBeanList.get(parentposition).getMergeItemBeanList().get(chaildposition).getGoodsDealBean().getGoodsCount());
        }
        UpdateRecyclerView(parentposition,1);
    }


    /**
     *更新Recycleyview刷新报错问题
     */
    private void UpdateRecyclerView(final int startpos, final int count) {
        mView.toUpdataView(startpos,count,"￥ "+ ContextUtils.S2places(totalPrice)+" 元");
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

    private List<CartMergeBean> initData() {
        mMergeBeanList = new ArrayList<>();
        mMergeItemBeanList = new ArrayList<>();
        mMergeItemBeanList1 = new ArrayList<>();
        mMergeItemBeanList2 = new ArrayList<>();
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
        //        allCbBean1.setIsCheck(false);
        StoreStatusBean allCbBean2 = new StoreStatusBean();
        //        allCbBean2.setIsCheck(false);
        StoreStatusBean allCbBean3 = new StoreStatusBean();
        //        allCbBean3.setIsCheck(false);

        CartMergeBean cartMergeBean1 = new CartMergeBean(storeDealBean1,allCbBean1,mMergeItemBeanList);
        CartMergeBean cartMergeBean2 = new CartMergeBean(storeDealBean2,allCbBean2,mMergeItemBeanList1);
        CartMergeBean cartMergeBean3 = new CartMergeBean(storeDealBean3,allCbBean3,mMergeItemBeanList2);
        mMergeBeanList.add(cartMergeBean1);
        mMergeBeanList.add(cartMergeBean2);
        mMergeBeanList.add(cartMergeBean3);

        return mMergeBeanList;
    }

}
