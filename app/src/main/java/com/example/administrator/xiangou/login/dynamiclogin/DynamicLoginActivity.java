package com.example.administrator.xiangou.login.dynamiclogin;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.login.idlogin.IDLoginActivity;
import com.example.administrator.xiangou.main.MainActivity;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.CountDownTimerUtils;

public class DynamicLoginActivity extends MVPBaseActivity<DynamicLoginContract.View, DynamicLoginPresenter>
        implements DynamicLoginContract.View,View.OnClickListener {
    private EditText dynamic_number,dynamic_code;
    private TextView dynamic_verification;
    private Button dynamic_btn ;
    private ImageView dynamic_cls,dynamic_back;
    private CountDownTimerUtils countDownTimerUtils;
    private InputMethodManager imm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_login);
        initView();
    }

    private void initView() {
        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        dynamic_number= (EditText) findViewById(R.id.dynamic_numberedit);
        dynamic_code= (EditText) findViewById(R.id.dynamic_codeedit);
        dynamic_verification= (TextView) findViewById(R.id.dynamic_verification);
        dynamic_btn= (Button) findViewById(R.id.dynamic_login);
        dynamic_cls= (ImageView) findViewById(R.id.dynamic_clean);
        dynamic_back= (ImageView) findViewById(R.id.dynamic_back);
        dynamic_verification.setOnClickListener(this);
        dynamic_cls.setOnClickListener(this);
        dynamic_btn.setOnClickListener(this);
        dynamic_back.setOnClickListener(this);
        countDownTimerUtils = new CountDownTimerUtils(dynamic_number, dynamic_verification, 30000, 1000, this);
        dynamic_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0||s.length()==6){
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                }else{
                    imm.showSoftInput(dynamic_code,InputMethodManager.SHOW_FORCED);
                }
            }
        });
        dynamic_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0||s.length()==11){
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                }else{
                    imm.showSoftInput(dynamic_number,InputMethodManager.SHOW_FORCED);
                }
                if (s.length()==11){
                    dynamic_verification.setClickable(true);
                    dynamic_verification.setTextColor(getResources().getColor(R.color.colorAccent));
                }else{
                    dynamic_verification.setClickable(false);
                    dynamic_verification.setTextColor(getResources().getColor(R.color.gray));
                }
                if (s.length()==0){
                    dynamic_cls.setVisibility(View.INVISIBLE);
                    dynamic_cls.setClickable(false);
                }
                else{
                    dynamic_cls.setVisibility(View.VISIBLE);
                    dynamic_cls.setClickable(true);
                }
            }
        });
        dynamic_number.requestFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dynamic_verification:
                mPresenter.sendCaptcha(dynamic_number.getText().toString(),"login");
                countDownTimerUtils.start();
                break;
            case R.id.dynamic_login:
                mPresenter.loginV(dynamic_number.getText().toString(),dynamic_code.getText().toString());
                break;
            case R.id.dynamic_clean:
                dynamic_number.setText("");
                break;
            case R.id.dynamic_back:
                startNewUI(IDLoginActivity.class);
                finish();
                break;
            default:
                break;
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimerUtils.cancel();
    }

    @Override
    public void LoginvSuccess() {
        if ( !mSharedPreferences.getBoolean("hasLogined",false) )
            mSharedPreferences.putBoolean("hasLogined",true);
        startNewUI(MainActivity.class);
        showToast("登录成功！");
        finish();
    }

    @Override
    public void sendFialRequest(String message) {
        showToast(message);
    }
}
