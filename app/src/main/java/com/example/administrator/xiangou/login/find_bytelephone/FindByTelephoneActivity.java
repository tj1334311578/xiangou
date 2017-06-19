package com.example.administrator.xiangou.login.find_bytelephone;


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
import com.example.administrator.xiangou.login.find_resetpwd.ResetpwdActivity;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.CountDownTimerUtils;

public class FindByTelephoneActivity extends MVPBaseActivity<FindByTelephoneContract.View, FindByTelephonePresenter>
        implements FindByTelephoneContract.View ,View.OnClickListener{
    private EditText mFindPSW_NumberEdt,mFindPSW_CaptchaEdt;
    private Button mFindPSW_NextBtn;
    private ImageView mFindPSW_BackIv, mFindPSW_CleanIv;
    private InputMethodManager imm;
    private TextView mFindPSW_SendCaptchaTv;
    private CountDownTimerUtils countDownTimerUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findonepager);
        initView();
    }

    private void initView() {
        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mFindPSW_NumberEdt = (EditText) findViewById(R.id.findpswone_number_edt);
        mFindPSW_CaptchaEdt = (EditText) findViewById(R.id.findpswone_captcha_edt);
        mFindPSW_SendCaptchaTv = (TextView) findViewById(R.id.findpswone_resend_tv);
        mFindPSW_SendCaptchaTv.setOnClickListener(FindByTelephoneActivity.this);
        mFindPSW_NextBtn = (Button) findViewById(R.id.findpswone_next_btn);
        mFindPSW_BackIv = (ImageView) findViewById(R.id.findpswone_back_iv);
        mFindPSW_CleanIv = (ImageView) findViewById(R.id.findpswone_clean_iv);
        mFindPSW_BackIv.setOnClickListener(this);
        mFindPSW_CleanIv.setOnClickListener(this);
        mFindPSW_NumberEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0||s.length()==11){
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                    if (mFindPSW_CaptchaEdt.getText().length()==6){
                        mFindPSW_NextBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_checked));
                        mFindPSW_NextBtn.setOnClickListener(FindByTelephoneActivity.this);
                    }else{
                        mFindPSW_NextBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_unchecked));
                        mFindPSW_NextBtn.setClickable(false);
                    }
                }else{
                    imm.showSoftInput(mFindPSW_NumberEdt,InputMethodManager.SHOW_FORCED);
                }
            }
        });
        mFindPSW_CaptchaEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0||s.length()==6){
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                    if (mFindPSW_NumberEdt.getText().length()==11){
                        mFindPSW_NextBtn.setOnClickListener(FindByTelephoneActivity.this);
                        mFindPSW_NextBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_checked));
                        mFindPSW_SendCaptchaTv.setClickable(true);
                        mFindPSW_SendCaptchaTv.setOnClickListener(FindByTelephoneActivity.this);
                    }else
                    {
                        mFindPSW_NextBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_unchecked));
                    }
                }else{
                    mFindPSW_CleanIv.setOnClickListener(FindByTelephoneActivity.this);
                    imm.showSoftInput(mFindPSW_CaptchaEdt,InputMethodManager.SHOW_FORCED);
                }
            }
        });
        //设置光标
        if (mFindPSW_NumberEdt.getText()==null){
            mFindPSW_NumberEdt.requestFocus();
        }else {
            mFindPSW_CaptchaEdt.requestFocus();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.findpswone_resend_tv:
                countDownTimerUtils = new CountDownTimerUtils(mFindPSW_CaptchaEdt, mFindPSW_SendCaptchaTv, 30000, 1000, this);
                countDownTimerUtils.start();
                mPresenter.getCaptcha(mFindPSW_NumberEdt.getText().toString(),"findpsw");
                break;
            case R.id.findpswone_back_iv:
                finish();
                break;
            case R.id.findpswone_clean_iv:
                mFindPSW_NumberEdt.setText("");
                break;
            case R.id.findpswone_next_btn:
                mPresenter.verifyCaptchaFindPwd( mFindPSW_NumberEdt.getText().toString(), mFindPSW_CaptchaEdt.getText().toString());
                break;
            default:
                break;
        }

    }
    @Override
    public void sendFialRequest(String message) {
        showToast(message);
    }

    @Override
    public void verifySuccess(String tel, String code) {
        String[] strs = {tel,code};
        getSP().putString("tel_findpwd",tel);
        getSP().putString("code_findpwd",code);
        startNewUICarryStr(ResetpwdActivity.class,"datas_findpwd",strs);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFindPSW_NumberEdt.setText(getSP().getString("tel_findpwd",""));
        mFindPSW_CaptchaEdt.setText(getSP().getString("code_findpwd",""));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimerUtils!=null)
            countDownTimerUtils.cancel();
    }
}
