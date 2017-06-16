package com.example.administrator.xiangou.mine.mystore.couponmanager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CouponManagerPresenter extends BasePresenterImpl<CouponManagerContract.View> implements CouponManagerContract.Presenter{

    @Override
    public void callFindCoupon(final int did, String condition) {
        addSubscription(mApiService.callFindCoupon(did,condition),
                new BaseSubscriber<ResponseBody>(mView.getContext()) {
                    @Override
                    public void onNext(ResponseBody couponBean) {

                        if (couponBean!=null)
                        try {
                            JSONObject str= new JSONObject(couponBean.string());
                            Log.e("next", "gooooooonNext: "+str );
                            JSONArray data=str.getJSONArray("data");
                            JSONObject state=str.getJSONObject("state");
                            int code=state.getInt("code");
                            CouponBean CouponDatas=new CouponBean();
                            for (int i = 0; i < data.length(); i++) {
                                CouponBean.CouponsBean Bean=new CouponBean.CouponsBean();
                                JSONObject bean=data.getJSONObject(i);
                                int couponid=bean.getInt("couponid");
                                int rests=bean.getInt("rests");
                                int createnum=bean.getInt("createnum");
                                String money=bean.getString("money");
                                String condition=bean.getString("condition");
                                String use_start_time=bean.getString("use_start_time");
                                String use_end_time=bean.getString("use_end_time");
                                Bean.setCouponid(couponid);
                                Bean.setRests(rests);
                                Bean.setCreatenum(createnum);
                                Bean.setMoney(money);
                                Bean.setCondition(condition);
                                Bean.setUse_start_time(use_start_time);
                                Bean.setUse_end_time(use_end_time);
                                CouponDatas.getData().add(Bean);
                            }
                            if (code==200){
                                mView.dataToView(CouponDatas);
                            }else{
                                Toast.makeText(mView.getContext(), "错误码："+code, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        else{
                            mView.dataToView(null);
                        }
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }
                });
    }

    @Override
    public void callAddCoupon(int did, double condition, double money, int createnum, long use_start_time, long use_end_time) {
        addSubscription(mApiService.callAddCoupon(did,condition,money,createnum,use_start_time,use_end_time),
                new BaseSubscriber<ResponseBody>(mView.getContext()) {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Log.e("sssssss", "onNext: "+responseBody.string() );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mView.addCouponSuccess();
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }
                });

    }
}
