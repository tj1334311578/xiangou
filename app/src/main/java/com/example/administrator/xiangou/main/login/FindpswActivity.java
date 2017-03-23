package com.example.administrator.xiangou.main.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.BaseActivity;

/**
 * Created by Administrator on 2017/3/22.
 */

public class FindpswActivity extends BaseActivity implements View.OnClickListener{
    private TextView findmessage,finderrormsg,showorhidepassword,sendagain;
    private EditText findpassword,findnumber;
    private ImageView findback,findclean;
    private CheckBox findcheck;
    private Button findbtn ;
    private boolean isChecked=false;
    private static int state=1;
    private InputMethodManager imm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        setContentView(R.layout.findreturnpsw);
        initView();
    }

    private void initView() {
        findmessage= (TextView) findViewById(R.id.findpswMessage);
        findpassword= (EditText) findViewById(R.id.findpsw_paswordedit);
        findnumber= (EditText) findViewById(R.id.findpsw_numberedit);
        findback= (ImageView) findViewById(R.id.findpsw_back);
        findclean= (ImageView) findViewById(R.id.findpsw_clean);
        findcheck= (CheckBox) findViewById(R.id.findcheckbox);
        findbtn= (Button) findViewById(R.id.findpsw_login);
        finderrormsg= (TextView) findViewById(R.id.findpsw_error_msg);
        showorhidepassword= (TextView) findViewById(R.id.inorvisibility);
        sendagain= (TextView) findViewById(R.id.sendagain);
        showNextView(state);
        findbtn.setOnClickListener(this);
        findback.setOnClickListener(this);
        findclean.setOnClickListener(this);
        findcheck.setOnClickListener(this);
        findcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    findnumber.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    findpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else{
                    findnumber.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    findpassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        findpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                switch (state){
                    case 3:
                        if (s.length()==0||s.length()==16){
                            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                        }else {
                            imm.showSoftInput(findnumber,InputMethodManager.SHOW_FORCED);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        findnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                switch (state) {
                    case 1:
                        if (s.length() == 0||s.length()==11) {
                            if (s.length()==0){
                                findclean.setVisibility(View.INVISIBLE);
                                findbtn.setClickable(false);
                            }else{
                                findclean.setVisibility(View.VISIBLE);
                                findbtn.setClickable(true);
                            }
                            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                        }else {
                            findbtn.setClickable(false);
                            imm.showSoftInput(findnumber, InputMethodManager.SHOW_FORCED);
                            findclean.setVisibility(View.VISIBLE);
                            findclean.setClickable(true);
                        }
                        break;
                    case 2:
                        if (s.length()==0||s.length()==4){
                            sendagain.setClickable(true);
                            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                        }else {
                            imm.showSoftInput(findnumber, InputMethodManager.SHOW_FORCED);
                            sendagain.setClickable(false);
                        }
                        break;
                    case 3:
                        if (s.length()==0||s.length()==16){
                            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                        }
                        else {
                            imm.showSoftInput(findnumber, InputMethodManager.SHOW_FORCED);
                        }
                        break;
                    default: break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.findpsw_back:
                state--;
                showNextView(state);
                break;
            case R.id.findpsw_clean:
                findnumber.setText("");
                break;
            case R.id.findcheckbox:
                isChecked=!isChecked;
                //显示密码
                if (state==3)
                    findcheck.setChecked(isChecked);
                break;
            case R.id.findpsw_login:
                    state++;
                showNextView(state);
                break;
        }
    }

    private void showNextView(int state) {
        switch (state){
            case 0:
                this.finish();
                startActivity(new Intent(this,MainLoginActivity.class));
                break;
            case 1:
                findmessage.setVisibility(View.INVISIBLE);
                findViewById(R.id.findpsw_passwordll).setVisibility(View.GONE);
                findnumber.setHint("请输入号码");
                findnumber.setInputType(InputType.TYPE_CLASS_NUMBER);
                InputFilter[] filter={new InputFilter.LengthFilter(11)};
                findnumber.setFilters(filter);
                findnumber.setText("");
                finderrormsg.setVisibility(View.GONE);
                findcheck.setVisibility(View.GONE);
                showorhidepassword.setVisibility(View.GONE);
                findbtn.setVisibility(View.VISIBLE);
                findbtn.setText("下一步");
                findViewById(R.id.findpsw_userll).setVisibility(View.VISIBLE);
                findclean.setVisibility(View.INVISIBLE);
                sendagain.setVisibility(View.GONE);
                sendagain.setText("重新发送");
                break;
            case 2:
                findmessage.setVisibility(View.VISIBLE);
                findmessage.setText("已向你的手机发送验证码请输入：");
                findViewById(R.id.findpsw_passwordll).setVisibility(View.GONE);
                findnumber.setHint("请输入验证码");
                findnumber.setInputType(InputType.TYPE_CLASS_NUMBER);
                InputFilter[] filters={new InputFilter.LengthFilter(4)};
                findnumber.setFilters(filters);
                finderrormsg.setVisibility(View.GONE);
                findcheck.setVisibility(View.GONE);
                showorhidepassword.setVisibility(View.GONE);
                findbtn.setVisibility(View.VISIBLE);
                findbtn.setText("下一步");
                findnumber.setText("");
                findViewById(R.id.findpsw_userll).setVisibility(View.VISIBLE);
                findclean.setVisibility(View.INVISIBLE);
                sendagain.setVisibility(View.VISIBLE);
                sendagain.setText("重新发送(60s)");
                break;
            case 3:
                findmessage.setVisibility(View.INVISIBLE);
                findViewById(R.id.findpsw_passwordll).setVisibility(View.VISIBLE);
                findnumber.setHint("输入新密码");
                findnumber.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                InputFilter[] filterss={new InputFilter.LengthFilter(16)};
                findnumber.setFilters(filterss);
                findpassword.setHint("确认新密码");
                findnumber.setText("");
                findpassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                findpassword.setFilters(filterss);
                finderrormsg.setVisibility(View.VISIBLE);
                findcheck.setVisibility(View.VISIBLE);
                showorhidepassword.setVisibility(View.VISIBLE);
                findbtn.setVisibility(View.VISIBLE);
                findbtn.setText("下一步");
                findViewById(R.id.findpsw_userll).setVisibility(View.VISIBLE);
                findclean.setVisibility(View.INVISIBLE);
                sendagain.setVisibility(View.GONE);
                break;
            case 4:
//                state--;
                Toast.makeText(this, "错误信息或者跳转到登录信息", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
