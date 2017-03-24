package com.example.administrator.xiangou.main.login;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/24.
 */

public class FindonepagerActivity extends BaseActivity implements View.OnClickListener {
    private EditText findonepager_number;
    private Button findonepager_next;
    private ImageView findonepager_back,findonepager_cls;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findonepager);
        initView();
    }

    private void initView() {
        findonepager_number= (EditText) findViewById(R.id.findpswone_numberedit);
        findonepager_next= (Button) findViewById(R.id.findpswone_login);
        findonepager_back= (ImageView) findViewById(R.id.findpswone_back);
        findonepager_cls= (ImageView) findViewById(R.id.findpswone_clean);
        findonepager_next.setOnClickListener(this);
        findonepager_back.setOnClickListener(this);
        findonepager_cls.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.findpswone_login:
                if (findonepager_number.getText().length()==11) {
                    finish();
                    startActivity(new Intent(this,FindtwopagerActivity.class).putExtra("tel",findonepager_number.getText().toString()));
                }else{
                    Toast.makeText(this, "请正确输入您的手机号", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.findpswone_back:
                finish();
                startActivity(new Intent(this,MainLoginActivity.class));
                break;
            case R.id.findpswone_clean:
                findonepager_number.setText("");
                break;
            default:
                break;
        }

    }
}
