package com.example.administrator.xiangou.login.idlogin;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.login.dynamiclogin.DynamicLoginActivity;
import com.example.administrator.xiangou.login.find_bytelephone.FindByTelephoneActivity;
import com.example.administrator.xiangou.login.registerverify.RegisterVerifyActivity;
import com.example.administrator.xiangou.main.MainActivity;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.CustomImageView;

public class IDLoginActivity extends MVPBaseActivity<IDLoginContract.View, IDLoginPresenter>
        implements IDLoginContract.View ,View.OnClickListener{
    private TextView IDlogin_Dynamic, IDLogin_Forget, IDLogin_Register;
    private ImageView IDLogin_Backimg, IDLogin_Cls;
    private CustomImageView IDLogin_Userimg;
    private EditText IDLogin_TelNumber, IDLogin_PWD;
    private Button mIDLoginBtn;
    private InputMethodManager imm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);
        initView();
    }

    private void initView() {
        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        IDLogin_PWD = (EditText) findViewById(R.id.mainlogin_paswordedit);
        IDLogin_TelNumber = (EditText) findViewById(R.id.mainlogin_numberedit);
        mIDLoginBtn = (Button) findViewById(R.id.mainlogin_login);
        IDlogin_Dynamic = (TextView) findViewById(R.id.mainlogin_Dynamic_login);
        IDLogin_Forget = (TextView) findViewById(R.id.mainlogin_forgetpassword);
        IDLogin_Register = (TextView) findViewById(R.id.mainlogin_register);
        IDLogin_Backimg = (ImageView) findViewById(R.id.mainlogin_back);
        IDLogin_Userimg = findContentView(R.id.mainlogin_img,false);
        //头像加载
        if (bUser.getHead_pic()!=null) {
            loadImg(bUser.getHead_pic(), IDLogin_Userimg);
        }
        IDLogin_Cls = (ImageView) findViewById(R.id.mainlogin_clean);
        IDLogin_Cls.setOnClickListener(this);
        IDlogin_Dynamic.setOnClickListener(this);
        IDLogin_Forget.setOnClickListener(this);
        IDLogin_Register.setOnClickListener(this);
        IDLogin_Backimg.setOnClickListener(this);
        IDLogin_PWD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0){
                    mIDLoginBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_unchecked));
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                    mIDLoginBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_unchecked));
                    mIDLoginBtn.setFocusable(false);
                    mIDLoginBtn.setClickable(false);
                }else if (s.length()>7){
                    if (IDLogin_TelNumber.getText().length()==11){
                        mIDLoginBtn.setOnClickListener(IDLoginActivity.this);
                        mIDLoginBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_checked));
                    }else{
                        mIDLoginBtn.setFocusable(false);
                        mIDLoginBtn.setClickable(false);
                        mIDLoginBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_unchecked));
                    }
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                }else{
                    mIDLoginBtn.setFocusable(false);
                    mIDLoginBtn.setClickable(false);
                    mIDLoginBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_unchecked));
                    imm.showSoftInput(IDLogin_PWD,InputMethodManager.SHOW_FORCED);
                }
            }
        });
        IDLogin_TelNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0){
                    IDLogin_Cls.setVisibility(View.INVISIBLE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                    mIDLoginBtn.setFocusable(false);
                    mIDLoginBtn.setClickable(false);
                    mIDLoginBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_unchecked));
                }else if (s.length()==11) {
                    if ( IDLogin_PWD.getText().length()>7 ){
                        mIDLoginBtn.setOnClickListener(IDLoginActivity.this);
                        mIDLoginBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_checked));
                    }else{
                        mIDLoginBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_unchecked));
                    }
                    mIDLoginBtn.setFocusable(true);
                    IDLogin_Cls.setVisibility(View.INVISIBLE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                    mIDLoginBtn.setClickable(true);
                }else {
                    mIDLoginBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_unchecked));
                    mIDLoginBtn.setFocusable(false);
                    IDLogin_Cls.setVisibility(View.VISIBLE);
                    imm.showSoftInput(IDLogin_PWD, InputMethodManager.SHOW_FORCED);
                    mIDLoginBtn.setClickable(false);
                }
            }
        });
        IDLogin_TelNumber.requestFocus();
    }

    @Override
    protected void onStart() {
        super.onStart();
        IDLogin_TelNumber.setText(bSharedPreferences.getString("IDLogin_TelNumber",""));
        IDLogin_PWD.setText(bSharedPreferences.getString("IDLogin_PWD",""));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainlogin_login:
                mPresenter.IDlogin(IDLogin_TelNumber.getText().toString(), IDLogin_PWD.getText().toString());
                break;
            case R.id.mainlogin_Dynamic_login:
                startNewUI(DynamicLoginActivity.class);
                break;
            case R.id.mainlogin_forgetpassword:
                startNewUI(FindByTelephoneActivity.class);
                this.finish();
                break;
            case R.id.mainlogin_register:
                startNewUI(RegisterVerifyActivity.class);
                break;
            case R.id.mainlogin_clean:
                IDLogin_TelNumber.setText("");
               break;
            case R.id.mainlogin_back:
                this.finish();
                break;
        }
    }

    //登录请求成功
    @Override
    public void LoginidSuccess(LoginBean.DataBean data) {
        Log.e("User", "LoginidSuccess: "+ bUser.toString());
        hasLogined();
        startNewUI(MainActivity.class);
        Log.e("code", "onNext: 4" );
        finish();
    }

    @Override
    public void sendFialRequest(String message) {
        showToast(message);
    }

    //保存信息
    @Override
    protected void onStop() {
        mPresenter.saveInfo("IDLogin_TelNumber",IDLogin_TelNumber.getText().toString(),
                "IDLogin_PWD",IDLogin_PWD.getText().toString());
        super.onStop();
    }
}
