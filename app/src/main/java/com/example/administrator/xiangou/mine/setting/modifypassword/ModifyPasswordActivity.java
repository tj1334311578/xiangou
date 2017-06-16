package com.example.administrator.xiangou.mine.setting.modifypassword;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.ContextUtils;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ModifyPasswordActivity extends MVPBaseActivity<ModifyPasswordContract.View, ModifyPasswordPresenter> implements ModifyPasswordContract.View {
    private EditText oldpasEdit,pasEdit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_modify_password);
        initView();
    }

    private void initView() {
        ((TextView)findContentView(R.id.setting_head_center,false)).setText("修改密码");
        findContentView(R.id.setting_head_back);
        findContentView(R.id.setting_head_right,false).setVisibility(View.GONE);
        oldpasEdit=findContentView(R.id.modify_password_oldpas_edit,false);
        pasEdit=findContentView(R.id.modify_password_pas_edit);
        findContentView(R.id.modify_password_nextbtn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_head_back://返回
                finish();
            break;
            case R.id.modify_password_nextbtn://下一步

                Log.e("ff", "checked: "+0);
                if (checked()){//检测输入密码格式是否正确
                    Log.e("ff", "checked: "+5);
                    mPresenter.modifyPassword(getUser().getUser_id(),ContextUtils.MD5(oldpasEdit.getText().toString()),ContextUtils.MD5(pasEdit.getText().toString()));
                }
                break;
        }
    }

    private boolean checked() {
        Log.e("ff", "checked: "+1);
        String old=oldpasEdit.getText().toString();
        String pas=pasEdit.getText().toString();
        if (old.getBytes().length>7&&old.getBytes().length<17){
            Log.e("ff", "checked: "+2);
            if (pas.getBytes().length>7&&pas.getBytes().length<17){
                if (!pas.equals(old)){
                    Log.e("ff", "checked: "+3);
                    return true;
                }else{
                    showToast("新旧密码不能相同！！！");
                }
            }else{
                showToast("新密码格式问题！！！");
            }
        }else{
            showToast("旧密码格式问题！！！");
        }
        return false;
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK)
        switch (requestCode){
            case 100:
                if (data.getIntExtra("code",0)==200)
                finish();
                break;
        }
    }

    @Override
    public void getCode(int code) {
        startNewUIForResult(HintMessageActivity.class,100,"code",code);
    }
}
