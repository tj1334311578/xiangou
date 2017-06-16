package com.example.administrator.xiangou.goodsdetails.simplegoodsdetails;

import android.util.Log;

import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean.SimpleGoodsDetialBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Comment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SimpleGoodsDetailsPresenter extends BasePresenterImpl<SimpleGoodsDetailsContract.View> implements SimpleGoodsDetailsContract.Presenter{
    @Override
    public void dealSimpleDetailsCall(int id,int user_id,String map_x,String map_y,int type) {
        //用于请求的接收类服务器数据不全无法获取code：1000未知错误
        addSubscription(mApiService.callSimpleGoodsDetails(id,type),
                new BaseSubscriber<ResponseBody>(mView.getContext()) {
            @Override
            public void onNext(ResponseBody responseBody) {
//                Log.e("onNext", "onNext: "+tempBean.toString() +"\nCode:"+tempBean.getState().getCode());
//                if (tempBean.getState().getCode()==200){
//                    mView.sendDataBeanToView(tempBean);
//                }
                try {
                    String reset=responseBody.string();
                    JSONObject str=new JSONObject(reset);
                    SimpleGoodsDetialBean body=new SimpleGoodsDetialBean();
                    //解析state属性
                    JSONObject state=str.getJSONObject("state");
                    SimpleGoodsDetialBean.StateBean stateBean=new SimpleGoodsDetialBean.StateBean();
                    stateBean.setCode(state.getInt("code"));
                    stateBean.setMsg(state.getString("msg"));
                    stateBean.setDebugMsg(state.getString("debugMsg"));
                    stateBean.setUrl(state.getString("url"));
                    body.setState(stateBean);
                    //解析data属性
                    JSONObject data=str.getJSONObject("data");
                    SimpleGoodsDetialBean.DataBean dataBean=new SimpleGoodsDetialBean.DataBean();
                    dataBean.setGoods_id(data.getInt("goods_id"));
                    dataBean.setOriginal_img(data.getString("original_img"));
                    dataBean.setGoods_name(data.getString("goods_name"));
                    dataBean.setMarket_price(data.getString("market_price"));
                    dataBean.setShop_price(data.getString("shop_price"));
                    dataBean.setStore_count(data.getInt("store_count"));
                    dataBean.setSales_sum(data.getInt("sales_sum"));
                    dataBean.setName(data.getString("name"));
                    dataBean.setProm_type(data.getInt("prom_type"));
                    dataBean.setProm_id(data.getInt("prom_id"));
                    dataBean.setLogo(data.getString("logo"));
                    dataBean.setStore_id(data.getInt("store_id"));
                    dataBean.setDistance(data.getInt("distance"));
                    //goods_img[]
                    Log.e("data", "onNext: "+data.get("goods_img") );
                    if (data.get("goods_img").toString().toCharArray().length>2 ){
                        Log.e("data", "onNext: "+"++++++++++++++++++");
                        List<SimpleGoodsDetialBean.DataBean.GoodsImgBean> GoodsImgs=new ArrayList<SimpleGoodsDetialBean.DataBean.GoodsImgBean>();
                        JSONArray goods_imgArray=data.getJSONArray("goods_img");
                        for (int i = 0; i < goods_imgArray.length(); i++) {
                            SimpleGoodsDetialBean.DataBean.GoodsImgBean GoodsImg=new SimpleGoodsDetialBean.DataBean.GoodsImgBean();
                            GoodsImg.setGoods_id(goods_imgArray.getJSONObject(i).getInt("img_id"));
                            GoodsImg.setImg_id(goods_imgArray.getJSONObject(i).getInt("goods_id"));
                            GoodsImg.setImage_url(goods_imgArray.getJSONObject(i).getString("image_url"));
                            GoodsImgs.add(GoodsImg);
                        }
                        dataBean.setGoods_img(GoodsImgs);
                    }else
                    {
                        Log.e("data", "onNext: "+"------------------");
                    }

                    dataBean.setFavorite(data.getInt("favorite"));
//
                    if(data.get("filter_spec").toString().toCharArray().length>2){
                        Log.e("filter_spc", "onNext: filter_spec为类" );
                        SimpleGoodsDetialBean.DataBean.FilterSpecBean specBean=new SimpleGoodsDetialBean.DataBean.FilterSpecBean();
                        Log.e("go", "ooｉｓ　ｃｌａｓｓ  onNext: " );
                        JSONObject SpecBean = data.getJSONObject("filter_spec");
                        Iterator<String> iterator = SpecBean.keys();
//                        //获取filter_spec属性
                        while (iterator.hasNext()) {
                            String key = iterator.next();
                            if (key.equals("颜色")) {
                                JSONArray colorObj = SpecBean.getJSONArray(key);
                                List<SimpleGoodsDetialBean.DataBean.FilterSpecBean.颜色Bean> colorBeans = new ArrayList<SimpleGoodsDetialBean.DataBean.FilterSpecBean.颜色Bean>();
                                for (int j = 0; j < colorObj.length(); j++) {
                                    SimpleGoodsDetialBean.DataBean.FilterSpecBean.颜色Bean colorBean = new SimpleGoodsDetialBean.DataBean.FilterSpecBean.颜色Bean();
                                    JSONObject colorItemObj = colorObj.getJSONObject(j);
                                    int item_id = colorItemObj.getInt("item_id");
                                    String item = colorItemObj.getString("item");
                                    colorBean.setItem_id(item_id);
                                    colorBean.setItem(item);
                                    colorBeans.add(colorBean);
                                }
                                specBean.set颜色(colorBeans);
                            } else {
                                JSONArray sizeObj = SpecBean.getJSONArray(key);
                                List<SimpleGoodsDetialBean.DataBean.FilterSpecBean.尺寸Bean> sizeBeans = new ArrayList<SimpleGoodsDetialBean.DataBean.FilterSpecBean.尺寸Bean>();
                                for (int j = 0; j < sizeObj.length(); j++) {
                                    SimpleGoodsDetialBean.DataBean.FilterSpecBean.尺寸Bean sizeBean = new SimpleGoodsDetialBean.DataBean.FilterSpecBean.尺寸Bean();
                                    JSONObject sizeItemObj = sizeObj.getJSONObject(j);
                                    sizeBean.setItem_id(sizeItemObj.getInt("item_id"));
                                    sizeBean.setItem(sizeItemObj.getString("item"));
                                    sizeBeans.add(sizeBean);
                                }
                                specBean.set尺寸(sizeBeans);
                            }
                        }
                        dataBean.setFilter_spec(specBean);
                    }else{
                        Log.e("filter_spc", "onNext: filter_spec为空集合" );
                    }

                    //goods_attr属性有可能没有
                    if (data.get("goods_attr").toString().toCharArray().length>2){
                        JSONArray goods_attrNarray=data.getJSONArray("goods_attr");
                        List<SimpleGoodsDetialBean.DataBean.GoodsAttrBean> goodsAttrs=new ArrayList<SimpleGoodsDetialBean.DataBean.GoodsAttrBean>();
                        for (int i = 0; i < goods_attrNarray.length(); i++) {
                            SimpleGoodsDetialBean.DataBean.GoodsAttrBean goodsAttrBean=new SimpleGoodsDetialBean.DataBean.GoodsAttrBean();
                            goodsAttrBean.setAttr_name(goods_attrNarray.getJSONObject(i).getString("attr_name"));
                            goodsAttrBean.setAttr_value(goods_attrNarray.getJSONObject(i).getString("attr_value"));
                            goodsAttrs.add(goodsAttrBean);
                        }
                        dataBean.setGoods_attr(goodsAttrs);
                    }else{
                        Log.e("goods_attr", "onNext: goods_attr为空集合" );
                    }
                    //spec_goods-count属性
                        dataBean.setIs_favorite(data.getInt("is_favorite"));
                        dataBean.setCart_num(data.getInt("cart_num"));
                    //coupon属性
                    if (data.get("coupon").toString().toCharArray().length>2){
                        Log.e("coupon", "onNext:有coupon " );
                        JSONArray CouponsArray= data.getJSONArray("coupon");
                        List<SimpleGoodsDetialBean.DataBean.CouponBean> coupons=new ArrayList<SimpleGoodsDetialBean.DataBean.CouponBean>();
                        for (int i = 0; i < CouponsArray.length(); i++) {
                            SimpleGoodsDetialBean.DataBean.CouponBean couponBean=new SimpleGoodsDetialBean.DataBean.CouponBean();
                            couponBean.setCoupon_id(CouponsArray.getJSONObject(i).getInt("coupon_id"));
                            couponBean.setMoney(CouponsArray.getJSONObject(i).getString("money"));
                            couponBean.setCondition(CouponsArray.getJSONObject(i).getString("condition"));
//                            couponBean.setUse_start_time(CouponsArray.getJSONObject(i).getString("use_start_time"));//为空null报1000错误
//                            couponBean.setUse_end_time(CouponsArray.getJSONObject(i).getString("use_end_time"));
                            couponBean.setIs_get(CouponsArray.getJSONObject(i).getInt("is_get"));
                            coupons.add(couponBean);
                        }
                        dataBean.setCoupon(coupons);
                    }else{
                        Log.e("coupon", "onNext:无coupon " );
                    }
                        dataBean.setScore((float) Double.parseDouble(data.getString("score")));
                        dataBean.setStore_total_sale(data.getString("store_total_sale"));
                        dataBean.setStore_follow(data.getInt("store_follow"));
                        dataBean.setFare(data.getInt("fare"));
                        dataBean.setComment_counts(data.getInt("comment_counts"));
//                        //comment有可能没有；
                    if (data.get("comment").toString().toCharArray().length>2){
                        Log.e("comment", "onNext:有comment属性 " );
                        JSONArray CommentArray=data.getJSONArray("comment");
                        List<SimpleGoodsDetialBean.DataBean.CommentBean> comments=new ArrayList<SimpleGoodsDetialBean.DataBean.CommentBean>();
                        for (int i = 0; i < CommentArray.length(); i++) {
                            SimpleGoodsDetialBean.DataBean.CommentBean CommentBean=new SimpleGoodsDetialBean.DataBean.CommentBean();
                            CommentBean.setComment_id(CommentArray.getJSONObject(i).getInt("comment_id"));
                            CommentBean.setContent(CommentArray.getJSONObject(i).getString("content"));
                            CommentBean.setUsername(CommentArray.getJSONObject(i).getString("username"));
                            //img[]
                            if (CommentArray.getJSONObject(i).get("img").toString().toCharArray().length>2){//存在img[]
                                List<String> imgs=new ArrayList<String>();
                                Log.e("imgs", "onNext: "+CommentArray.getJSONObject(i).get("img").toString());
                                String imgstr=CommentArray.getJSONObject(i).get("img").toString().replaceAll("\\[\"","");
                                imgstr=imgstr.replaceAll("\"\\]","");
                                imgstr=imgstr.replaceAll("\\\\","");//四个反斜杠表示一个反斜杠
                                String [] ims=imgstr.split("\",\"");
                                Log.e("ims", "onNext: "+ Arrays.deepToString(ims) );
                                for (int j = 0; j < ims.length; j++) {
                                    imgs.add(ims[j]);
                                }
                                CommentBean.setImg(imgs);
                            }else{
                                Log.e("img[]", "onNext: img[]为空" );
                            }
                            CommentBean.setAdd_time(CommentArray.getJSONObject(i).getString("add_time"));
                            CommentBean.setOrder_id(CommentArray.getJSONObject(i).getInt("order_id"));
                            CommentBean.setHead_pic(CommentArray.getJSONObject(i).getString("head_pic"));
                            //spec:""
                            comments.add(CommentBean);
                        }
                        dataBean.setComment(comments);
                    }else{
                        Log.e("comment", "onNext:没有comment属性 " );
                    }
                        body.setData(dataBean);
                        if (body.getState().getCode()==200){
                            mView.sendDataBeanToView(body);
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
