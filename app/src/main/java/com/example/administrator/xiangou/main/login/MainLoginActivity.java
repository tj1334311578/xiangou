package com.example.administrator.xiangou.main.login;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.main.MainActivity;
import com.example.administrator.xiangou.tool.BaseActivity;

/**
 * Created by Administrator on 2017/3/22.
 */

public class MainLoginActivity extends BaseActivity implements View.OnClickListener{
    private TextView mainlogin_Dynamic,mainlogin_forget,mainlogin_mainregister;
    private ImageView mainlogin_backimg,mainlogin_userimg,mainlogin_cls;
    private EditText mainLogin_number,mainLogin_psw;
    private Button mainLoginBtn;
    private InputMethodManager imm;
    private boolean phoneistrue=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);
        initView();
    }

    private void initView() {
        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mainLogin_psw= (EditText) findViewById(R.id.mainlogin_paswordedit);
        mainLogin_number= (EditText) findViewById(R.id.mainlogin_numberedit);

        mainLoginBtn= (Button) findViewById(R.id.mainlogin_login);
        mainlogin_Dynamic= (TextView) findViewById(R.id.mainlogin_Dynamic_login);
        mainlogin_forget= (TextView) findViewById(R.id.mainlogin_forgetpassword);
        mainlogin_mainregister= (TextView) findViewById(R.id.mainlogin_register);
        mainlogin_backimg= (ImageView) findViewById(R.id.mainlogin_back);
        mainlogin_cls= (ImageView) findViewById(R.id.mainlogin_clean);
        mainlogin_cls.setOnClickListener(this);
        mainlogin_Dynamic.setOnClickListener(this);
        mainlogin_forget.setOnClickListener(this);
        mainlogin_mainregister.setOnClickListener(this);
        mainlogin_backimg.setOnClickListener(this);
        mainLogin_psw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0){
                    mainLoginBtn.setBackground(getResources().getDrawable(R.drawable.unround20dp));
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                }else if (s.length()==16){
                    if (mainLogin_number.getText().length()==11){
                        mainLoginBtn.setOnClickListener(MainLoginActivity.this);
                        mainLoginBtn.setBackground(getResources().getDrawable(R.drawable.round20dp));
                    }else{
                        mainLoginBtn.setBackground(getResources().getDrawable(R.drawable.unround20dp));
                    }
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                }else{
                    mainLoginBtn.setBackground(getResources().getDrawable(R.drawable.unround20dp));
                    imm.showSoftInput(mainLogin_psw,InputMethodManager.SHOW_FORCED);
                }
            }
        });
        mainLogin_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0){
                    mainlogin_cls.setVisibility(View.INVISIBLE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                    mainLoginBtn.setFocusable(false);
                    mainLoginBtn.setClickable(false);
                    mainLoginBtn.setBackground(getResources().getDrawable(R.drawable.unround20dp));
                }else if (s.length()==11)
                {
                    if (mainLogin_psw.getText().length()==16){
                        mainLoginBtn.setOnClickListener(MainLoginActivity.this);
                        mainLoginBtn.setBackground(getResources().getDrawable(R.drawable.round20dp));
                    }else{
                        mainLoginBtn.setBackground(getResources().getDrawable(R.drawable.unround20dp));
                    }
                    mainLoginBtn.setFocusable(true);
                    mainlogin_cls.setVisibility(View.VISIBLE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                    mainLoginBtn.setClickable(true);
                }else {
                    mainLoginBtn.setBackground(getResources().getDrawable(R.drawable.unround20dp));
                    mainLoginBtn.setFocusable(false);
                    mainlogin_cls.setVisibility(View.VISIBLE);
                    imm.showSoftInput(mainLogin_psw, InputMethodManager.SHOW_FORCED);
                    mainLoginBtn.setClickable(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId()!=R.id.mainlogin_clean)
        this.finish();
        switch (v.getId()) {
            case R.id.mainlogin_login:
                Toast.makeText(this, "登录页面成功跳转或失败信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mainlogin_Dynamic_login:
                startActivity(new Intent(MainLoginActivity.this,DynamicActivity.class));
                break;
            case R.id.mainlogin_forgetpassword:
                Log.e("TAG", "onClick: ");
                this.finish();
                startActivity(new Intent(MainLoginActivity.this,FindonepagerActivity.class));
                break;
            case R.id.mainlogin_register:
                this.finish();
                Log.e("register", "onClick: " );
//                startActivity(new Intent(MainLoginActivity.this,MainRegisterActivity.class));
                startActivity(new Intent(MainLoginActivity.this,MainRegisterActivity.class));
                break;
            case R.id.mainlogin_clean:
                mainLogin_number.setText("");
                break;
            case R.id.mainlogin_back:
                Toast.makeText(this, "返回上一级", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
