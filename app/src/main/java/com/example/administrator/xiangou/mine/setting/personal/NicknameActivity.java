package com.example.administrator.xiangou.mine.setting.personal;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.net.RetrofitClient;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.BaseActivity;

/**
 * Created by Administrator on 2017/4/24.
 */

public class NicknameActivity extends BaseActivity {
    private ImageView backBtn,CleanTv;
    private TextView TitleTv,SaveTv;
    private EditText nickName;
    protected XianGouApiService mApiService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_nickname);
        mApiService = RetrofitClient.getInstance(this).create(XianGouApiService.class);
        initView();
    }

    private void initView() {
        backBtn= findContentView(R.id.setting_head_back);
        TitleTv= (TextView) findViewById(R.id.setting_nickname_head).findViewById(R.id.setting_head_center);
        SaveTv= findContentView(R.id.setting_head_right);
        CleanTv=findContentView(R.id.setting_nickname_delimage);
        nickName=findContentView(R.id.setting_nickname_edit,false);
        TitleTv.setText("昵称");
        SaveTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void onClick(View v) {
        if (v==backBtn){
            finish();
        }else if (v==SaveTv){
            // TODO: 2017/4/24上传到服务器数据
            if (!nickName.getText().toString().equals("")){
            uploadNickname(nickName.getText().toString());
            showToast("昵称修改成功！");
            }else{
                showToast("昵称修改失败！\n可能存在原因：昵称修改为空");
            }
        }else if(v==CleanTv){
            nickName.setText("");
        }

    }

    private void uploadNickname(String nickname) {
        addSubscription(mApiService.uploadUserDetials(bUser.getUser_id(),0,nickname,null),
                new BaseSubscriber<PersonalDetialsBean>(this) {
                    @Override
                    public void onNext(PersonalDetialsBean Detial) {
                            if (Detial.getState().getCode()==200){
                                Intent intent=new Intent();
                                intent.putExtra("nickname",Detial.getData().toString());
                                setResult(RESULT_OK,intent);
                            }
                    }

                    @Override
                    public void onFinish() {
                        finish();
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("error", "onError: "+e.toString() );
                    }
                }
                );
    }
}
