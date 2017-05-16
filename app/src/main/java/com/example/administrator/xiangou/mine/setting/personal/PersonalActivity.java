package com.example.administrator.xiangou.mine.setting.personal;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.CustomImageView;
import com.example.administrator.xiangou.tool.ImageUtils;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PersonalActivity extends MVPBaseActivity<PersonalContract.View, PersonalPresenter> implements PersonalContract.View {
    private RelativeLayout img_setting,nickname,sex;
    private CustomImageView person_img;
    private ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_personal);
        img_setting=findContentView(R.id.setting_personal_Rl,true);
        person_img=findContentView(R.id.setting_personal_img);
        nickname=findContentView(R.id.setting_personal_nickname_Rl,true);
        sex=findContentView(R.id.setting_personal_sex_Rl,true);
        back=findContentView(R.id.setting_personal_back,true);
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
                startNewUI(NicknameActivity.class);
                break;
            case R.id.setting_personal_sex_Rl:
                startNewUI(SexActivity.class);
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
        setImg(0,resultCode,data);
    }

    private void setImg(int i, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String imagePath = "";
            if (data != null && data.getData() != null) {//有数据返回直接使用返回的图片地址
                imagePath = ImageUtils.getFilePathByFileUri(this, data.getData());
//                imgpathMap.put(requestCode,imagePath);
            }
//            update(requestCode,imgpathMap);// 刷新图片
                    update(imagePath);
        }
    }
//刷新图片方法
    private void update(String imagePath) {
        //为解决图片每次变小
//       Drawable drawable=new BitmapDrawable(ImageUtils.getImageThumbnail(imagePath,person_img.getWidth() ,person_img.getHeight() ));
        Drawable drawable=new BitmapDrawable(BitmapFactory.decodeFile(imagePath));
        person_img.setImageDrawable(drawable);
    }
}
