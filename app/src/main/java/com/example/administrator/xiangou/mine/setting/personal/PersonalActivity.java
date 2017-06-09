package com.example.administrator.xiangou.mine.setting.personal;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.tool.CustomImageView;
import com.example.administrator.xiangou.tool.ImageUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static okhttp3.MultipartBody.Part.createFormData;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PersonalActivity extends MVPBaseActivity<PersonalContract.View, PersonalPresenter>
        implements PersonalContract.View {
    private RelativeLayout img_setting,nickname,sex;
    private CustomImageView person_img;
    private ImageView back;
    private TextView nickname_Tv,sex_Tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_personal);
        initView();
    }

    private void initView() {
        Log.e("PersonalActivity", "initView: " +getUser().toString());
        img_setting=findContentView(R.id.setting_personal_Rl,true);
        person_img=findContentView(R.id.setting_personal_img);
        nickname=findContentView(R.id.setting_personal_nickname_Rl,true);
        sex=findContentView(R.id.setting_personal_sex_Rl,true);
        back=findContentView(R.id.setting_personal_back,true);
        nickname_Tv=findContentView(R.id.setting_personal_nickname_Tv,false);
        sex_Tv=findContentView(R.id.setting_personal_sex_Tv,false);

        //根据上次记录设初始值
        loadImg(getUser().getHead_pic(),person_img);
        nickname_Tv.setText(getUser().getNickname());
        String sex ;
        switch (getUser().getSex()){
            case 1:
                sex = new String("男");
            case 2:
                sex = new String("女");
            default:
                sex = new String("");
        }
        sex_Tv.setText(sex);
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_personal_Rl:
                setShowatlocation(R.id.setting_personal_layoutRl);
                showPicturePopupWindow(0);
                break;
            case R.id.setting_personal_nickname_Rl:
                startNewUIForResult(NicknameActivity.class,1,"",null);
                break;
            case R.id.setting_personal_sex_Rl:
                startNewUIForResult(SexActivity.class,2,"",null);
                break;
            case R.id.setting_personal_back:
                finish();
                break;
            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null&&resultCode==RESULT_OK){
        Log.e("data", "onActivityResult: "+data.toString()+"\nrequestCode:"+requestCode+"\nresultCode:"+resultCode);
        switch (requestCode){
            case 1:
                setNickName(data);
                break;
            case 2:
                setSex(data);
                break;
            default:
                setImg(data);
                break;
        }
    }
    }
    //设置性别栏状态
    private void setSex( Intent data) {
        sex_Tv.setText(data.getStringExtra("sex"));
    }
    //设置昵称
    private void setNickName( Intent data) {
        nickname_Tv.setText(data.getStringExtra("nickname"));
    }
    //设置头像
    private void setImg( Intent data) {
        String imagePath = "";
            if (data.getData() != null) {//有数据返回直接使用返回的图片地址
                imagePath = ImageUtils.getFilePathByFileUri(this, data.getData());
                Log.e("urlpath", "setImg: " + imagePath+"\n"+data.getData().getScheme());
//                imgpathMap.put(requestCode,imagePath);
                File img = new File(imagePath);
                RequestBody requestbody=RequestBody.create(MediaType.parse("multipart/form-data"),img);
                MultipartBody.Part file = createFormData("head_img",img.getName(),requestbody);
                uploadUserLogo(data,file);//上传图片
            }
//        loadImg(data.getData(),person_img);// 刷新图片
    }

    private void uploadUserLogo(final Intent data, MultipartBody.Part requestbody) {
        Log.e("uploadUserLogo", "uploadUserLogo: "+requestbody.toString() );
        addSubscription(mApiService.uploadUserDetials(getUser().getUser_id(),0,null,requestbody),
                new BaseSubscriber<Captcha>(this) {
                    @Override
                    public void onNext(Captcha captcha) {
                        Log.e("onNext", "onNext: "+captcha.toString());
                        if ( captcha.getState().getCode()==200 ){
                            showToast("头像修改成功！");
                            update(data.getData());
                        }else{
                            showToast("头像修改失败！");
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

    //刷新图片方法
    private void update(Uri uri) {
        ImageUtils.loadLocationImg(getContext(),uri,person_img);
        getSP().saveImgUri(uri);
    }

    @Override
    protected void onDestroy() {
        getSP().upDateUserInfo(getUser().toString());
        super.onDestroy();
    }
}
