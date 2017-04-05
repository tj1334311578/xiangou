package com.example.administrator.xiangou.login.registerverify;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.login.register.RegisterActivity;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.CountDownTimerUtils;

/**
 * 注册页面--输入号码获取验证码
 */
public class RegisterVerifyActivity extends MVPBaseActivity<RegisterVerifyContract.View, RegisterVerifyPresenter>
        implements RegisterVerifyContract.View ,View.OnClickListener{

    private EditText register_number,register_code;
    private TextView register_verification,register_xiyi;
    private CheckBox register_cb;
    private ImageView register_cls,mainregister_back;
    private Button mRegister_NextBtn;
    private CountDownTimerUtils mTimer;
    private InputMethodManager imm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_register);
        initView();

        showToast("创建RegisterVerifyActivity");
    }

    private void initView() {
        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        register_number = (EditText) findViewById(R.id.mainregister_number_edit);
        register_code= (EditText) findViewById(R.id.mainregister_verification_code);
        register_verification = (TextView) findViewById(R.id.mainregister_verificationBtn);
        register_cb = (CheckBox) findViewById(R.id.register_cb);
        register_cls = (ImageView) findViewById(R.id.mainregister_clean);
        mainregister_back= (ImageView) findViewById(R.id.mainregister_cancel);
        mRegister_NextBtn = (Button) findViewById(R.id.register_Btn);
        register_verification.setOnClickListener(this);
        register_cb.setOnClickListener(this);
        register_cls.setOnClickListener(this);
        mainregister_back.setOnClickListener(this);
        //editview的添加字符改变监听
        register_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0||s.length()==6){
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                    if (register_number.getText().length()==11){
                        register_cb.setChecked(true);
                    }else {
                        register_cb.setChecked(false);
                    }
                }else{
                    imm.showSoftInput(register_code,InputMethodManager.SHOW_FORCED);
                    register_cb.setChecked(false);
                }
            }
        });
        register_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0||s.length()==11){
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                    register_cls.setVisibility(View.GONE);
                    if (register_code.getText().length()>5){
//                        mRegister_NextBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_checked));
                        register_cb.setChecked(true);
                    }else{
//                        mRegister_NextBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_unchecked));
                        register_cb.setChecked(false);
                    }
                }else{
                    imm.showSoftInput(register_number,InputMethodManager.SHOW_FORCED);
                    register_cls.setVisibility(View.VISIBLE);
                    register_cb.setChecked(false);
                }
            }
        });

        register_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (register_cb.isChecked()){
                    mRegister_NextBtn.setOnClickListener(RegisterVerifyActivity.this);
                    mRegister_NextBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_checked));
                }else {
                    mRegister_NextBtn.setClickable(false);
                    mRegister_NextBtn.setBackground(getResources().getDrawable(R.drawable.btnbg_unchecked));
                }
            }
        });
        register_number.requestFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mainregister_cancel:
                //返回键
                finish();
                break;
            case R.id.mainregister_clean:
                //清除键
                register_number.setText("");
                break;
            case R.id.register_cb:
                //checkbox键
                Log.e("checkbox键", "onClick: "+ register_cb.isChecked() );
                if (register_cb.isChecked()){
                    register_cb.setChecked(false);
                }else {
                    register_cb.setChecked(true);
                }
                break;
            case R.id.mainregister_verificationBtn:
                //获取验证码键
                mPresenter.captcha(register_number.getText().toString());
                mTimer = new CountDownTimerUtils(register_number, register_verification,30000,1000,this);
                mTimer.start();
                break;
            case R.id.register_Btn:
                //下一步Button键
                if (register_code.getText()!=null){
                    Log.e(" edit ", "onClick: "+ register_number.getText().toString()+" /\n"+register_code.getText().toString() );
                    mPresenter.registerv(register_number.getText().toString(),register_code.getText().toString());
                }
                break;
        }
    }

    @Override
    public void verifySuccess() {
        startNewUICarryStr( RegisterActivity.class,"register_code",register_code.getText().toString());
        finish();
    }

    @Override
    public void sendFialRequest(String message) {
        showToast(message);
    }
}
