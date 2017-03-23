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
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.BaseActivity;

/**
 * Created by Administrator on 2017/3/23.
 */

public class MainRegisterTwoActivity extends BaseActivity implements View.OnClickListener {
    private EditText twopager_password,twopager_password_sure;
    private ImageView twopager_cls,twopager_back;
    private Button twopager_Btn;
    private InputMethodManager imm;
    private TextWatcher textWatcher;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registertwopager);
        initView();
    }

    private void initView() {
        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        twopager_password= (EditText) findViewById(R.id.twopagerregister_number_edit);
        twopager_password_sure= (EditText) findViewById(R.id.twopagerregister_passwordsure);
        twopager_cls= (ImageView) findViewById(R.id.twopagerregister_clean);
        twopager_back= (ImageView) findViewById(R.id.twopagerregister_cancel);
        twopager_Btn= (Button) findViewById(R.id.twopagerregister_Btn);
        twopager_cls.setOnClickListener(this);
        twopager_back.setOnClickListener(this);
        twopager_Btn.setOnClickListener(this);
        twopager_password.addTextChangedListener(textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0||s.length()==16){
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
                }else{
                    imm.showSoftInput(twopager_password,InputMethodManager.SHOW_FORCED);
                }
            }
        });
        twopager_password_sure.addTextChangedListener(textWatcher);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.twopagerregister_clean://清除
                twopager_password.setText("");
                break;
            case R.id.twopagerregister_cancel://返回上一级
                startActivity(new Intent(this,MainRegisterActivity.class));
                break;
            case R.id.twopagerregister_Btn://进入下一级
                Toast.makeText(this, "注册成功或失败提醒", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
