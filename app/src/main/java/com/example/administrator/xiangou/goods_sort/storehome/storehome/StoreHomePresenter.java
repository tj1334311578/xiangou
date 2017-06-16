package com.example.administrator.xiangou.goods_sort.storehome.storehome;


import android.util.Log;
import android.widget.Toast;

import com.example.administrator.xiangou.goods_sort.storehome.HomePageBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StoreHomePresenter extends BasePresenterImpl<StoreHomeContract.View> implements StoreHomeContract.Presenter{
    @Override
    public void dealStoreHomeCall(int did, int user_id) {

        Toast.makeText(mView.getContext(), "dealStoreHomeCall", Toast.LENGTH_SHORT).show();
        mView.showLoading();
        addSubscription(mApiService.callHomePagerData(did, user_id),
                new BaseSubscriber<ResponseBody>(mView.getContext()) {
                    @Override
                    public void onNext(ResponseBody body) {
//                        if (dataBean.getState().getCode()==200){
//                            Log.e("storedata", "onNext: " + dataBean.getData().toString());
//                            mView.sendDataBeanToView(dataBean);
//                        }
                        HomePageBean homeBean=new HomePageBean();
                        try {
                            JSONObject str=new JSONObject(body.string());
                            JSONObject data=str.getJSONObject("data");
                            JSONObject state=str.getJSONObject("state");
                            //获取code中的属性
                            HomePageBean.StateBean stateBean=new HomePageBean.StateBean();
                            stateBean.setCode(state.getInt("code"));
                            stateBean.setDebugMsg(state.getString("debugMsg"));
                            stateBean.setMsg(state.getString("msg"));
                            stateBean.setUrl(state.getString("url"));
                            homeBean.setState(stateBean);
                            //获取data属性
                            HomePageBean.DataBean dataBean=new HomePageBean.DataBean();
                            dataBean.setLogo(data.getString("logo"));
                            dataBean.setIs_follow(data.getInt("is_follow"));
                            dataBean.setName(data.getString("name"));
//                            Log.e("total_commets", "onNext: "+data.getInt("total_commets"));
//                            dataBean.setTotal_commets(data.getInt("total_commets"));
//                            dataBean.setTotal_time(data.getInt("total_time"));
                            dataBean.setScore(data.getString("score"));
                            dataBean.setFollow(data.getInt("follow"));
                            //homepagebean 没有goods_banner属性
                            dataBean.setBanner(data.getString("banner"));
                            dataBean.setTotal_sale(data.getInt("total_sale"));
                            //获取goods_list集合
                            JSONArray goods_list=data.getJSONArray("goods_list");
                            List<HomePageBean.DataBean.GoodsListBean> goodsList =new ArrayList<HomePageBean.DataBean.GoodsListBean>();
                            for (int i = 0; i < goods_list.length(); i++) {
                                HomePageBean.DataBean.GoodsListBean  goodsListBean =new HomePageBean.DataBean.GoodsListBean();
                                JSONObject good=goods_list.getJSONObject(i);
                                goodsListBean.setGoods_id(good.getInt("goods_id"));
                                goodsListBean.setGoods_name(good.getString("goods_name"));
                                goodsListBean.setMarket_price(good.getString("market_price"));
                                goodsListBean.setShop_price(good.getString("shop_price"));
                                goodsListBean.setOriginal_img(good.getString("original_img"));
                                goodsListBean.setIs_new(good.getInt("is_new"));
                                goodsListBean.setSales_sum(good.getInt("sales_sum"));
                                goodsList.add(goodsListBean);
                            }
                            dataBean.setGoods_list(goodsList);
                            //获取coupon属性
                            JSONArray coupon=data.getJSONArray("coupon");
                            List<HomePageBean.DataBean.CouponBean> listCoupons=new ArrayList<HomePageBean.DataBean.CouponBean>();
                            for (int i = 0; i < coupon.length(); i++) {
                                JSONObject couponObj=coupon.getJSONObject(i);
                                HomePageBean.DataBean.CouponBean couponBean=new HomePageBean.DataBean.CouponBean();
                                couponBean.setId(couponObj.getInt("id"));
                                couponBean.setName(couponObj.getString("name"));
                                couponBean.setMoney(couponObj.getString("money"));
                                couponBean.setCondition(couponObj.getString("condition"));
                                couponBean.setCreatenum(couponObj.getInt("createnum"));
                                couponBean.setSend_num(couponObj.getInt("send_num"));
                                couponBean.setUse_num(couponObj.getInt("use_num"));
//                                if (couponObj.get("send_start_time")!=null)
//                                    couponBean.setSend_start_time(couponObj.getInt("send_start_time"));
//                                if (couponObj.get("send_end_time")!=null)
//                                    couponBean.setSend_end_time(couponObj.getInt("send_end_time"));
//                                    couponBean.setUse_start_time(couponObj.getInt("use_start_time"));
//                                    couponBean.setUse_end_time(couponObj.getInt("use_end_time"));
//                                    couponBean.setAdd_time(couponObj.getInt("use_start_time"));
                                    couponBean.setStore_id(couponObj.getInt("store_id"));
                                    couponBean.setIs_del(couponObj.getInt("is_del"));
                                    couponBean.setIs_get(couponObj.getInt("is_get"));
                                listCoupons.add(couponBean);
                            }
                            dataBean.setCoupon(listCoupons);
                            homeBean.setData(dataBean);
                            if (stateBean.getCode()==200) {
                                mView.sendDataBeanToView(homeBean);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFinish() {
                            mView.hideLoading();
                        Toast.makeText(mView.getContext(), "onFinish", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("error", "onError: "+e.toString() );
                            mView.sendFialRequest(e.getMessage());
                    }
                }
        );
    }
}
