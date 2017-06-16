package com.example.administrator.xiangou.mine.setting.modifypassword;

import android.content.Context;
import android.util.Log;

import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ModifyPasswordPresenter extends BasePresenterImpl<ModifyPasswordContract.View> implements ModifyPasswordContract.Presenter{

    @Override
    public void modifyPassword(int user_id, String oldpassword, String password) {
        addSubscription(mApiService.modifyPassword(user_id,oldpassword,password),
                new BaseSubscriber<ResponseBody>(mView.getContext()) {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            JSONObject str=new JSONObject(responseBody.string());
                            JSONObject state =str.getJSONObject("state");
                            int code=state.getInt("code");
                            mView.getCode(code);
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
