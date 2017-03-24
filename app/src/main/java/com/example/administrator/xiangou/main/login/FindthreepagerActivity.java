package com.example.administrator.xiangou.main.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findthreepager);
        initView();
    }

    private void initView() {
        findthreepager_back= (ImageView) findViewById(R.id.findpswthree_back);
        findthreepager_cls= (ImageView) findViewById(R.id.findpswthree_clean);
        findthreepager_psw= (EditText) findViewById(R.id.findpswthree_numberedit);
        findthreepager_pswagain= (EditText) findViewById(R.id.findpswthree_paswordedit);
        findthreepager_check= (CheckBox) findViewById(R.id.findcheckbox_three);
        findthreepager_btn= (Button) findViewById(R.id.findpswthree_login);
        findthreepager_back.setOnClickListener(this);
        findthreepager_cls.setOnClickListener(this);
        findthreepager_check.setOnClickListener(this);
        findthreepager_btn.setOnClickListener(this);
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

