package com.example.administrator.xiangou.login.find_verifyphone;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.administrator.xiangou.login.find_bytelephone.FindByTelephoneActivity;
import com.example.administrator.xiangou.login.find_resetpwd.ResetpwdActivity;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.CountDownTimerUtils;

public class VerifyPhoneActivity extends MVPBaseActivity<VerifyPhoneContract.View, VerifyPhonePresenter>
        implements VerifyPhoneContract.View ,View.OnClickListener{
    private EditText findtwopager_number;
    private Button findtwopager_btn;
    private TextView findtwopager_message,findtwopager_verification;
    private ImageView findtwopager_cls,findtwopager_back;
    private CountDownTimerUtils countDownTimerUtils;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findtwopager);
        initView();

    }

    private void initView() {
        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        findtwopager_number= (EditText) findViewById(R.id.findpswtwo_numberedit);
        findtwopager_btn= (Button) findViewById(R.id.findpswtwo_login);
        findtwopager_message= (TextView) findViewById(R.id.findpswMessage_two);
        findtwopager_verification= (TextView) findViewById(R.id.sendagain_two);
        findtwopager_cls= (ImageView) findViewById(R.id.findpswtwo_clean);
        findtwopager_back= (ImageView) findViewById(R.id.findpswtwo_back);



        //在设置监听中有一段代码为if（！isClickable）{ setClickable(true);}强制设置为可点击
        //        findtwopager_verification.setOnClickListener(this);
        findtwopager_cls.setOnClickListener(this);
        findtwopager_back.setOnClickListener(this);

        findtwopager_number.addTextChangedListener(new TextWatcher() {
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
                    imm.showSoftInput(findtwopager_number,InputMethodManager.SHOW_FORCED);
                }
                if (s.length()==6){
                    findtwopager_btn.setOnClickListener(VerifyPhoneActivity.this);
                    findtwopager_btn.setBackground(getResources().getDrawable(R.drawable.btnbg_checked));
                    findtwopager_verification.setClickable(true);
                    findtwopager_verification.setOnClickListener(VerifyPhoneActivity.this);
                }else
                {
                    findtwopager_btn.setBackground(getResources().getDrawable(R.drawable.btnbg_unchecked));
                    findtwopager_verification.setFocusable(false);
                    findtwopager_verification.setClickable(false);
                }
            }
        });

        if (getIntent()!=null){
            String getString=getIntent().getStringExtra("tel");
            if(getString.length()!=0){
                findtwopager_message.setText("已向您的手机"+getString.substring(0,3)+"..."+getString.substring(7,11)+"发送验证码，请注意查收");
            }
            countDownTimerUtils = new CountDownTimerUtils(findtwopager_number, findtwopager_verification, 30000, 1000, this);
        }}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.findpswtwo_login:
                if (findtwopager_number.getText().length()==6) {
                    finish();
                    startActivity(new Intent(this, ResetpwdActivity.class).putExtra("tel",getIntent().getStringExtra("tel")));
                }else{
                    Toast.makeText(this, "请正确输入您验证码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sendagain_two:
                countDownTimerUtils.start();
                break;
            case R.id.findpswtwo_clean:
                findtwopager_number.setText("");
                break;
            case R.id.findpswtwo_back:
                finish();
                startActivity(new Intent(this,FindByTelephoneActivity.class));
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
    public void sendFialRequest(String message) {

    }
}
