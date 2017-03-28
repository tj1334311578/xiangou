package com.example.administrator.xiangou.main.login;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.BaseActivity;

/**
 * Created by Administrator on 2017/3/22.
 */

public class DynamicActivity extends BaseActivity implements View.OnClickListener{
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
        dynamic_cls.setOnClickListener(this);
        dynamic_btn.setOnClickListener(this);
        dynamic_back.setOnClickListener(this);
        countDownTimerUtils = new CountDownTimerUtils(dynamic_number, dynamic_verification, 30000, 1000, this);
        dynamic_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0||s.length()==11){
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                }else{
                    imm.showSoftInput(dynamic_number,InputMethodManager.SHOW_FORCED);
                }
                if (s.length()==11){
                    dynamic_verification.setClickable(true);
                    dynamic_verification.setOnClickListener(DynamicActivity.this);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dynamic_verification:
                countDownTimerUtils.start();
                break;
            case R.id.dynamic_login:
                Toast.makeText(this, "登录信息页面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dynamic_clean:
                dynamic_number.setText("");
                break;
            case R.id.dynamic_back:
                finish();
                startActivity(new Intent(this,MainLoginActivity.class));
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
}
