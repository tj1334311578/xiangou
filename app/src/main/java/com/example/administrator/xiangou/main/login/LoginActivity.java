//package com.example.administrator.xiangou.main.login;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.administrator.xiangou.R;
//import com.example.administrator.xiangou.tool.BaseActivity;
//
///**
// * Created by Administrator on 2017/3/20.
// */
//
//public class LoginActivity extends BaseActivity implements View.OnClickListener{
//    private TextView ForgetPsw,New_User_Register,DynamicLogin,LoginVerification,LoginTitle,LoginMessage,GetCode;
//    private Button LoginBtn;
//    private EditText LoginNumber,LoginPassword;
//    private ImageView LoginClean,NumberImg,PswImg,LoginBack,UserImg;
//    private static int state=1;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        initView();
//    }
//
//    private void initView() {
//        ForgetPsw= (TextView) findViewById(R.id.logging_forgetpassword);
//        New_User_Register= (TextView) findViewById(R.id.logging_register);
//        DynamicLogin= (TextView) findViewById(R.id.mainlogin_Dynamic_login);
//        LoginBtn= (Button) findViewById(R.id.logging_login);
//        LoginNumber= (EditText) findViewById(R.id.logging_numberedit);
//        LoginPassword= (EditText) findViewById(R.id.mainlogin_paswordedit);
//        LoginClean= (ImageView) findViewById(R.id.login_clean);
//        LoginVerification= (TextView) findViewById(R.id.login_verification);
//        NumberImg= (ImageView) findViewById(R.id.logging_userimg);
//        PswImg= (ImageView) findViewById(R.id.logging_passwordimg);
//        LoginBack= (ImageView) findViewById(R.id.logging_back);
//        LoginTitle= (TextView) findViewById(R.id.logging_log);
//        UserImg= (ImageView) findViewById(R.id.logging_img);
//        LoginMessage= (TextView) findViewById(R.id.loginMessage);
//        GetCode= (TextView) findViewById(R.id.login_verification);
//
//        GetCode.setOnClickListener(this);
//        ForgetPsw.setOnClickListener(this);
//        New_User_Register.setOnClickListener(this);
//        DynamicLogin.setOnClickListener(this);
//        LoginClean.setOnClickListener(this);
//        LoginBtn.setOnClickListener(this);
//        LoginBack.setOnClickListener(this);
//
//        LoginNumber.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                InputMethodManager imm= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                if (s.length()==0){
//                    LoginClean.setVisibility(View.INVISIBLE);
//                    LoginClean.setClickable(false);
//                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
//                } else{
//                    LoginClean.setClickable(true);
//                    LoginClean.setVisibility(View.VISIBLE);
//                }
//                if (s.length()==11){
//                    LoginVerification.setClickable(true);
//                    LoginVerification.setTextColor(getResources().getColor(R.color.textcolor_pink));
//                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
//                }
//                else{
//                    LoginVerification.setClickable(false);
//                    LoginVerification.setTextColor(getResources().getColor(R.color.gray));
//                    imm.showSoftInput(LoginNumber, InputMethodManager.SHOW_FORCED);
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId())
//        {
//            case R.id.mainlogin_Dynamic_login:
//                state=2;
//                showNextView(state);
//                break;
//            //登录
//            case R.id.logging_login:
//                break;
//            //忘记密码
//            case R.id.logging_forgetpassword:
//                state=3;
//                showNextView(state);
//                break;
//            case R.id.logging_register:
//                this.finish();
//                startActivity(new Intent(this,RegisterActivity.class));//跳转到注册界面
//                break;
//            case R.id.login_clean:
//                LoginNumber.setText("");
//                break;
//            case R.id.login_verification:
//
//                break;
//            //返回
//            case R.id.logging_back:
//                switch(state){state
//                    case 2:
//                        state=1;
//                        break;
//                    case 3:
//                        state=1;
//                        break;
//                    case 31:
//                        state=3;
//                        break;
//                    case 32:
//                        state=31;
//                        break;
//            }
//                showNextView(state);
//        }
//    }
//
//    private void showNextView(int state) {
//        switch (state)
//        {
//            case 0:
//                break;
//            case 1:
//                LoginTitle.setText("登录");
//                LoginTitle.setVisibility(View.VISIBLE);
//                UserImg.setVisibility(View.VISIBLE);
//                LoginNumber.setHint("请输入号码");
//                LoginPassword.setHint("请输入密码");
//                LoginPassword.setVisibility(View.VISIBLE);
//                DynamicLogin.setVisibility(View.VISIBLE);
//                LoginBtn.setBackgroundColor(getResources().getColor(R.color.gray));
//                ForgetPsw.setVisibility(View.VISIBLE);
//                LoginVerification.setVisibility(View.GONE);
//                NumberImg.setVisibility(View.VISIBLE);
//                PswImg.setVisibility(View.VISIBLE);
//                New_User_Register.setVisibility(View.VISIBLE);
//                LoginBack.setImageDrawable(getResources().getDrawable(R.drawable.error1));
//                LoginBtn.setText("登录");
//                LoginBtn.setBackground(getResources().getDrawable(R.drawable.round20dp));
//                break;
//            case 2:
//                DynamicLoginView();
//                break;
//            case 3:
//                findPasswordView();
//                break;
//
//        }
//    }
//
//    private void findPasswordView() {
//        LoginNumber.setHint("请输入号码");
//        LoginTitle.setText("找回密码");
//        DynamicLogin.setVisibility(View.GONE);
//        UserImg.setVisibility(View.GONE);
//        LoginPassword.setVisibility(View.GONE);
//        LoginBack.setImageDrawable(getResources().getDrawable(R.drawable.cancel));
//        LoginBtn.setBackground(getResources().getDrawable(R.drawable.round20dp));
//        ForgetPsw.setVisibility(View.GONE);
//        LoginVerification.setVisibility(View.GONE);
//        New_User_Register.setVisibility(View.GONE);
//        NumberImg.setVisibility(View.GONE);
//        PswImg.setVisibility(View.GONE);
//        LoginBtn.setText("下一步");
//    }
//
//    private void DynamicLoginView() {
//        LoginTitle.setText("动态登录");
//        DynamicLogin.setVisibility(View.INVISIBLE);
//        LoginNumber.setHint("请输入号码");
//        LoginPassword.setHint("请输入验证码");
//        LoginBack.setImageDrawable(getResources().getDrawable(R.drawable.cancel));
//        LoginTitle.setVisibility(View.VISIBLE);
//        UserImg.setVisibility(View.VISIBLE);
//        LoginBtn.setBackground(getResources().getDrawable(R.drawable.round20dp));
//        ForgetPsw.setVisibility(View.INVISIBLE);
//        LoginVerification.setVisibility(View.VISIBLE);
//        New_User_Register.setVisibility(View.INVISIBLE);
//        NumberImg.setVisibility(View.VISIBLE);
//        PswImg.setVisibility(View.VISIBLE);
//        LoginBtn.setText("登录");
//    }
//}
