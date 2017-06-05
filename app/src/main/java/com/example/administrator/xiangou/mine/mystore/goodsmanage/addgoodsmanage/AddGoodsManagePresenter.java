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
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddGoodsManagePresenter extends BasePresenterImpl<AddGoodsManageContract.View> implements AddGoodsManageContract.Presenter{

    @Override
    public void callIntoAddGoodPage(int did, final int goods_id) {
        addSubscription(mApiService.callIntoAddGoodsPage(did,goods_id),
                new BaseSubscriber<ResponseBody>(mView.getContext()) {
                    @Override
                    public void onNext(ResponseBody responseBody) {
//                        if (ResponseBody.getState().getCode()==200){
//                        if (responseBody!=null)
//                            mView.dataToView(responseBody);
////                        }
//                        if (responseBody!=null)
                        try {
                            JSONObject object=new JSONObject(responseBody.string());
                            Log.e("object", "onNext: "+object.toString() );
                            int code=object.getJSONObject("state").getInt("code");
                            JSONObject data=object.getJSONObject("data");
                            IntoAddGoodPageBean.DataBean dataBean=new  IntoAddGoodPageBean.DataBean();
                            //解析cate属性
                            JSONArray cate=data.getJSONArray("cate");
                            List<IntoAddGoodPageBean.DataBean.CateBean> cateBeans=new ArrayList<IntoAddGoodPageBean.DataBean.CateBean>();
                            for (int i = 0; i <cate.length() ; i++) {
                                JSONObject item= cate.getJSONObject(i);
                                int cat_id=item.getInt("cat_id");
                                IntoAddGoodPageBean.DataBean.CateBean cateBean=new IntoAddGoodPageBean.DataBean.CateBean();
                                cateBean.setCat_id(cat_id);
                                String name=item.getString("name");
                                cateBean.setName(name);
                                cateBeans.add(cateBean);
                            }
                            Log.e("cateBeans", "onNext: "+cateBeans.toString() );
                            dataBean.setCate(cateBeans);
                            //解析model属性
                            JSONArray model=data.getJSONArray("model");
                            List<IntoAddGoodPageBean.DataBean.ModelBean> modelBeans=new ArrayList<IntoAddGoodPageBean.DataBean.ModelBean>();
                            for (int i = 0; i < model.length(); i++) {
                                IntoAddGoodPageBean.DataBean.ModelBean modelBean=new IntoAddGoodPageBean.DataBean.ModelBean();
                                JSONObject item=model.getJSONObject(i);
                                int model_id=item.getInt("model_id");
                                modelBean.setModel_id(model_id);
                                String name=item.getString("name");
                                modelBean.setName(name);
                                modelBeans.add(modelBean);
                            }
                            dataBean.setModel(modelBeans);
                            Log.e("modelBeans", "onNext: "+modelBeans.toString() );
                            //解析sign属性
                            JSONArray sign=data.getJSONArray("sign");
                            List<IntoAddGoodPageBean.DataBean.SignBeanX> signBeans=new ArrayList<IntoAddGoodPageBean.DataBean.SignBeanX>();
                            for (int i = 0; i <sign.length() ; i++) {
                                JSONObject item =sign.getJSONObject(i);
                                IntoAddGoodPageBean.DataBean.SignBeanX signBean=new IntoAddGoodPageBean.DataBean.SignBeanX();
                                int sign_id=item.getInt("sign_id");
                                signBean.setSign_id(sign_id);
                                String name=item.getString("name");
                                signBean.setName(name);
                                signBeans.add(signBean);
                            }

                            Log.e("signBeans", "onNext: "+signBeans.toString() );
                            dataBean.setSign(signBeans);
                            //解析goosinfo属性
                            if (data.get("goodsinfo")!=null&data.getJSONArray("goodsinfo").length()!=0){
                                Log.e("goodinfo", "onNext:不为null " );
                                IntoAddGoodPageBean.DataBean.GoodsinfoBean goodsinfoBean=new IntoAddGoodPageBean.DataBean.GoodsinfoBean();
                                JSONObject goodsinfo=data.getJSONObject("goodsinfo");
                                String goods_name=goodsinfo.getString("goods_name");
                                goodsinfoBean.setGoods_name(goods_name);
                                int store_count=goodsinfo.getInt("store_count");
                                goodsinfoBean.setStore_count(store_count);
                                String market_price=goodsinfo.getString("market_price");
                                goodsinfoBean.setMarket_price(market_price);
                                String shop_price=goodsinfo.getString("shop_price");
                                goodsinfoBean.setShop_price(shop_price);
                                int model_id=goodsinfo.getInt("model_id");
                                goodsinfoBean.setModel_id(model_id);
                                int is_recommend=goodsinfo.getInt("is_recommend");
                                goodsinfoBean.setIs_recommend(is_recommend);
                                int is_new=goodsinfo.getInt("is_new");
                                goodsinfoBean.setIs_new(is_new);
                                List<IntoAddGoodPageBean.DataBean.GoodsinfoBean.SignBean> signs=new ArrayList<IntoAddGoodPageBean.DataBean.GoodsinfoBean.SignBean>();
                                JSONArray sign1=goodsinfo.getJSONArray("sign");
                                for (int i = 0; i <sign1.length() ; i++) {
                                    IntoAddGoodPageBean.DataBean.GoodsinfoBean.SignBean signBean=new IntoAddGoodPageBean.DataBean.GoodsinfoBean.SignBean();
                                    int sign_id=sign1.getJSONObject(i).getInt("sign_id");
                                    signBean.setSign_id(sign_id);
                                    String name=sign1.getJSONObject(i).getString("name");
                                    signBean.setName(name);
                                    signs.add(signBean);
                                }
                                int is_free_shipping=goodsinfo.getInt("is_free_shipping");
                                goodsinfoBean.setIs_free_shipping(is_free_shipping);
                                String fare=goodsinfo.getString("fare");
                                goodsinfoBean.setFare(fare);
                                List<IntoAddGoodPageBean.DataBean.GoodsinfoBean.GoodsImgBean> goodsimgs=new ArrayList<IntoAddGoodPageBean.DataBean.GoodsinfoBean.GoodsImgBean>();
                                JSONArray goods_img=goodsinfo.getJSONArray("goods_img");
                                for (int i = 0; i < goods_img.length(); i++) {
                                    IntoAddGoodPageBean.DataBean.GoodsinfoBean.GoodsImgBean goodsimgBean=new IntoAddGoodPageBean.DataBean.GoodsinfoBean.GoodsImgBean();
                                    int img_id=goods_img.getJSONObject(i).getInt("img_id");
                                    goodsimgBean.setImg_id(img_id);
                                    int goods_id=goods_img.getJSONObject(i).getInt("goods_id");
                                    goodsimgBean.setGoods_id(goods_id);
                                    String image_url=goods_img.getJSONObject(i).getString("image_url");
                                    goodsimgBean.setImage_url(image_url);
                                    goodsimgs.add(goodsimgBean);
                                }

                                dataBean.setGoodsinfo(goodsinfoBean);

                                Log.e("goodsinfoBean", "onNext: "+goodsinfoBean.toString() );
                            }
                            IntoAddGoodPageBean.DataBean.GoodModelBean goodmodelBean=new IntoAddGoodPageBean.DataBean.GoodModelBean();
                            JSONObject good_model=data.getJSONObject("good_model");
                                //数据为空不做操作
//                            dataBean.setGood_model();
                            Log.e("data--------", "onNext: "+dataBean.toString() );
                            if (code==200){
                                mView.dataToView(dataBean);
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
