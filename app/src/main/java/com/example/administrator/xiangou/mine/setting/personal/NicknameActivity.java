package com.example.administrator.xiangou.mine.setting.personal;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.tool.BaseActivity;

/**
 * Created by Administrator on 2017/4/24.
 */

public class NicknameActivity extends BaseActivity {
    private ImageView backBtn,CleanTv;
    private TextView TitleTv,SaveTv;
    private EditText mNickName;
    private String nickName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_nickname);
        initView();
    }

    private void initView() {
        backBtn= findContentView(R.id.setting_head_back);
        TitleTv= findContentView(R.id.setting_head_center,false);
        SaveTv= findContentView(R.id.setting_head_right);
        CleanTv=findContentView(R.id.setting_nickname_delimage);
        mNickName =findContentView(R.id.setting_nickname_edit,false);
        nickName = new String();
        if (getUser().getNickname()!=null) {
            mNickName.setText(getUser().getNickname());
            nickName = getUser().getNickname();
        }
        TitleTv.setText("昵称");
        SaveTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void onClick(View v) {
        if (v==backBtn){
            finish();
        }else if (v==SaveTv){
            nickName= mNickName.getText().toString();
//            Log.e("nickName", "onClick: " + nickName);
            if (nickName.length()>0){
                uploadNickname(nickName);
            }else{
                showToast("昵称不能为空");
            }
        }else if(v==CleanTv){
            mNickName.getText().clear();
            nickName = null;
        }

    }

    private void changeSuccess(){
        showToast("昵称修改成功！");
        Intent intent=new Intent();
        intent.putExtra("nickname",getUser().getNickname());
        setResult(RESULT_OK,intent);
        finish();
    }
    private void uploadNickname(String nickname) {
//        Log.e("nickAt",getUser().getUser_id()+ "<-id- -uploadNickname: " + nickname);
        addSubscription(mApiService.uploadUserDetials(
                getUser().getUser_id(), 0, nickname, null),
                new BaseSubscriber<Captcha>(this) {
                    @Override
                    public void onNext(Captcha captcha) {
//                        Log.e("nickAt", "onNext: " + captcha.toString());
                        switch (captcha.getState().getCode()){
                            case 200:
                                getUser().setNickname(mNickName.getText().toString());
                                changeSuccess();
                                break;
                            case 100:
                                showToast(captcha.getState().getMsg());
                        }
                    }

                    @Override
                    public void onFinish() {}

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
//                        Log.e("error", "onError: "+e.toString() );
                    }
                }
        );
    }
}
