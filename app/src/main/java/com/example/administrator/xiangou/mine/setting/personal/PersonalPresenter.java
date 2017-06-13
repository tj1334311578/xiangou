package com.example.administrator.xiangou.mine.setting.personal;

import android.net.Uri;
import android.util.Log;

import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.tool.MySharedPreferences;

import okhttp3.MultipartBody;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PersonalPresenter extends BasePresenterImpl<PersonalContract.View> implements PersonalContract.Presenter{

    @Override
    public void uploadUserLogo(final Uri data, MultipartBody.Part requestbody) {
//        Log.e("uploadUserLogo", "uploadUserLogo: "+requestbody.toString() );
        addSubscription(mApiService.uploadUserDetials(getUser().getUser_id(),0,null,requestbody),
                new BaseSubscriber<Captcha>(mView.getContext()) {
                    @Override
                    public void onNext(Captcha captcha) {
                        Log.e("onNext", "onNext: "+captcha.toString());
                        if ( captcha.getState().getCode()==200 ){
                            getSP().saveImgUri(MySharedPreferences.KEY_USERIMG,data);
                            mView.sendSuccessRequest("头像修改成功！");
//                            initImageView(person_img);
                        }else{
                            mView.sendFialRequest("头像修改失败！");
                        }
                    }

                    @Override
                    public void onFinish() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("imgerror", "onError: " +e.toString() );
                    }
                }
        );
    }
}
