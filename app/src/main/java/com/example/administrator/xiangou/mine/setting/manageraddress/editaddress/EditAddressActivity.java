package com.example.administrator.xiangou.mine.setting.manageraddress.editaddress;

import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.SpinnerBaseAdapter;
import com.example.administrator.xiangou.mine.ToApplyStoreBean;
import com.example.administrator.xiangou.mine.setting.manageraddress.ManagerAddressActivity;
import com.example.administrator.xiangou.mine.setting.manageraddress.model.UserAddressBean;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Observable;

public class EditAddressActivity extends MVPBaseActivity<EditAddressContract.View, EditAddressPresenter>
        implements EditAddressContract.View{

    private ImageView back;
    private TextView TitleTv, saveTv,locationTv;
    private EditText receiveEt,numEt,detailedEt;
    private UserAddressBean.DataBean bean;
    private SpinnerBaseAdapter[] mSpinnerBaseAdapters;
    private Spinner[] mSpinners;
//    private int[] mAreaId;
    private String[] mAreaName;
    private List<ToApplyStoreBean.DataBean> mAreaList;
    private AlertDialog mDialog;
    private String type;
    private JSONObject mInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editaddress);
//        mAreaId = new int[3];
        mAreaName = new String[3];
        mInfo = new JSONObject();
        try {
            mInfo.put("user_id",bUser.getUser_id());
            mInfo.put("map_x","104.0647572802");
            mInfo.put("map_y","30.5701516651");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initView();
    }

    private void initView() {
        receiveEt=findContentView(R.id.editaddress_receivename_edit,false);
        numEt=findContentView(R.id.editaddress_number_edit,false);
        locationTv=findContentView(R.id.editaddress_locationEt);
        locationTv.setOnClickListener(this);
        detailedEt=findContentView(R.id.editaddress_detailed,false);
        back = findContentView(R.id.setting_head_back);
        TitleTv = findContentView(R.id.setting_head_center,false);
        saveTv = findContentView(R.id.setting_head_right);
        receiveEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                try {
                    mInfo.put("consignee",s.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    mInfo.put("consignee",s.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        detailedEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                try {
                    mInfo.put("address",s.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    mInfo.put("address",s.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        numEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                try {
                    mInfo.put("mobile",s.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    mInfo.put("mobile",s.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //在获取省列表数据前创建dialog
        createDialog();

        if (getIntent().getSerializableExtra("edit_address")!= null ) {
            bean = (UserAddressBean.DataBean) getIntent().getSerializableExtra("edit_address");
            type = getIntent().getStringExtra("type");
            Log.e("收货地址", "initView -- type: "+type+" data "+ bean.toString());
            //最开始进来就获取省的列表
            mPresenter.enterEditAddress(bean.getUser_id(), bean.getAddress_id());
            if (type.equals("add")) {//判断是否是添加地址
                //添加数据地址
                receiveEt.setText("");
                numEt.setText("");
                locationTv.setText("");
                detailedEt.setText("");
                TitleTv.setText("添加收货地址");
            }else {
                receiveEt.setText(bean.getConsignee());
                numEt.setText(bean.getMobile());
                locationTv.setText(bean.getProvince() + " " + bean.getCity() + " " + bean.getDistrict());
                try {
                    mInfo.put("address_id",bean.getAddress_id());
                    mInfo.put("province",bean.getProvince_id());
                    mInfo.put("city",bean.getCity_id());
                    mInfo.put("district",bean.getDistrict_id());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                detailedEt.setText(bean.getAddress());
                TitleTv.setText("编辑收货地址");
                saveTv.setText("完成");
                saveTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            }
        }
    }

    private void createDialog(){
        //创建dialog
        if (mDialog==null) {
            mDialog = new AlertDialog.Builder(EditAddressActivity.this).create();
            //dialog自定义布局
            View dialogView = LayoutInflater.from(this).inflate(R.layout.address_spinner_dialog,null);
            mDialog.setView(dialogView);
            mSpinners = new Spinner[3];
            mSpinnerBaseAdapters = new SpinnerBaseAdapter[3];
            mSpinners[0] = (Spinner) dialogView.findViewById(R.id.province_address_spinner);
            mSpinners[1] = (Spinner) dialogView.findViewById(R.id.city_address_spinner);
            mSpinners[2] = (Spinner) dialogView.findViewById(R.id.districts_address_spinner);
            TextView sureBtn = (TextView) dialogView.findViewById(R.id.sure_change_address_btn);
            TextView cancelBtn = (TextView) dialogView.findViewById(R.id.cancel_change_address_btn);
            cancelBtn.setOnClickListener(this);
            sureBtn.setOnClickListener(this);
            Log.e("dialog", "createDialog: w: " +dialogView.getMeasuredWidth()+" h:"+dialogView.getMeasuredHeight());
            mDialog.setCancelable(false);//点击外部不关闭
        }
    }

    //获取省、市、区列表数据
    private void getChooseListData(Observable observable, int type) {
        mPresenter.getAreaList(observable,type);
    }
    //省市区数据绑定到控件
    private void initArea(final List<ToApplyStoreBean.DataBean> data, final int i) {
        Log.e("area", "initView: " +i+ data.toString());
        mSpinnerBaseAdapters[i] = new SpinnerBaseAdapter(getContext(), R.layout.item_spinner_applystore, data) {
            @Override
            public void bindDataToView(TextView textView, int position) {
                Log.e("adaper", "bindDataToView: ");
                mAreaName[i] = data.get(position).getName();
                textView.setText(mAreaName[i]);
                //这是记录ID的
//                String nameStr = null;
//                switch (i){
//                    case 0:
//                        nameStr = new String("province");
//                        break;
//                    case 1:
//                        nameStr = new String("city");
//                        break;
//                    case 2:
//                        nameStr = new String("district");
//                        break;
//                }
//                try {
//                    mInfo.put(nameStr,data.get(position).getRegion_id());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                mAreaId[i] = data.get(position).getRegion_id();
            }
        };

        mSpinners[i].setAdapter(mSpinnerBaseAdapters[i]);
        mSpinnerBaseAdapters[i].notifyDataSetChanged();
        mSpinners[i].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 根据省份更新城市区域信息
                Log.e("adaper", "bindDataToView: listen");
//                    mAreaId[i] = data.get(position).getRegion_id();
                mAreaName[i] = data.get(position).getName();
                String nameStr = null;
                switch (i){
                    case 0:
                        nameStr = new String("province");
                        break;
                    case 1:
                        nameStr = new String("city");
                        break;
                    case 2:
                        nameStr = new String("district");
                        break;
                }
                try {
                    mInfo.put(nameStr,data.get(position).getRegion_id());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (i < 2) {
                        int num;
                        num = i + 1;
                        getChooseListData(mApiService.chooseNextAdr(data.get(position).getRegion_id()), num);
                    }
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v==back){
            startNewUI(ManagerAddressActivity.class);
            finish();
        }else if (v== saveTv){
            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle("保存收货地址");
            saveDialog.setMessage("是否保存此收货地址？");
            saveDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            saveDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Log.e("info", "onClick: " + mInfo.toString());
                    if (mInfo!=null) {
                        mPresenter.commitEditAddress(mInfo);
                    }
                }
            });
            saveDialog.create().show();
//            mInfo = createInfo();
        }else {
            switch (v.getId()){
                case R.id.editaddress_locationEt:
                    if (mDialog!=null) {
                        mDialog.show();
                    }
                    break;
                case R.id.sure_change_address_btn:
                    StringBuffer text = new StringBuffer();
                    for (int i = 0; i < 3; i++) {
                        if (mAreaName[i] != null) {
                            text.append(mAreaName[i] + " ");
                        }
                    }
                    locationTv.setText(text.toString());
                    detailedEt.setText(mAreaName[0] + mAreaName[1] + mAreaName[2]);
                    if (mDialog!=null&&mDialog.isShowing()) {
                        mDialog.hide();
                    }
                    break;
                case R.id.cancel_change_address_btn:
                    if (mDialog!=null&&mDialog.isShowing()) {
                        mDialog.hide();
                    }
                    break;
            }
        }
    }

    //create上传数据info
//    private JSONObject createInfo(){
//        try {
//            mInfo.put("mobile",numEt.getText().toString());
//            mInfo.put("province",mAreaId[0]);
//            mInfo.put("city",mAreaId[1]);
//            mInfo.put("district",mAreaId[2]);
//            mInfo.put("address",detailedEt.getText().toString());
//            mInfo.put("user_id",bUser.getUser_id());
//            mInfo.put("address_id",0);
//            mInfo.put("map_x","104.0647572802");
//            mInfo.put("map_y","30.5701516651");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return mInfo;
//    }

    @Override
    public void commitSuccess(String captcha) {
        toastShow(captcha);
        startNewUI(ManagerAddressActivity.class);
        finish();
    }

    //刚进入页面时，获取省数据成功
    @Override
    public void enterSuccess(List<ToApplyStoreBean.DataBean> addressEnterBean) {
        //bind省列表数据
        mAreaList = addressEnterBean;
        if (mAreaList!=null)
            initArea(mAreaList,0);
    }

    //获取市、区的数据成功
    @Override
    public void bindListDataToView(List<ToApplyStoreBean.DataBean> data, int type) {
        initArea(data,type);
    }

    @Override
    public void sendFialRequest(String message) {
        Log.e("错了", "sendFialRequest: " + message);
        toastShow("错误："+message);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialog!=null||mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
