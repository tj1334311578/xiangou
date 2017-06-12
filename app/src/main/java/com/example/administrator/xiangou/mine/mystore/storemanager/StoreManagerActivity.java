package com.example.administrator.xiangou.mine.mystore.storemanager;


import android.content.Intent;
import android.net.Uri;
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
import com.example.administrator.xiangou.tool.ContextUtils;
import com.example.administrator.xiangou.tool.CustomImageView;
import com.example.administrator.xiangou.tool.ImageUtils;
import com.example.administrator.xiangou.tool.MySharedPreferences;

import java.io.File;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.example.administrator.xiangou.tool.MySharedPreferences.KEY_STOREIMG;


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
    private boolean hasLogoChanged = false;

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
                    ImageUtils.loadLocationImg(getContext(),data.getData(),storeLogo);

                    getSP().saveImgUri(MySharedPreferences.KEY_STOREIMG,data.getData());

                    imgpath=ImageUtils.getFilePathByFileUri(this,data.getData());
                    Log.e("2_____________", "onActivityResult: "+imgpath);
                    storeInfo.setLogo(data.getDataString());
                    hasLogoChanged = true;
                    break;
            }
        }
    }

    /**
     * 查找控件以及第一次请求店铺信息数据
     */
    private void initView() {
        mPresenter.callStoreInfo(1);//did=1
        storeLogo=findContentView(R.id.store_information_logo_img);
        backBtn=findContentView(R.id.store_headback);
        modityBtn=findContentView(R.id.store_information_btn);
        modityBtn.setClickable(false);
        titleTv =findContentView(R.id.head_title_tv,false);
        titleTv.setText("店铺信息管理");
        storeLogoBtn=findContentView(R.id.store_information_storelogo_rl);
        storeType=findContentView(R.id.store_information_style_show,false);
        storeName=findContentView(R.id.store_information_name_show,false);
        location=findContentView(R.id.store_information_location_show,false);
        address=findContentView(R.id.store_information_address_show,false);
        description=findContentView(R.id.store_information_describe_edit,false);
        tel=findContentView(R.id.store_information_phone_show,false);
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
            case R.id.store_information_btn:
                Log.e("修改上传", "onClick: " );
                MultipartBody.Part file = null;
                if (hasLogoChanged) {
                    File logo = ImageUtils.getFileFromMediaUri(getContext(), Uri.parse(storeInfo.getLogo()));
                    RequestBody requestbody = RequestBody.create(MediaType.parse("multipart/form-data"), logo);
                    file = MultipartBody.Part.createFormData("logo", "logo.png", requestbody);
                }
                Log.e("info", "onClick: " + storeInfo.toString());
                mPresenter.callEditStoreInfo(
//                        1,
                        storeInfo.getDid(),
//                        "104.014725","30.676117",
                        storeInfo.getMap_x(),storeInfo.getMap_y(),
//                        ContextUtils.makeTextOK(address.getText().toString())
                        address.getText().toString(),
//                        33007,33008,33027,
                        storeInfo.getProvince(),storeInfo.getCity(),storeInfo.getDistrict(),//若改变则需要新的数据
//                        ContextUtils.makeTextOK(description.getText().toString()),
                        description.getText().toString().replace("\n","\\n"),
                        tel.getText().toString(),
//                        ContextUtils.makeTextOK(tel.getText().toString()),
                        file);
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
        modityBtn.setClickable(true);
    }

    private void showView(StoreManagerInfoBean data) {
        initImageView(storeLogo,data.getData().getLogo());//加载店铺logo
        storeName.setText(data.getData().getName());
        storeType.setText(data.getData().getCate_name());
        location.setText(data.getData().getProvince_name()+" "+data.getData().getCity_name()
                +" "+data.getData().getDistrict_name());
        ContextUtils.setTextFormat(tel,data.getData().getTelephone());
        ContextUtils.setTextFormat(address,data.getData().getAddress());
        ContextUtils.setTextFormat(description,data.getData().getSynopsis());
    }
    private void initImageView(ImageView imageView, String logo){
        Uri uri = getSP().getImgUri(KEY_STOREIMG);
        if (uri!=null){
            ImageUtils.loadLocationImg(getContext(),uri,imageView);
        }else if (logo!=null){
            loadImg(logo,imageView);
        }
    }

    @Override
    public void modifySuccessd() {
        showToast("修改成功");
        saveDataToStoreInfo();//保存数据
        finish();
    }
}
