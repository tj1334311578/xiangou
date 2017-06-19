package com.example.administrator.xiangou.goods_sort.comprehensive;

import android.util.Log;

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

public class ComprehensivePresenter extends BasePresenterImpl<ComprehensiveContract.View> implements ComprehensiveContract.Presenter{
    @Override
    public void callClassificationSort(int cat_id,int page_no,int is_new,String name,String map_x,String map_y,String sort,String sort_asc) {
        addSubscription(mApiService.callClassificationSort(cat_id,page_no,is_new,name,map_x,map_y,sort,sort_asc),
                new BaseSubscriber<ResponseBody>(mView.getContext()) {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            JSONObject str=new JSONObject(responseBody.string());
                            JSONObject state=str.getJSONObject("state");
                            SortBean sortBean=new SortBean();
                            //sortBean的state属性
                            SortBean.StateBean stateBean=new SortBean.StateBean();
                            stateBean.setCode(state.getInt("code"));
                            stateBean.setDebugMsg(state.getString("debugMsg"));
                            stateBean.setMsg(state.getString("msg"));
                            stateBean.setUrl(state.getString("url"));
                            sortBean.setState(stateBean);
                            List<SortBean.DataBean> dataBeans=new ArrayList<SortBean.DataBean>();
                            JSONArray dataArray=str.getJSONArray("data");
                            for (int i = 0; i <dataArray.length() ; i++) {
                                SortBean.DataBean dataBean=new SortBean.DataBean();
                                dataBean.setGoods_id(dataArray.getJSONObject(i).getInt("goods_id"));
                                dataBean.setGoods_name(dataArray.getJSONObject(i).getString("goods_name"));
                                dataBean.setOriginal_img(dataArray.getJSONObject(i).getString("original_img"));
                                //sign判断是否有该属性
                                if (dataArray.getJSONObject(i).getJSONArray("sign").toString().toCharArray().length>2) {
                                    JSONArray signs = dataArray.getJSONObject(i).getJSONArray("sign");
                                    List<SortBean.ItemBean> itemBeans=new ArrayList<SortBean.ItemBean>();
                                    for (int j = 0; j <signs.length() ; j++) {
                                        SortBean.ItemBean itemBean=new SortBean.ItemBean();
                                        itemBean.setItem(signs.getJSONObject(j).getString("name"));
                                        itemBeans.add(itemBean);
                                    }
                                    dataBean.setSign(itemBeans);
                                }

                                dataBean.setIs_new(dataArray.getJSONObject(i).getInt("is_new"));
                                dataBean.setShop_price(dataArray.getJSONObject(i).getString("shop_price"));
                                dataBean.setDistance(dataArray.getJSONObject(i).getInt("distance"));
                                dataBeans.add(dataBean);
                            }
                            sortBean.setData(dataBeans);
                            if (sortBean.getState().getCode()==200){
                                mView.sortDataToView(sortBean);
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
