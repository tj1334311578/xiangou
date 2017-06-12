package com.example.administrator.xiangou.mine.setting.personal;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.CustomImageView;
import com.example.administrator.xiangou.tool.ImageUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.example.administrator.xiangou.tool.MySharedPreferences.KEY_USERIMG;
import static okhttp3.MultipartBody.Part.createFormData;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PersonalActivity extends MVPBaseActivity<PersonalContract.View, PersonalPresenter>
        implements PersonalContract.View {

    private CustomImageView person_img;
    private TextView nickname_Tv,sex_Tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_personal);
        initView();
    }

    private void initView() {
//        Log.e("PersonalActivity", "initView: user: " +getUser().toString());
        findContentView(R.id.setting_personal_Rl,true);
        findContentView(R.id.setting_personal_nickname_Rl,true);
        findContentView(R.id.setting_personal_sex_Rl,true);
        findContentView(R.id.setting_personal_back,true);
        person_img=findContentView(R.id.setting_personal_img);
        nickname_Tv=findContentView(R.id.setting_personal_nickname_Tv,false);
        sex_Tv=findContentView(R.id.setting_personal_sex_Tv,false);

        //根据上次记录设初始值 initData
        initImageView(person_img);
        nickname_Tv.setText(getUser().getNickname());
        switch (getUser().getSex()){
            case 1:
                sex_Tv.setText("男");
                break;
            case 2:
                sex_Tv.setText("女");
                break;
        }

    }

    @Override
    public void sendFialRequest(String message) {
        showToast(message);
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
            if (data.getData() != null) {//有数据返回直接使用返回的图片地址
                String imagePath = ImageUtils.getFilePathByFileUri(this, data.getData());

                File img = new File(imagePath);
                RequestBody requestbody=RequestBody.create(MediaType.parse("multipart/form-data"),img);
                MultipartBody.Part file = createFormData("head_img",img.getName(),requestbody);
                mPresenter.uploadUserLogo(data.getData(),file);//上传图片
            }
    }

    @Override
    public void sendSuccessRequest(String message) {
        showToast(message);
        initImageView(person_img);
    }

    //刷新图片方法
    private void initImageView(ImageView imageView){
        Uri uri = getSP().getImgUri(KEY_USERIMG);
        if (uri!=null){
//            Log.e("patloadimg", "initImageView: by uri"+uri );
            //            ImageUtils.loadLocationImg(getContext(),uri,imageView);
            loadImg(uri,imageView);
        }else if (getUser().getHead_pic()!=null){
//            Log.e("patloadimg", "initImageView: by getHead_pic" );
            loadImg(getUser().getHead_pic(),imageView);
        }
    }

    @Override
    protected void onDestroy() {
        getSP().upDateUserInfo(getUser().toString());
        super.onDestroy();
    }
}
