package com.example.administrator.xiangou.mine.setting.personal;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.tool.BaseActivity;

/**
 * Created by Administrator on 2017/4/24.
 */

public class SexActivity extends BaseActivity {
    private ImageView backBtn;
    private TextView TitleTv,SaveTv;
    private RadioButton Rtb_nan,Rtb_nv;
    private int mSexTag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_sex);
        initView();
    }

    private void initView() {
        Rtb_nan=findContentView(R.id.sex_nan,false);
        Rtb_nan.setChecked(true);
        Rtb_nv=findContentView(R.id.sex_nv,false);
        Rtb_nv.setChecked(false);
        backBtn= findContentView(R.id.setting_head_back);
        TitleTv= (TextView) findViewById(R.id.setting_sex_head).findViewById(R.id.setting_head_center);
        SaveTv= findContentView(R.id.setting_head_right);
        TitleTv.setText("性别");
        SaveTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void onClick(View v) {
        if (v==backBtn){
            finish();
        }else if (v==SaveTv){
            Intent intent=new Intent();
            if (Rtb_nan.isChecked()){
                mSexTag =1;
                intent.putExtra("sex","男");
                setResult(RESULT_OK,intent);
            }else if (Rtb_nv.isChecked()){
                mSexTag =2;
                intent.putExtra("sex","女");
                setResult(RESULT_OK,intent);
            }
            uploadSex(mSexTag);
        }
    }

    private void requestSuccess(){
        showToast("性别修改成功！");
        Intent intent=new Intent();
        switch (mSexTag){
            case 1:
                getUser().setSex(1);
                intent.putExtra("sex","男");
                break;
            case 2:
                getUser().setSex(2);
                intent.putExtra("sex","女");
                break;
        }
        setResult(RESULT_OK,intent);
        finish();
    }
    private void toastError(String message){
        showToast(message);
    }

    private void uploadSex(int sex_tag) {
//        Log.e("sex", "uploadSex: "+sex_tag +"\nbUser:"+getUser().getUser_id());
        addSubscription(mApiService.uploadUserDetials(getUser().getUser_id(),sex_tag,null,null),
                new BaseSubscriber<Captcha>(this) {
                    @Override
                    public void onNext(Captcha captcha) {
//                        Log.e("Detial", "onNext: "+captcha.getData().toString() );
                        switch (captcha.getState().getCode()){
                            case 200:
                                requestSuccess();
                                break;
                            default:
                                toastError("修改性别失败！code:"+captcha.getState().getCode());
                                break;
                        }
                    }


                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
//                        Log.e("error", "onError: "+e.toString());
                        toastError(e.getLocalizedMessage());
                    }
                });
    }
}
