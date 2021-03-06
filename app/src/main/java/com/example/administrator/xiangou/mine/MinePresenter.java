package com.example.administrator.xiangou.mine;


import android.util.Log;

import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.tool.ContextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;


public class MinePresenter extends BasePresenterImpl<MineContract.View> implements MineContract.Presenter{

    @Override
    public void IDlogin(String userName, String password) {
//        Log.e("minep", "enter：IDlogin "+userName+" p="+password+" --pwd: "+ ContextUtils.MD5(password));
        if (userName!=null&&password!=null) {
            addSubscription( mApiService.loginID(userName, ContextUtils.MD5(password)),
                    new BaseSubscriber<LoginBean>(mView.getContext()) {
                        @Override
                        public void onNext(LoginBean loginBean) {
                            switch (loginBean.getState().getCode()) {
                                case 200:
                                    if (loginBean.getData() != null) {
                                        if (getUser().changeUser(loginBean.getData())) {
                                            Log.e("minep", "enter：onNext \n"+getUser().toString()+
                                                    "\n"+loginBean.getData().toString()
                                            );
                                            setbUserBySP(loginBean.getData());
                                            getSP().upDateUserInfo(getUser().toString());
                                            mView.ReLoginidSuccess(loginBean.getData());
                                        }
                                        //                                            upDateUserInfo(loginBean.getData().toString());
                                    }
                                    break;
//                                case 100:
//                                default:
//                                    mView.sendFialRequest(loginBean.getState().getMsg());
//                                    break;
                            }
                        }

                        @Override
                        public void onFinish() {
//                            mView.hideLoading();
                        }

                        @Override
                        public void onError(ExceptionHandle.ResponeThrowable e) {
                            Log.e("minep", e.code + " onError：" + e.getMessage());
                            if (e.code == 1000)
                                mView.sendFialRequest("账号或密码错误");
                        }
                    }
            );
        }
    }

    @Override
    public void ceshi(String channel, String order_sn, int amount) {
        addSubscription(mApiService.callceshiApi(channel,order_sn,amount),
                new BaseSubscriber<ResponseBody>(mView.getContext()) {

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Log.e("minep", "onNext: "+responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
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
    public void callsigns(int user_id) {
        addSubscription(mApiService.callSign(user_id),
                new BaseSubscriber<ResponseBody>(mView.getContext()) {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        if (responseBody!=null)
                            try {
                                JSONObject str=new JSONObject(responseBody.string());
                                JSONObject state=str.getJSONObject("state");
                                int code =state.getInt("code");
                                int value=0;
                                if (code==200){
                                    value=str.getInt("data");
                                }
                                mView.dataToView(code,value);
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
