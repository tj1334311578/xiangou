package com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage;

import android.content.Context;
import android.util.Log;

import com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean.IntoAddGoodPageBean;
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

public class AddGoodsManagePresenter extends BasePresenterImpl<AddGoodsManageContract.View> implements AddGoodsManageContract.Presenter{

    @Override
    public void callIntoAddGoodPage(int did, int goods_id) {
        addSubscription(mApiService.callIntoAddGoodsPage(did,goods_id),
                new BaseSubscriber<ResponseBody>(mView.getContext()) {
                    @Override
                    public void onNext(ResponseBody responseBody) {
//                        if (ResponseBody.getState().getCode()==200){
                        if (responseBody!=null)
                            mView.dataToView(responseBody);
//                        }
                        try {
                            JSONObject object=new JSONObject(responseBody.string());
                            int code=object.getJSONObject("state").getInt("code");
                            JSONObject data=object.getJSONObject("data");
                            //解析cate属性
                            JSONArray cate=data.getJSONArray("date");
                            for (int i = 0; i <cate.length() ; i++) {
                                JSONObject item= cate.getJSONObject(i);
                                int cat_id=item.getInt("cat_id");
                                String name=item.getString("name");
                            }
                            //解析model属性
                            JSONArray model=object.getJSONArray("model");
                            for (int i = 0; i < model.length(); i++) {
                                JSONObject item=model.getJSONObject(i);
                                int model_id=item.getInt("model_id");
                                String name=item.getString("name");
                            }
                            //解析sign属性
                            JSONArray sign=object.getJSONArray("sign");
                            for (int i = 0; i <sign.length() ; i++) {
                                JSONObject item =sign.getJSONObject(i);
                                int sign_id=item.getInt("sign_id");
                                String name=item.getString("name");
                            }
                            //解析goosinfo属性
                            if (object.get("goodsinfo")!=null){
                                JSONObject goodsinfo=object.getJSONObject("goodsinfo");
                                String goods_name=goodsinfo.getString("goods_name");
                                int store_count=goodsinfo.getInt("store_count");
                                String market_price=goodsinfo.getString("market_price");
                                String shop_price=goodsinfo.getString("shop_price");
                                //// TODO: 2017/6/2 未写完 
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("onError", "onError: "+e.toString() );
                    }
                });
    }
}
