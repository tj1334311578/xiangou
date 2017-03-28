package com.example.administrator.xiangou.mine.store_application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.main.login.PopupWindowsBaseActivity;

/**
 * Created by Administrator on 2017/3/28.
 */

public class StoreApplicationActivity extends PopupWindowsBaseActivity implements View.OnClickListener{
    private RelativeLayout ID_positive,ID_opposite,logo_potato,Business_license,Lease_contract;

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
        showPicturePopupWindow();
    switch (v.getId()){
        case R.id.ID_positive:
            break;
        case R.id.ID_opposite:
            break;
        case R.id.logo_potato:
            break;
        case R.id.Business_license:
            break;
        case R.id.Lease_contract:
            break;
    }
    }
}
