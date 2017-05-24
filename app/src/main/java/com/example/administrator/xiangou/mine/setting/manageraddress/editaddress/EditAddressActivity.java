package com.example.administrator.xiangou.mine.setting.manageraddress.editaddress;


import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.SpinnerBaseAdapter;
import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.mine.ToApplyStoreBean;
import com.example.administrator.xiangou.mine.setting.manageraddress.AddressBean;
import com.example.administrator.xiangou.mine.setting.manageraddress.model.UserAddressBean;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;

import java.util.List;

import rx.Observable;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class EditAddressActivity extends MVPBaseActivity<EditAddressContract.View, EditAddressPresenter>
        implements EditAddressContract.View {
    private ImageView back;
    private TextView TitleTv,SuccessTv,locationTv;
    private EditText receiveEt,numEt,detailedEt;
    private UserAddressBean.DataBean bean;
    private PopupWindow mPopupWindow;

    private SpinnerBaseAdapter[] mSpinnerBaseAdapters;
    private Spinner[] mSpinners;
    private int[] mAreaId;
    private String[] mAreaName;
    private List<ToApplyStoreBean.DataBean> mAreaList;
    private View mContentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editaddress);
        initView();
    }

    private void initView() {
        receiveEt=findContentView(R.id.editaddress_receivename_edit);
        numEt=findContentView(R.id.editaddress_number_edit);
        locationTv=findContentView(R.id.editaddress_locationEt);
        locationTv.setOnClickListener(this);
        detailedEt=findContentView(R.id.editaddress_detailed);

        back = findContentView(R.id.setting_head_back);
        TitleTv = findContentView(R.id.setting_head_center,false);
        SuccessTv = findContentView(R.id.setting_head_right);
        if (getIntent().getStringExtra("edit_address")!= null ) {
            bean = (UserAddressBean.DataBean) getIntent().getSerializableExtra("edit_address");
            Log.e("收货地址", "initView: " + bean.toString());
            //获取省的列表
            mPresenter.enterEditAddress(bean.getUser_id(), bean.getAddress_id());
            if (bean.getAddress_id() == -110) {//判断是否是添加地址
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
                detailedEt.setText(bean.getAddress());
                TitleTv.setText("编辑收货地址");
                SuccessTv.setText("完成");
                SuccessTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            }
        }
    }

    @Override
    public void sendFialRequest(String message) {
        showToast(message);
    }
    private void showPopupwindow() {
        mContentView = LayoutInflater.from(getContext()).inflate(R.layout.address_spinner_popupwindow,null);
        TextView cancelTv = (TextView) mContentView.findViewById(R.id.cancel_change_address_tv);
        cancelTv.setOnClickListener(this);
        TextView saveTv = (TextView) mContentView.findViewById(R.id.save_change_address_tv);
        saveTv.setOnClickListener(this);
        mSpinners = new Spinner[3];
        mSpinnerBaseAdapters = new SpinnerBaseAdapter[3];
        mAreaId = new int[3];
        mAreaName = new String[3];
//        mProvinceSpn = (Spinner) mContentView.findViewById(R.id.province_address_spinner);
//        mCitySpn = (Spinner) mContentView.findViewById(R.id.city_address_spinner);
//        mDistrictsSpn = (Spinner) mContentView.findViewById(R.id.districts_address_spinner);
        mSpinners[0] = (Spinner) mContentView.findViewById(R.id.province_address_spinner);
        mSpinners[1] = (Spinner) mContentView.findViewById(R.id.city_address_spinner);
        mSpinners[2] = (Spinner) mContentView.findViewById(R.id.districts_address_spinner);
        if (mAreaList!=null)
        initArea(mAreaList,0);

        mPopupWindow = new PopupWindow(mContentView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT,true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.grayBgColor)));
        mPopupWindow.showAtLocation(SuccessTv, Gravity.CENTER,0,0);
    }
    //获取省、市、区列表数据
    private void getChooseListData(Observable observable, int type) {
        mPresenter.getAreaList(observable,type);
    }
    //省市区数据绑定到控件
    private void initArea(final List<ToApplyStoreBean.DataBean> data, final int i) {
        mSpinnerBaseAdapters[i] = new SpinnerBaseAdapter(mContentView.getContext(),R.layout.item_spinner_applystore,data) {
            @Override
            public void bindDataToView(TextView textView, int position) {
                                textView.setText(data.get(position).getName());
                                mAreaName[i] = data.get(position).getName();
                                mAreaId[i] = data.get(position).getRegion_id();
            }
        };
        Log.e("area", "initView: "+ data.toString());

        mSpinners[i].setAdapter(mSpinnerBaseAdapters[i]);
        /**
         * 省级下拉框监听
         */
        mSpinners[i].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 根据省份更新城市区域信息
                mAreaId[i] = data.get(position).getRegion_id();
                mAreaName[i] = data.get(position).getName();
                if (i<2) {
                    int num;
                    num = i + 1;
                    toastShow("点咯！"+i);
                    getChooseListData(mApiService.chooseNextAdr(data.get(position).getRegion_id()),num);
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
            finish();
        }else if (v==SuccessTv){
            // TODO: 2017/4/25 请求修改信息
            Log.e("addresstype", "onClick: " +getIntent().getStringExtra("edit_address"));
            Intent intent =new Intent();
            AddressBean bean = new AddressBean(
                    receiveEt.getText().toString(),numEt.getText().toString(),
                    mAreaId[0],mAreaId[1],mAreaId[2],
                    detailedEt.getText().toString(),
                    bUser.getUser_id(), 0,
                    "104.0647572802","30.5701516651");
            intent.putExtra("data", bean);
            this.setResult(RESULT_OK,intent);
            //提交保存地址
            mPresenter.commitEditAddress(bean);
        }else {
            switch (v.getId()){
                case R.id.editaddress_locationEt:
                    showPopupwindow();
                    break;
                case R.id.cancel_change_address_tv:
                    mPopupWindow.dismiss();
                    break;
                case R.id.save_change_address_tv:
                    StringBuffer text = new StringBuffer();
                    for (int i = 0; i < 3; i++) {
                        if (mAreaName[i]!=null){
                            text.append(mAreaName[i]+" ");
                        }
                    }
                    locationTv.setText(text.toString());
                    mPopupWindow.dismiss();
                    break;
            }
        }
    }

    @Override
    public void commitSuccess(Captcha captcha) {
        showToast("信息保存成功！");
        finish();
    }

    @Override
    public void enterSuccess(List<ToApplyStoreBean.DataBean> addressEnterBean) {
        //bind省列表数据
        mAreaList = addressEnterBean;
    }

    @Override
    public void bindListDataToView(List<ToApplyStoreBean.DataBean> data, int type) {
        initArea(data,type);
    }
}
