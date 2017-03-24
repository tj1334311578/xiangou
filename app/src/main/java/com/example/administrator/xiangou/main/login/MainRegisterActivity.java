package com.example.administrator.xiangou.main.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.BaseActivity;

/**
 * Created by Administrator on 2017/3/23.
 */

public class MainRegisterActivity extends BaseActivity  implements View.OnClickListener{
    private EditText mainregister_number,mainregister_code;
    private TextView mainregister_verification,mainregister_xiyi;
    private CheckBox mainregister_vb;
    private ImageView mainregister_cls,mainregister_back;
    private Button mainregister_Btn;
    private CountDownTimerUtils mTimer;
    private InputMethodManager imm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_register);
        initView();
    }

    private void initView() {
        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mainregister_number= (EditText) findViewById(R.id.mainregister_number_edit);
        mainregister_code= (EditText) findViewById(R.id.mainregister_verification_code);

        mainregister_verification= (TextView) findViewById(R.id.mainregister_verificationBtn);
        mainregister_vb= (CheckBox) findViewById(R.id.register_cb);
        mainregister_cls= (ImageView) findViewById(R.id.mainregister_clean);
        mainregister_back= (ImageView) findViewById(R.id.mainregister_cancel);
        mainregister_Btn= (Button) findViewById(R.id.register_Btn);
        mainregister_verification.setClickable(false);
        mainregister_verification.setOnClickListener(this);
        mainregister_vb.setOnClickListener(this);
        mainregister_cls.setOnClickListener(this);
        mainregister_back.setOnClickListener(this);
        mainregister_Btn.setOnClickListener(this);
        //editview的添加字符改变监听
        mainregister_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0||s.length()==4){
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                }else{
                    imm.showSoftInput(mainregister_code,InputMethodManager.SHOW_FORCED);
                }
            }
        });
        mainregister_number.addTextChangedListener(new TextWatcher() {
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
                    imm.showSoftInput(mainregister_number,InputMethodManager.SHOW_FORCED);
                }
                if (s.length()==0){
                    mainregister_cls.setVisibility(View.INVISIBLE);
                    mainregister_cls.setClickable(false);
                }else {
                    mainregister_cls.setVisibility(View.VISIBLE);
                    mainregister_cls.setClickable(true);
                }
                if (s.length()==11){
                    mainregister_verification.setClickable(true);
                    mainregister_verification.setTextColor(getResources().getColor(R.color.textcolor_advs_topic_title));
                }else{
                    mainregister_verification.setClickable(false);
                    mainregister_verification.setTextColor(getResources().getColor(R.color.gray));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        mTimer = new CountDownTimerUtils(mainregister_number,mainregister_verification,30000,1000,this);
        switch (v.getId()){
            case R.id.mainregister_cancel://返回键
                finish();
                startActivity(new Intent(this,MainLoginActivity.class));
                break;
            case R.id.mainregister_clean://清除键
                mainregister_number.setText("");
                break;
            case R.id.register_cb://checkbox键
                break;
            case R.id.mainregister_verificationBtn://获取验证码键
                Toast.makeText(this, "verification", Toast.LENGTH_SHORT).show();
                mTimer.start();
                break;
            case R.id.register_Btn://下一步Button键

                finish();
                startActivity(new Intent(this,MainRegisterTwoActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();//关闭定时器
    }
}
