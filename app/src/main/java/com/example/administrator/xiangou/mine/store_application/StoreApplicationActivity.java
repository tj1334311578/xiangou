package com.example.administrator.xiangou.mine.store_application;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.net.RetrofitClient;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.ImageUtils;
import com.example.administrator.xiangou.tool.ReadLocalJsonUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/3/28.
 */

public class StoreApplicationActivity extends PopupWindowsBaseActivity implements View.OnClickListener{
    /**                                         分割线
     *                                      通过本地json获取数据
     * ---------------------------------------------------------------------------------------------
     */
    public static String TAG="bug";
    // 判断是否有区
    private boolean hasArea = false;

    // 省份
    private String[] mProvinceDatas;
    // 城市
    private String[] mCitiesDatas;
    // 地区
    private String[] mAreaDatas;

    // 列表选择的省市区
    private String selectedPro = "";
    private String selectedCity = "";
    private String selectedArea = "";

    // 存储省对应的所有市
    private Map<String, String[]> mCitiesDataMap = new HashMap<String, String[]>();
    // 存储市对应的所有区
    private Map<String, String[]> mAreaDataMap = new HashMap<String, String[]>();

    private ArrayAdapter<String> mProvinceAdapter;
    private ArrayAdapter<String> mCityAdapter;
    private ArrayAdapter<String> mAreaAdapter;
    /**                                         分割线
     * ---------------------------------------------------------------------------------------------
     */
    private RelativeLayout ID_positive,ID_opposite,logo_potato,Business_license,Lease_contract;
    private EditText mApplicantNameEdt,mTelEdt,mIDCardEdt,mStoreNameEdt,mShopAdressEdt;
    private ImageView backBtn;
    private Button mCommitBtn;
    private Spinner province,city,districts;
    private Map<Integer,String> imgpathMap;
//    private String imagepath;

    private ApplicantInfoBean mApplicantInfoBean;

    /**                                         分割线
     * ---------------------------------------------------------------------------------------------
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_store);
        Log.e("intent+++++", "onCreate:" +getIntent().getStringExtra("position") );
        imgpathMap = new HashMap<>();
        initView();
    }

    private void initView() {
        mApplicantNameEdt = findContentView(R.id.application_name_edit,false);
        mTelEdt = findContentView(R.id.application_number_edit,false);
        mIDCardEdt = findContentView(R.id.application_ID_Card_edit,false);
        mStoreNameEdt = findContentView(R.id.store_name_edit,false);
        mShopAdressEdt = findContentView(R.id.store_address_edit,false);
        mCommitBtn = findContentView(R.id.commit_application_btn);

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
        backBtn= (ImageView)(findViewById(R.id.store_head_layout).findViewById(R.id.store_headback));
        backBtn.setOnClickListener(this);

        province= (Spinner) findViewById(R.id.store_address_province);
        city= (Spinner) findViewById(R.id.store_address_city);
        districts= (Spinner) findViewById(R.id.store_address_districts);
        //获取json字符串
        BeginJsonCitisData(ReadLocalJsonUtil.InitData(this));

        mProvinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mProvinceDatas);
        Log.e(TAG, "initView: "+ mProvinceDatas.toString());
        mProvinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        province.setAdapter(mProvinceAdapter);
/**
 * 省级下拉框监听
 */
        province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPro = "";
                selectedPro = (String) parent.getSelectedItem();
                // 根据省份更新城市区域信息
                updateCity(selectedPro);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
/**
 * 市级下拉框监听
 */
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = "";
                selectedCity = (String) parent.getSelectedItem();
                updateArea(selectedCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void updateArea(String selectedCity) {

        String[] areas = mAreaDataMap.get(selectedCity);

        // 存在区
        if (areas != null) {
            // 存在区
            districts.setVisibility(View.VISIBLE);
            mAreaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, areas);
            mAreaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            districts.setAdapter(mAreaAdapter);
            mAreaAdapter.notifyDataSetChanged();
            districts.setSelection(0);
        } else {
            showToast("没有选择区域");
        }
    }

    private void updateCity(String pro) {
        String[] cities = mCitiesDataMap.get(pro);
        for (int i = 0; i < cities.length; i++) {
            // 存在区
            mCityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
            mCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            city.setAdapter(mCityAdapter);
            mCityAdapter.notifyDataSetChanged();
            city.setSelection(0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ID_positive:
               showPicturePopupWindow(0);
//                imagepath=getmImagePath();
//                showToast(imagepath);
//                Log.e("ID_position", "onClick: "+imagepath );
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
         }
    }

    private String changeEdtext(EditText text) {
        return text.getText().toString();
    }
    //提交店铺申请
    private void commitApplicationShop() {
        mApplicantInfoBean = new ApplicantInfoBean();
        mApplicantInfoBean.setRealname(changeEdtext(mApplicantNameEdt));
        mApplicantInfoBean.setIdcard(changeEdtext(mIDCardEdt));
        mApplicantInfoBean.setTel(changeEdtext(mTelEdt));
        mApplicantInfoBean.setCid(new ArrayList<Integer>());
        mApplicantInfoBean.setName(changeEdtext(mStoreNameEdt));
        mApplicantInfoBean.setAddress(changeEdtext(mShopAdressEdt));
        String info = new Gson().toJson(mApplicantInfoBean);

        XianGouApiService mApi = RetrofitClient.getInstance(this).create(XianGouApiService.class);
//        RequestBody infos = RequestBody.create(MediaType.parse("application/infos; charset=utf-8"),info);
        MultipartBody.Part[] id_img = new MultipartBody.Part[2];
        MultipartBody.Part logo = null,licence= null,contract= null;
        for (int i = 0; i < 5; i++) {
            if (imgpathMap.get(i)!=null){
                File file = new File(imgpathMap.get(i));
                RequestBody img = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                if (i<2) {
                    //注--这里的key是id_img[]，而后台给的字段是id_img，是因为这个字段里其实是放了多个元素的，所以要加[]
                    //至于id_img[]与id_img的匹配问题--据后台妹子说是她后台那边字段名后的[]不是归在字段名内，而是代表这个字段里存放多个元素。
                    id_img[i] = MultipartBody.Part.createFormData("id_img[]",file.getName(),img);
                }else {
                    switch (i){
                        case 2:
//                            RequestBody img_logo = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                            logo = MultipartBody.Part.createFormData("logo",file.getName(), img);
                        case 3:
//                            RequestBody img_licence = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                            licence = MultipartBody.Part.createFormData("licence",file.getName(),img);
                        case 4:
//                            RequestBody img_contract = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                            contract = MultipartBody.Part.createFormData("contract",file.getName(),img);
                    }
                }

                //// TODO: 2017/5/5 这里报空了logo那里 
//                Log.e("imgfile", "commitApplicationShop: " + id_img[i].body().toString()
//                        +"/\n"+logo.body().toString()+"/\n"
//                        +licence.body().toString()+"/\n"
//                        +contract.body().toString());
            }
        }
        //
        addSubscription(mApi.applyShop(info,
                id_img, logo, licence, contract), new BaseSubscriber<Captcha>(this) {
            @Override
            public void onNext(Captcha captcha) {
                Log.e("applyshop", "onNext: ");
                if (captcha.getState().getCode()==200){
                    toastShow("OK!");
                }
            }

            @Override
            public void onFinish() {

            }

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
    private void setImg(int requestCode, int resultCode, Intent data){
            String imagePath = "";
            if ( data.getData() != null) {//有数据返回直接使用返回的图片地址
                imagePath = ImageUtils.getFilePathByFileUri(this, data.getData());
                imgpathMap.put(requestCode,imagePath);
            }
            update(requestCode,imgpathMap);// 刷新图片
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null && resultCode == RESULT_OK) {
            Log.e("TGA", "onActivityResult: " + data.getData());
            switch (requestCode) {
                case 0:
                    setImg(0, resultCode, data);
                    break;
                case 1:
                    setImg(1, resultCode, data);
                    break;
                case 2:
                    setImg(2, resultCode, data);
                    break;
                case 3:
                    setImg(3, resultCode, data);
                    break;
                case 4:
                    setImg(4, resultCode, data);
            }
        }
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

    /**
     * 开始解析城市数据
     *
     * @Title: BeginJsonCitisData
     * @param
     * @return void
     * @throws @date
     *             [2017年4月12日 上午10:22:00]
     */
    private void BeginJsonCitisData(String cityJson) {
        if (!TextUtils.isEmpty(cityJson)) {
            try {
                JSONObject object = new JSONObject(cityJson);
                JSONArray array = object.getJSONArray("citylist");

                // 获取省份的数量
                mProvinceDatas = new String[array.length()];
                String mProvinceStr = null;
                // 循环遍历
                for (int i = 0; i < array.length(); i++) {
                    // 循环遍历省份，并将省保存在mProvinceDatas[]中
                    JSONObject mProvinceObject = array.getJSONObject(i);
                    if (mProvinceObject.has("p")) {
                        mProvinceStr = mProvinceObject.getString("p");
                        mProvinceDatas[i] = mProvinceStr;
                    } else {
                        mProvinceDatas[i] = "unknown province";
                    }

                    JSONArray cityArray;
                    String mCityStr = null;
                    try {
                        // 循环遍历省对应下的城市
                        cityArray = mProvinceObject.getJSONArray("c");
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }

                    mCitiesDatas = new String[cityArray.length()];
                    for (int j = 0; j < cityArray.length(); j++) {
                        // 循环遍历城市，并将城市保存在mCitiesDatas[]中
                        JSONObject mCityObject = cityArray.getJSONObject(j);
                        if (mCityObject.has("n")) {
                            mCityStr = mCityObject.getString("n");
                            mCitiesDatas[j] = mCityStr;
                        } else {
                            mCitiesDatas[j] = "unknown city";
                        }

                        // 循环遍历地区
                        JSONArray areaArray;

                        try {
                            // 判断是否含有第三级区域划分，若没有，则跳出本次循环，重新执行循环遍历城市
                            areaArray = mCityObject.getJSONArray("a");
                        } catch (Exception e) {
                            e.printStackTrace();
                            continue;
                        }

                        // 执行下面过程则说明存在第三级区域
                        mAreaDatas = new String[areaArray.length()];
                        for (int m = 0; m < areaArray.length(); m++) {

                            // 循环遍历第三级区域，并将区域保存在mAreaDatas[]中
                            JSONObject areaObject = areaArray.getJSONObject(m);
                            if (areaObject.has("s")) {
                                mAreaDatas[m] = areaObject.getString("s");
                            } else {
                                mAreaDatas[m] = "unknown area";
                            }
//                            showToast(mAreaDatas[m]);
                        }

                        // 存储市对应的所有第三级区域
                        mAreaDataMap.put(mCityStr, mAreaDatas);
                    }

                    // 存储省份对应的所有市
                    mCitiesDataMap.put(mProvinceStr, mCitiesDatas);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
