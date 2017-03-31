package com.example.administrator.xiangou.login.find_bytelephone;


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
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.login.find_verifyphone.VerifyPhoneActivity;
import com.example.administrator.xiangou.login.idlogin.IDLoginActivity;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;

public class FindByTelephoneActivity extends MVPBaseActivity<FindByTelephoneContract.View, FindByTelephonePresenter>
        implements FindByTelephoneContract.View ,View.OnClickListener{
    private EditText findonepager_number;
    private Button findonepager_next;
    private ImageView findonepager_back,findonepager_cls;
    private InputMethodManager imm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findonepager);
        initView();
    }

    private void initView() {
        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        findonepager_number= (EditText) findViewById(R.id.findpswone_numberedit);
        findonepager_next= (Button) findViewById(R.id.findpswone_login);
        findonepager_back= (ImageView) findViewById(R.id.findpswone_back);
        findonepager_cls= (ImageView) findViewById(R.id.findpswone_clean);
        findonepager_back.setOnClickListener(this);
        findonepager_cls.setOnClickListener(this);

        findonepager_number.addTextChangedListener(new TextWatcher() {
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
                    imm.showSoftInput(findonepager_number,InputMethodManager.SHOW_FORCED);
                }
                if (s.length()==11){
                    findonepager_next.setBackground(getResources().getDrawable(R.drawable.btnbg_checked));
                    findonepager_next.setOnClickListener(FindByTelephoneActivity.this);
                }else{
                    findonepager_next.setBackground(getResources().getDrawable(R.drawable.btnbg_unchecked));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.findpswone_login:
                if (findonepager_number.getText().length()==11) {
                    finish();
                    startActivity(new Intent(this,VerifyPhoneActivity.class).putExtra("tel",findonepager_number.getText().toString()));
                }else{
                    Toast.makeText(this, "请正确输入您的手机号", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.findpswone_back:
                startActivity(new Intent(this,IDLoginActivity.class));
                finish();
                break;
            case R.id.findpswone_clean:
                findonepager_number.setText("");
                break;
            default:
                break;
        }

    }
    @Override
    public void sendFialRequest(String message) {

    }
}
