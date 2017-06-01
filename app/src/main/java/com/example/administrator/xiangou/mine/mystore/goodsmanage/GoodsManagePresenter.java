package com.example.administrator.xiangou.mine.mystore.goodsmanage;

import android.content.Context;
import android.util.Log;

import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.google.gson.JsonParser;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class GoodsManagePresenter extends BasePresenterImpl<GoodsManageContract.View> implements GoodsManageContract.Presenter{

    @Override
    public void callGoodsManagerList(int did, String key_word, int page_no, int type) {
        addSubscription(mApiService.callStoreGoodsList(did,key_word,page_no,type),
                new BaseSubscriber<ResponseBody>(mView.getContext()) {
                    @Override
                    public void onNext(ResponseBody body) {
//
                        try {
                            JSONObject ob=new JSONObject(body.string());
                            Log.e("body", "onNext: "+ob.toString());
                            JSONArray data=ob.getJSONArray("data");
                            JSONObject state=ob.getJSONObject("state");
                            int code =state.getInt("code");
                            List<GoodsitemBean.DataBean> dataList = new ArrayList<GoodsitemBean.DataBean>();
                            Log.e("data.length", "onNext: "+data.length() );
                            for (int i = 0; i <data.length() ; i++) {
                                Log.e("spec", "onNext:第一层for循环 " );
                                GoodsitemBean.DataBean bean = new GoodsitemBean.DataBean();
                                JSONObject obj=data.getJSONObject(i);
                                bean.setGoods_id((Integer) obj.get("goods_id"));
                                bean.setGoods_name((String) obj.get("goods_name"));
                                bean.setMarket_price((String) obj.get("market_price"));
                                bean.setShop_price((String) obj.get("shop_price"));
                                bean.setStore_count((Integer) obj.get("store_count"));
                                bean.setOriginal_img((String) obj.get("original_img"));
                                bean.setIs_on_sale((Integer) obj.get("is_on_sale"));
                                Log.e("for", "onNext: "+i);
                                if (obj.get("spec") instanceof JSONObject){
                                    Log.e("spec", "onNext:spec是类 " );
                                    Spec2Bean specbean= new Spec2Bean();
                                    JSONObject spec_obj=obj.getJSONObject("spec");

                                    Iterator<?> iterator = spec_obj.keys();// 应用迭代器Iterator 获取所有的key值
//                                    List<String> keys=new ArrayList<String>();
                                    while (iterator.hasNext()){//遍历每个key
                                        String key= (String) iterator.next();
//                                        keys.add(key);
                                        //切记用optJSONObject();用getJSONObject()在为null的时候会报错哦！！！
                                        if (key.equals("颜色")){
                                            JSONArray color=spec_obj.getJSONArray(key);
                                            List<Spec2Bean.颜色Bean> colors=new ArrayList<Spec2Bean.颜色Bean>();
                                            for (int j = 0; j <color.length() ; j++) {
                                                Log.e("for", "颜色 j++"+j);
                                                Spec2Bean.颜色Bean colorBean=new Spec2Bean.颜色Bean();
                                                JSONObject colorobj= (JSONObject) color.get(j);
                                                colorBean.setItem_id((Integer) colorobj.get("item_id"));
                                                colorBean.setItem((String) colorobj.get("item"));
                                                colors.add(colorBean);
                                            }
                                            specbean.set颜色BeanList(colors);
                                        }else if (key.equals("尺码")){
                                            JSONArray size=spec_obj.getJSONArray(key);
                                            List<Spec2Bean.尺码Bean> sizes=new ArrayList<Spec2Bean.尺码Bean>();
                                            for (int z = 0; z <size.length() ; z++) {
                                                Log.e("for", "尺寸 z++"+z);
                                                Spec2Bean.尺码Bean sizeBean=new Spec2Bean.尺码Bean();
                                                JSONObject sizeobj= (JSONObject) size.get(z);
                                                sizeBean.setItem_id((Integer) sizeobj.get("item_id"));
                                                sizeBean.setItem((String) sizeobj.get("item"));
                                                sizes.add(sizeBean);
                                            }
                                            specbean.set尺码BeanList(sizes);
                                        }
                                        else if (key.equals("鞋码")){
                                            JSONArray size=spec_obj.getJSONArray(key);
                                            List<Spec2Bean.尺码Bean> sizes=new ArrayList<Spec2Bean.尺码Bean>();
                                            for (int z = 0; z <size.length() ; z++) {
                                                Log.e("for", "尺寸 z++"+z);
                                                Spec2Bean.尺码Bean sizeBean=new Spec2Bean.尺码Bean();
                                                JSONObject sizeobj= (JSONObject) size.get(z);
                                                sizeBean.setItem_id((Integer) sizeobj.get("item_id"));
                                                sizeBean.setItem((String) sizeobj.get("item"));
                                                sizes.add(sizeBean);
                                            }
                                            specbean.set尺码BeanList(sizes);
                                        }
                                    }
//                                    Log.e("spec", "onNext:类 "+spec_obj.toString() );
//                                    JSONArray color=spec_obj.getJSONArray("颜色");
//                                    JSONArray size=spec_obj.getJSONArray("尺码");
//                                    List<Spec2Bean.尺码Bean> sizes=new ArrayList<Spec2Bean.尺码Bean>();
//                                    List<Spec2Bean.颜色Bean> colors=new ArrayList<Spec2Bean.颜色Bean>();
//                                    Log.e("for", "颜色 "+i);
                                    //解析颜色
//                                    for (int j = 0; j <color.length() ; j++) {
//                                        Log.e("for", "颜色 j++"+j);
//                                        Spec2Bean.颜色Bean colorBean=new Spec2Bean.颜色Bean();
//                                        JSONObject colorobj= (JSONObject) color.get(j);
//                                        colorBean.setItem_id((Integer) colorobj.get("item_id"));
//                                        colorBean.setItem((String) colorobj.get("item"));
//                                        colors.add(colorBean);
//                                    }
//                                    Log.e("for", "尺寸 "+i);
                                    //解析尺寸
//                                    for (int z = 0; z <size.length() ; z++) {
//                                        Log.e("for", "尺寸 z++"+z);
//                                        Spec2Bean.尺码Bean sizeBean=new Spec2Bean.尺码Bean();
//                                        JSONObject sizeobj= (JSONObject) size.get(z);
//                                        sizeBean.setItem_id((Integer) sizeobj.get("item_id"));
//                                        sizeBean.setItem((String) sizeobj.get("item"));
//                                        sizes.add(sizeBean);
//                                    }
//                                    specbean.set颜色BeanList(colors);
//                                    specbean.set尺码BeanList(sizes);
                                    bean.setSpec2Bean(specbean);
//                                    Log.e("for", "lei++ "+i);
                                } else if (obj.get("spec") instanceof JSONArray){//
                                    Log.e("spec", "onNext:spec是集合" );
                                    List<GoodsitemBean.DataBean.SpecBean> spec=new ArrayList<GoodsitemBean.DataBean.SpecBean>();
                                    //存放数组
                                    JSONArray specarray= (JSONArray) obj.get("spec");
                                    for (int j = 0; j <specarray.length() ; j++) {
                                        GoodsitemBean.DataBean.SpecBean SpecBean=new GoodsitemBean.DataBean.SpecBean();
                                        JSONObject specobj= (JSONObject) specarray.get(j);
                                        SpecBean.setSpec_id((Integer) specobj.get("spec_id"));
                                        SpecBean.setName((String) specobj.get("name"));
                                        SpecBean.setItem_id((Integer) specobj.get("item_id"));
                                        SpecBean.setItem((String) specobj.get("item"));
                                        spec.add(SpecBean);
                                    }
                                  bean.setSpec(spec);
                                }
                                dataList.add(bean);//存放data数据
                            }

                            Log.e("data", "onNext: "+dataList.toString() );
                            if (code==200)
                                mView.dataToView(dataList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
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
