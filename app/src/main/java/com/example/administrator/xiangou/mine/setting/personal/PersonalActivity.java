package com.example.administrator.xiangou.mine.setting.personal;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.net.RetrofitClient;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.CustomImageView;
import com.example.administrator.xiangou.tool.ImageUtils;

import org.w3c.dom.Text;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PersonalActivity extends MVPBaseActivity<PersonalContract.View, PersonalPresenter> implements PersonalContract.View {
    private RelativeLayout img_setting,nickname,sex;
    private CustomImageView person_img;
    private ImageView back;
    private TextView nickname_Tv,sex_Tv;
    private XianGouApiService mApiService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_personal);
        mApiService = RetrofitClient.getInstance(this).create(XianGouApiService.class);
        initView();
    }

    private void initView() {
        img_setting=findContentView(R.id.setting_personal_Rl,true);
        person_img=findContentView(R.id.setting_personal_img);
        nickname=findContentView(R.id.setting_personal_nickname_Rl,true);
        sex=findContentView(R.id.setting_personal_sex_Rl,true);
        back=findContentView(R.id.setting_personal_back,true);
        nickname_Tv=findContentView(R.id.setting_personal_nickname_Tv,false);
        sex_Tv=findContentView(R.id.setting_personal_sex_Tv,false);
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
                startNewUIForResult(NicknameActivity.class,1,null,null);
                break;
            case R.id.setting_personal_sex_Rl:
                startNewUIForResult(SexActivity.class,2,null,null);
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
        if (data!=null)
        Log.e("data", "onActivityResult: "+data.toString()+"\nrequestCode:"+requestCode+"\nresultCode:"+resultCode);
        if (data!=null&&resultCode==RESULT_OK){
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
//                imgpathMap.put(requestCode,imagePath);
                RequestBody requestbody=RequestBody.create(MediaType.parse("multipart/form-data"),new File(imagePath));
                uploadUserLogo(requestbody);
            }
//            update(requestCode,imgpathMap);// 刷新图片
                    update(imagePath);
    }

    private void uploadUserLogo(RequestBody requestbody) {
        Log.e("uploadUserLogo", "uploadUserLogo: "+requestbody.toString() );
        addSubscription(mApiService.uploadUserDetials(bUser.getUser_id(),0,requestbody,null),
                new BaseSubscriber<PersonalDetialsBean>(this) {
                    @Override
                    public void onNext(PersonalDetialsBean personalDetialsBean) {
                        Log.e("onNext", "onNext: "+personalDetialsBean.getState().getCode()+"\ndata:"+personalDetialsBean.getData().toString());
                        if (personalDetialsBean.getState().getCode()==200){
                            showToast("头像修改成功！");
                        }else{
                            showToast("头像修改失败！");
                        }
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }
                }
                );
    }

    //刷新图片方法
    private void update(String imagePath) {
        //为解决图片每次变小
//      Drawable drawable=new BitmapDrawable(ImageUtils.getImageThumbnail(imagePath,person_img.getWidth() ,person_img.getHeight() ));
        Drawable drawable=new BitmapDrawable(BitmapFactory.decodeFile(imagePath));
        person_img.setImageDrawable(drawable);
    }
}
