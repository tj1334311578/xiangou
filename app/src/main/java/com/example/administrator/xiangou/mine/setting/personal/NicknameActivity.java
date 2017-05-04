package com.example.administrator.xiangou.mine.setting.personal;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.BaseActivity;

/**
 * Created by Administrator on 2017/4/24.
 */

public class NicknameActivity extends BaseActivity {
    private ImageView backBtn;
    private TextView TitleTv,SaveTv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_nickname);
        initView();
    }

    private void initView() {
        backBtn= findContentView(R.id.setting_head_back);
        TitleTv= (TextView) findViewById(R.id.setting_nickname_head).findViewById(R.id.setting_head_center);
        SaveTv= findContentView(R.id.setting_head_right);
        TitleTv.setText("昵称");
        SaveTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void onClick(View v) {
        if (v==backBtn){
            finish();
        }else if (v==SaveTv){
            // TODO: 2017/4/24 请求存储修改后的用户信息
        }

    }
}
