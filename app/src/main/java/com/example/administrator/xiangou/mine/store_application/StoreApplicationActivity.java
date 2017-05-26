package com.example.administrator.xiangou.mine.store_application;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.SpinnerBaseAdapter;
import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.mine.ToApplyStoreBean;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.tool.ImageUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

import static okhttp3.MultipartBody.Part.createFormData;

/**
 * Created by Administrator on 2017/3/28.
 */

public class StoreApplicationActivity extends PopupWindowsBaseActivity implements View.OnClickListener{
    /**                                         分割线
     *                                      通过本地json获取数据
     * ---------------------------------------------------------------------------------------------
     */
    public static String TAG="bug";

    /**                                         分割线
     * ---------------------------------------------------------------------------------------------
     */
    private static final int STARTCODE = 11;
    private RelativeLayout ID_positive,ID_opposite,logo_potato,Business_license,Lease_contract;
    private EditText mApplicantNameEdt,mTelEdt,mIDCardEdt,mStoreNameEdt,mShopAdressEdt;
    private ImageView backBtn;
    private Button mCommitBtn;
    private TextView mCategoryTv;
    private Map<Integer,String> imgpathMap;
    private ApplicantInfoBean mApplicantInfoBean;
    private Intent mIntent;


    private final int sizeCount = 3;
    private SpinnerBaseAdapter[] mSpinnerBaseAdapters;
    private Spinner[] mSpinners;
    private int[] mAreaId;
    /**                                         分割线
     * ---------------------------------------------------------------------------------------------
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_store);
        mIntent = getIntent();
        imgpathMap = new HashMap<>();
        mApplicantInfoBean = new ApplicantInfoBean();
        int mUserId = mIntent.getIntExtra("user_id",0);
        mApplicantInfoBean.setUser_id(mUserId);
        initView();
        getChooseListData(mApiService.toApplyShop(mUserId),0);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mSpinners = new Spinner[sizeCount];
        mSpinnerBaseAdapters = new SpinnerBaseAdapter[sizeCount];
        mAreaId = new int[sizeCount];

        mApplicantNameEdt = findContentView(R.id.application_name_edit,false);
        mTelEdt = findContentView(R.id.application_number_edit,false);
        mIDCardEdt = findContentView(R.id.application_ID_Card_edit,false);
        mStoreNameEdt = findContentView(R.id.store_name_edit,false);
        mShopAdressEdt = findContentView(R.id.store_address_edit,false);
        mCommitBtn = findContentView(R.id.commit_application_btn);
        mCategoryTv = findContentView(R.id.main_category_edit);
        //获取上次记录数据
        Log.e("storeinfo", "onStop: " + bSharedPreferences.getString("StoreApplyInfo",null));
        String[] user =  bSharedPreferences.getString("StoreApplyInfo",null).split(",");
        for (int i = 0; i < user.length; i++) {
            if (!user[i].equals("")){
                switch (i){
                    case 0:
                        mApplicantNameEdt.setText(user[0]);
                        break;
                    case 1:
                        mTelEdt.setText(user[1]);
                        break;
                    case 2:
                        mIDCardEdt.setText(user[2]);
                        break;
                    case 3:
                        mStoreNameEdt.setText(user[3]);
                        break;
                    case 4:
                        mShopAdressEdt.setText(user[4]);
                        break;
                }
            }
        }
        ID_positive= findContentView(R.id.ID_positive);
        ID_opposite= findContentView(R.id.ID_opposite);
        logo_potato= findContentView(R.id.logo_potato);
        Business_license= findContentView(R.id.Business_license);
        Lease_contract= findContentView(R.id.Lease_contract);
        backBtn= findContentView(R.id.store_headback);

        mSpinners[0]= (Spinner) findViewById(R.id.store_address_province);
        mSpinners[1]= (Spinner) findViewById(R.id.store_address_city);
        mSpinners[2]= (Spinner) findViewById(R.id.store_address_districts);
    }

    //获取省、市、区列表数据
    private void getChooseListData(Observable observable, final int type) {
        addSubscription(observable, new BaseSubscriber<ToApplyStoreBean>(this) {
            @Override
            public void onNext(ToApplyStoreBean toApplyStoreBean) {
                Log.e("ToApplyStore", "onNext: " +toApplyStoreBean.getData().toString());
                switch (toApplyStoreBean.getState().getCode()){
                    case 200:
                        if (type == 0) {
                            initArea(toApplyStoreBean.getData(),type);
                            return;
                        }else if (type == 1) {
                            initArea(toApplyStoreBean.getData(),type);
                            return;
                        }else if (type == 2) {
                            initArea(toApplyStoreBean.getData(),type);
                            return;
                        }
                        break;
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                Log.e("toapplystore", "onError: " +e.getMessage());
            }

        });
    }
    //省市区数据绑定到控件
    private void initArea(final List<ToApplyStoreBean.DataBean> data, final int type) {
        mSpinnerBaseAdapters[type] = new SpinnerBaseAdapter(this, R.layout.item_spinner_applystore,data) {
            @Override
            public void bindDataToView(TextView textView, int position) {
                textView.setText(data.get(position).getName());
                mAreaId[type] = data.get(position).getRegion_id();
            }
        };
        Log.e(TAG, "initView: "+ data.toString());
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinners[type].setAdapter(mSpinnerBaseAdapters[type]);
        /**
         * 省级下拉框监听
         */
        mSpinners[type].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 根据省份更新城市区域信息;
                mAreaId[type] = data.get(position).getRegion_id();
                if (type<2){
                    int num;
                    num = type + 1;
                    getChooseListData(mApiService.chooseNextAdr(data.get(position).getRegion_id()),num);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null && resultCode == RESULT_OK) {
            Log.e("TGA", "onActivityResult: " + data.getData());
            switch (requestCode) {
                case 0:
                    setImg(0, data);
                    break;
                case 1:
                    setImg(1, data);
                    break;
                case 2:
                    setImg(2, data);
                    break;
                case 3:
                    setImg(3, data);
                    break;
                case 4:
                    setImg(4, data);
                    break;
                case STARTCODE:
                    StringBuilder text = new StringBuilder();
                    int[] categoryData = data.getIntArrayExtra("category_list");
                    Log.e("cid", "onActivityResult: " + categoryData.toString());

                    String[] categoryNameData = data.getStringArrayExtra("category_namelist");
                    mApplicantInfoBean.setCid(categoryData);
                    for (int i = 0; i < categoryData.length; i++) {
                        text.append(categoryNameData[i]+" ");
                    }
                    mCategoryTv.setText(text);
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ID_positive:
                showPicturePopupWindow(0);
                break;
            case R.id.ID_opposite:
                showPicturePopupWindow(1);
                break;
            case R.id.logo_potato:
                showPicturePopupWindow(2);
                break;
            case R.id.Business_license:
                showPicturePopupWindow(3);
                break;
            case R.id.Lease_contract:
                showPicturePopupWindow(4);
                break;
            case R.id.store_headback:
                finish();
                break;
            case R.id.commit_application_btn:
                commitApplicationShop();
                break;
            case R.id.main_category_edit:
                startNewUIForResult(CategoryTypeActivity.class,STARTCODE,"category_type","");
                break;
         }
    }

    private String changeEdtext(EditText text) {
        return text.getText().toString();
    }
    //提交店铺申请
    private void commitApplicationShop() {
        mApplicantInfoBean.setRealname(changeEdtext(mApplicantNameEdt));
        mApplicantInfoBean.setTel(changeEdtext(mTelEdt));
        mApplicantInfoBean.setIdcard(changeEdtext(mIDCardEdt));
        mApplicantInfoBean.setName(changeEdtext(mStoreNameEdt));
        mApplicantInfoBean.setAddress(changeEdtext(mShopAdressEdt));
        mApplicantInfoBean.setProvince(mAreaId[0]);
        mApplicantInfoBean.setCity(mAreaId[1]);
        mApplicantInfoBean.setDistrict(mAreaId[2]);
        //待修改
        mApplicantInfoBean.setMap_x("104.234452");
        mApplicantInfoBean.setMap_y("30.123434");
        Log.e("ApplicantInfoBean", "commitApplicationShop: " + mApplicantInfoBean.toString() );

        Map<String, RequestBody> imgs = new HashMap<>();
        MultipartBody.Part[] id_img = new MultipartBody.Part[2];
        MultipartBody.Part logo = null,licence= null,contract= null;
        for (int i = 0; i < 5; i++) {
            if (imgpathMap.get(i)!=null){
                File file = new File(imgpathMap.get(i));
                RequestBody img = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                if (i<2) {
                    //注--这里的key是id_img[]，而后台给的字段是id_img，是因为这个字段里其实是放了多个元素的，所以要加[]
                    //至于id_img[]与id_img的匹配问题--据后台妹子说是她后台那边字段名后的[]不是归在字段名内，而是代表这个字段里存放多个元素。
                    id_img[i] = createFormData("id_img[]",file.getName(),img);
                }else {
                    switch (i){
                        case 2:
                            logo = createFormData("logo",file.getName(), img);
                            break;
                        case 3:
//                            imgs.put("licence",img);
                            licence = createFormData("licence",file.getName(),img);
//                            licence = img;
                            break;
                        case 4:
//                            contract = img;
//                            imgs.put("contract",img);
                            contract = createFormData("contract",file.getName(),img);
                            break;
                    }
                }

            }
        }

        addSubscription(mApiService.applyShop(
                mApplicantInfoBean,
                id_img,
                logo,
//                imgs
                licence,
                contract
        ), new BaseSubscriber<Captcha>(this) {
            @Override
            public void onNext(Captcha captcha) {
                Log.e("applyshop", "onNext: "+captcha.toString());
                if (captcha.getState().getCode()==200){
                    showToast("已成功提交申请，请等待！");
                    Intent intent = new Intent();
                    intent.putExtra("applying","申请审核中");
                    StoreApplicationActivity.this.setResult(RESULT_OK,intent);
                    finish();
                }

            }

            @Override
            public void onFinish() {}

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                Log.e("applyshop", "onError: "+e.message+e.getMessage());
                showToast(e.getMessage());
            }
        });
    }
    private void showDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(StoreApplicationActivity.this);
        dialog.setIcon(R.mipmap.shop_treasure_icon);
        dialog.setTitle("店铺申请");
        dialog.setMessage("您的店铺申请已成功提交,请等待处理消息!");
        dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }
    private void setImg(int requestCode, Intent data){
            String imagePath = "";
            if ( data.getData() != null) {//有数据返回直接使用返回的图片地址
                imagePath = ImageUtils.getFilePathByFileUri(this, data.getData());
                imgpathMap.put(requestCode,imagePath);
            }
            update(requestCode,imgpathMap);// 刷新图片
    }


    private void update(int i, Map<Integer, String> imagepaths) {
        Drawable drawable;
        ImageView img;
        RelativeLayout temp=null;
        switch (i){
            case 0:
                temp= ID_positive;
                break;
            case 1:
                temp= ID_opposite;
                break;
            case 2:
                temp=logo_potato;
                break;
            case 3:
                temp=Business_license;
                break;
            case 4:
                temp=Lease_contract;
                break;
            default:
                break;
        }
        img=new ImageView(this);
        img.setMinimumWidth(temp.getWidth());
        img.setMinimumHeight(temp.getHeight());
        drawable=new BitmapDrawable(ImageUtils.getImageThumbnail(imagepaths.get(i),temp.getWidth() ,temp.getHeight() ));
        img.setImageDrawable(drawable);
        temp.addView(img);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mApplicantInfoBean.setRealname(changeEdtext(mApplicantNameEdt));
        mApplicantInfoBean.setTel(changeEdtext(mTelEdt));
        mApplicantInfoBean.setIdcard(changeEdtext(mIDCardEdt));
        mApplicantInfoBean.setName(changeEdtext(mStoreNameEdt));
        mApplicantInfoBean.setAddress(changeEdtext(mShopAdressEdt));
        bSharedPreferences.putString("StoreApplyInfo",mApplicantInfoBean.toString());
    }

}
