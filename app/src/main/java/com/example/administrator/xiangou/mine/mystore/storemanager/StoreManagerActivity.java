package com.example.administrator.xiangou.mine.mystore.storemanager;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.CustomImageView;
import com.example.administrator.xiangou.tool.GlideImageLoader;
import com.example.administrator.xiangou.tool.ImageUtils;

import java.io.File;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StoreManagerActivity extends MVPBaseActivity<StoreManagerContract.View, StoreManagerPresenter> implements StoreManagerContract.View {
    private ImageView backBtn;
    private TextView titleTv, storeName, storeType;
    private CustomImageView storeLogo;
    private RelativeLayout storeLogoBtn;
    private Button modityBtn;
    private StoreManagerInfoBean.DataBean storeInfo;
    private EditText tel,location,address,description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_center_storeinfo_manager);
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case 1://更新logo图片
                    storeLogo.setImageDrawable(new BitmapDrawable(BitmapFactory.decodeFile(ImageUtils.getFilePathByFileUri(this,data.getData()))));
                    imgpath=ImageUtils.getFilePathByFileUri(this,data.getData());
                    Log.e("2_____________", "onActivityResult: "+imgpath);
                    storeInfo.setLogo(imgpath);
                    break;
            }
        }
    }

    /**
     * 查找控件以及第一次请求店铺信息数据
     */
    private void initView() {
        storeLogo=findContentView(R.id.store_information_logo_img);
        backBtn=findContentView(R.id.store_headback);
        modityBtn=findContentView(R.id.store_information_btn);
        titleTv =findContentView(R.id.store_headTitleTv,false);
        titleTv.setText("店铺信息管理");
        storeLogoBtn=findContentView(R.id.store_information_storelogo_rl);
        storeType=findContentView(R.id.store_information_style_show,false);
        storeName=findContentView(R.id.store_information_name_show,false);
        location=findContentView(R.id.store_information_location_show,false);
        address=findContentView(R.id.store_information_address_show,false);
        description=findContentView(R.id.store_information_describe_edit,false);
        tel=findContentView(R.id.store_information_phone_show,false);
        mPresenter.callStoreInfo(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.store_headback://返回按钮
                finish();
            case R.id.store_information_storelogo_rl://更换店铺logo按钮
                setShowatlocation(R.id.store_information_rl);//设置popupwindow的父布局
                showPicturePopupWindow(1);
                break;
            case R.id.store_information_btn://
                Log.e("修改上传", "onClick: " );
                saveDataToStoreInfo();//先保存再请求数据
                /**
                 * 这一步的文件上传可以分开写
                 *  imagePath = ImageUtils.getFilePathByFileUri(this, data.getData());
                 *  imgpathMap.put(requestCode,imagePath);
                 *  File img = new File(imagePath);
                 *  RequestBody requestbody=RequestBody.create(MediaType.parse("multipart/form-data"),img);
                 *  MultipartBody.Part file = createFormData("head_img",img.getName(),requestbody);
                 */
                File logo=new File(storeInfo.getLogo());
                RequestBody requestbody=RequestBody.create(MediaType.parse("multipart/form-data"),logo);
                MultipartBody.Part file= MultipartBody.Part.createFormData("logo","logo.png",requestbody);
                Log.e("info", "onClick: "+storeInfo.toString() );
                mPresenter.callEditStoreInfo(1,"104.014725","30.676117",storeInfo.getAddress(),33007,33008,33027,storeInfo.getSynopsis(),storeInfo.getTelephone(),file);
//                if (storeInfo!=null)
//                mPresenter.callEditStoreInfo(storeInfo.getDid(),storeInfo.getMap_x(),storeInfo.getMap_y(),
//                        storeInfo.getAddress(),storeInfo.getProvince(),storeInfo.getCity(),storeInfo.getDistrict(),
//                        storeInfo.getSynopsis(),storeInfo.getTelephone(),file);
//                        MultipartBody.Part.createFormData("logo","logo/png",
//                                RequestBody.create(MediaType.parse("multipart/form-data"),
//                                        new File(storeInfo.getLogo()))));
                break;
        }
    }

    private void saveDataToStoreInfo() {
        Log.e("tel", "saveDataToStoreInfo: "+tel.getText().toString() );
        storeInfo.setTelephone(tel.getText().toString());
        formatString(location.getText().toString()); //保存省市区数据
        storeInfo.setAddress(address.getText().toString());
        storeInfo.setSynopsis(description.getText().toString());
    }

    private void formatString(String s) {
        String[] str=s.split(" ");
        if (str.length==3) {
            storeInfo.setProvince_name(str[0]);
            storeInfo.setCity_name(str[1]);
            storeInfo.setDistrict_name(str[2]);
        }
        Log.e("str", "formatString: "+ Arrays.toString(str));

    }


    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void infoDataToView(StoreManagerInfoBean data) {
        storeInfo = data.getData();//保存数据
        showView(data);//数据显示在界面上的方法
    }

    private void showView(StoreManagerInfoBean data) {
        new GlideImageLoader().displayImage(this,mApiService.IMGBASEURL +data.getData().getLogo(),storeLogo);//加载店铺logo
        storeName.setText(data.getData().getName());
        storeType.setText(data.getData().getCate_name());
        tel.setText(data.getData().getTelephone());
        location.setText(data.getData().getProvince_name()+" "+data.getData().getCity_name()+" "+data.getData().getDistrict_name());
        address.setText(data.getData().getAddress());
        description.setText(data.getData().getSynopsis());
    }


    @Override
    public void modifySuccessd() {
        showToast("修改成功");
    }
}
