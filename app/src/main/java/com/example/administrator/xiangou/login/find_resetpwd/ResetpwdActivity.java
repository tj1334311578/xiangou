package com.example.administrator.xiangou.login.find_resetpwd;


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
import com.example.administrator.xiangou.login.idlogin.IDLoginActivity;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;

public class ResetpwdActivity extends MVPBaseActivity<ResetpwdContract.View, ResetpwdPresenter>
        implements ResetpwdContract.View ,View.OnClickListener{
    private ImageView findthreepager_back,findthreepager_cls;
    private EditText findthreepager_psw,findthreepager_pswagain;
    private CheckBox findthreepager_check;
    private Button findthreepager_btn;
    private static boolean isChecked;
    private String[] mStrs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findthreepager);
        Intent intent = getIntent();
        mStrs = intent.getStringArrayExtra("datas_findpwd");
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
        findthreepager_psw.requestFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.findpswthree_back:
                finish();
                break;
            case R.id.findpswthree_clean:
                findthreepager_psw.setText("");
                break;
            case R.id.findcheckbox_three:
                isChecked=!isChecked;
                Log.e("TAG", "onClick: "+findthreepager_check.isChecked() +"/\n"+isChecked);
                findthreepager_check.setChecked(isChecked);
                break;
            case R.id.findpswthree_login:
                mPresenter.resetPwd(mStrs[0],findthreepager_pswagain.getText().toString(),mStrs[1]);
                break;
        }
    }

    @Override
    public void sendFialRequest(String message) {
        showToast(message);
    }

    @Override
    public void resetPwdSuccess() {
        showToast("密码设置成功!");
        startNewUI(IDLoginActivity.class);
    }
}
