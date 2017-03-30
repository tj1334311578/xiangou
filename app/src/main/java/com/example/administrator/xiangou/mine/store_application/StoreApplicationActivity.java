package com.example.administrator.xiangou.mine.store_application;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.ImageUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/28.
 */

public class StoreApplicationActivity extends PopupWindowsBaseActivity implements View.OnClickListener{
    private RelativeLayout ID_positive,ID_opposite,logo_potato,Business_license,Lease_contract;
    private ArrayList<String> imagepaths=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_store);
        initView();
    }

    private void initView() {
        ID_positive= (RelativeLayout) findViewById(R.id.ID_positive);
        ID_positive.setOnClickListener(this);
        ID_opposite= (RelativeLayout) findViewById(R.id.ID_opposite);
        ID_opposite.setOnClickListener(this);
        logo_potato= (RelativeLayout) findViewById(R.id.logo_potato);
        logo_potato.setOnClickListener(this);
        Business_license= (RelativeLayout) findViewById(R.id.Business_license);
        Business_license.setOnClickListener(this);
        Lease_contract= (RelativeLayout) findViewById(R.id.Lease_contract);
        Lease_contract.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        showPicturePopupWindow();
//        Drawable drawable=new BitmapDrawable(ImageUtils.getImageThumbnail(mImagePath,ID_positive.getWidth() ,ID_positive.getHeight() ));
        switch (v.getId()){
            case R.id.ID_positive:
            showPicturePopupWindow();
            break;
        case R.id.ID_opposite:
            showPicturePopupWindow();
            break;
        case R.id.logo_potato:
            showPicturePopupWindow();
            break;
        case R.id.Business_license:
            showPicturePopupWindow();
            break;
        case R.id.Lease_contract:
            showPicturePopupWindow();
            break;
    }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_IMAGE_RESULT_CODE && resultCode == RESULT_OK){
            String imagePath = "";
            if(data != null && data.getData() != null){//有数据返回直接使用返回的图片地址
                imagePath = ImageUtils.getFilePathByFileUri(this, data.getData());
            }else{//无数据使用指定的图片路径
                imagePath = mImagePath;
            }
            imagepaths.add(imagePath);
             update(imagepaths);// 刷新图片
            Log.e("onActivityResult", "onActivityResult: "+imagepaths.toString() );
        }
    }

    private void update(ArrayList<String> imagepaths) {
        Drawable drawable;
        ImageView img;
        for (int i = 0; i <imagepaths.size() ; i++) {
            switch (i){
                case 0:
                    img=new ImageView(this);
                    img.setMinimumWidth(ID_positive.getWidth());
                    img.setMinimumHeight(ID_positive.getHeight());
                    drawable=new BitmapDrawable(ImageUtils.getImageThumbnail(imagepaths.get(0),ID_positive.getWidth() ,ID_positive.getHeight() ));;
                    img.setImageDrawable(drawable);
                    ID_positive.addView(img);
                    break;
                case 1:
                    img=new ImageView(this);
                    img.setMinimumWidth(ID_positive.getWidth());
                    img.setMinimumHeight(ID_positive.getHeight());
                    drawable=new BitmapDrawable(ImageUtils.getImageThumbnail(imagepaths.get(1),ID_positive.getWidth() ,ID_positive.getHeight() ));;
                    img.setImageDrawable(drawable);
                    ID_opposite.addView(img);
                    break;
                case 2:
                    img=new ImageView(this);
                    img.setMinimumWidth(ID_positive.getWidth());
                    img.setMinimumHeight(ID_positive.getHeight());
                    drawable=new BitmapDrawable(ImageUtils.getImageThumbnail(imagepaths.get(2),ID_positive.getWidth() ,ID_positive.getHeight() ));;
                    img.setImageDrawable(drawable);
                    logo_potato.addView(img);
                    break;
                case 3:
                    img=new ImageView(this);
                    img.setMinimumWidth(ID_positive.getWidth());
                    img.setMinimumHeight(ID_positive.getHeight());
                    drawable=new BitmapDrawable(ImageUtils.getImageThumbnail(imagepaths.get(3),ID_positive.getWidth() ,ID_positive.getHeight() ));;
                    img.setImageDrawable(drawable);
                    logo_potato.addView(img);
                    break;
                case 4:
                    img=new ImageView(this);
                    img.setMinimumWidth(ID_positive.getWidth());
                    img.setMinimumHeight(ID_positive.getHeight());
                    drawable=new BitmapDrawable(ImageUtils.getImageThumbnail(imagepaths.get(4),ID_positive.getWidth() ,ID_positive.getHeight() ));;
                    img.setImageDrawable(drawable);
                    logo_potato.addView(img);
                    break;
            }
        }
    }
}
