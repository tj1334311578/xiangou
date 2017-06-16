package com.example.administrator.xiangou.mine.setting.modifypassword;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.BaseActivity;

import org.w3c.dom.Text;

/**
 * 作者： tj on 2017/6/14.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class HintMessageActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_modify_password_hintmessage);
        initView();
    }

    private void initView() {
        ((TextView)findContentView(R.id.setting_head_center)).setText("系统提示");
        findContentView(R.id.setting_head_back);
        findContentView(R.id.setting_head_right).setVisibility(View.GONE);
        findContentView(R.id.modify_password_sure);
        ImageView hint_img=findContentView(R.id.modify_password_hint_img,false);
        TextView hint_message=findContentView(R.id.modify_password_hint_msg,false);
        int code=getIntent().getIntExtra("code",0);
        switch (code){
            case 1://缺少必填参数
                hint_message.setText("缺少必填参数");
                break;
            case 101://账号或密码错误
                hint_message.setText("账号或密码错误");
                break;
            case 200://成功
                hint_message.setText("修改成功");
                break;
            case 102://系统繁忙
                hint_message.setText("系统繁忙");
                break;
            default://未知错误
                hint_message.setText("未知错误");
                break;
        }
        if (code==200){
            hint_img.setImageResource(R.drawable.girl_v);
        }else{
            hint_img.setImageResource(R.drawable.error);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_head_back:
                finish();
                break;
            case R.id.modify_password_sure:
                setResult(RESULT_OK,new Intent().putExtra("code",200));
                finish();
                break;
        }
    }
}
