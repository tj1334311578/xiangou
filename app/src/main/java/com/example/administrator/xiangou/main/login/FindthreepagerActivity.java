package com.example.administrator.xiangou.main.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.BaseActivity;

/**
 * A login screen that offers login via email/password.
 */
public class FindthreepagerActivity extends BaseActivity implements View.OnClickListener{
    private ImageView findthreepager_back,findthreepager_cls;
    private EditText findthreepager_psw,findthreepager_pswagain;
    private CheckBox findthreepager_check;
    private Button findthreepager_btn;
    private static boolean isChecked;
    private InputMethodManager imm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findthreepager);
        initView();
    }

    private void initView() {
        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        findthreepager_back= (ImageView) findViewById(R.id.findpswthree_back);
        findthreepager_cls= (ImageView) findViewById(R.id.findpswthree_clean);
        findthreepager_psw= (EditText) findViewById(R.id.findpswthree_numberedit);
        findthreepager_pswagain= (EditText) findViewById(R.id.findpswthree_paswordedit);
        findthreepager_check= (CheckBox) findViewById(R.id.findcheckbox_three);
        findthreepager_btn= (Button) findViewById(R.id.findpswthree_login);

        findthreepager_back.setOnClickListener(this);
        findthreepager_cls.setOnClickListener(this);
        findthreepager_check.setOnClickListener(this);

        findthreepager_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    findthreepager_psw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    findthreepager_pswagain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else{
                    findthreepager_psw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    findthreepager_pswagain.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });
        findthreepager_pswagain.addTextChangedListener(new TextWatcher() {
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
                    imm.showSoftInput(findthreepager_pswagain,InputMethodManager.SHOW_FORCED);
                }
                if (s.length()==16 && findthreepager_psw.getText().length()==16){
                    findthreepager_btn.setOnClickListener(FindthreepagerActivity.this);
                    findthreepager_btn.setBackground(getResources().getDrawable(R.drawable.round20dp));
                }else{
                    findthreepager_btn.setBackground(getResources().getDrawable(R.drawable.unround20dp));
                }
            }
        });
        findthreepager_psw.addTextChangedListener(new TextWatcher() {
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
                    imm.showSoftInput(findthreepager_psw,InputMethodManager.SHOW_FORCED);
                }
                if (s.length()==16 && findthreepager_pswagain.getText().length()==16){
                    findthreepager_btn.setOnClickListener(FindthreepagerActivity.this);
                    findthreepager_btn.setBackground(getResources().getDrawable(R.drawable.round20dp));
                }else{
                    findthreepager_btn.setBackground(getResources().getDrawable(R.drawable.unround20dp));
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.findpswthree_back:
                finish();
                startActivity(new Intent(this,FindtwopagerActivity.class).putExtra("tel",getIntent().getStringExtra("tel")));
                break;
            case R.id.findpswthree_clean:
                findthreepager_psw.setText("");
                break;
            case R.id.findcheckbox_three:
                Log.e("TAG", "onClick: "+findthreepager_check.isChecked() );
                isChecked=!isChecked;
                findthreepager_check.setChecked(isChecked);
                break;
            case R.id.findpswthree_login:
                break;
        }
    }
}

