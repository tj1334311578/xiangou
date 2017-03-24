package com.example.administrator.xiangou.main.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.BaseActivity;

public class FindtwopagerActivity extends BaseActivity implements View.OnClickListener{
    private EditText findtwopager_number;
    private Button findtwopager_btn;
    private TextView findtwopager_message,findtwopager_verification;
    private ImageView findtwopager_cls,findtwopager_back;
    private CountDownTimerUtils countDownTimerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findtwopager);
        initView();

    }

    private void initView() {
        findtwopager_number= (EditText) findViewById(R.id.findpswtwo_numberedit);
        findtwopager_btn= (Button) findViewById(R.id.findpswtwo_login);
        findtwopager_message= (TextView) findViewById(R.id.findpswMessage_two);
        findtwopager_verification= (TextView) findViewById(R.id.sendagain_two);
        findtwopager_cls= (ImageView) findViewById(R.id.findpswtwo_clean);
        findtwopager_back= (ImageView) findViewById(R.id.findpswtwo_back);
        findtwopager_btn.setOnClickListener(this);
        findtwopager_verification.setOnClickListener(this);
        findtwopager_cls.setOnClickListener(this);
        findtwopager_back.setOnClickListener(this);
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
                    startActivity(new Intent(this, FindthreepagerActivity.class).putExtra("tel",getIntent().getStringExtra("tel")));
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
                startActivity(new Intent(this,FindonepagerActivity.class));
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
}
