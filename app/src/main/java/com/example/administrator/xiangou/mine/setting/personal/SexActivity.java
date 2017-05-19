package com.example.administrator.xiangou.mine.setting.personal;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
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

public class SexActivity extends BaseActivity {
    private ImageView backBtn;
    private TextView TitleTv,SaveTv;
    private RadioButton Rtb_nan,Rtb_nv;
    protected XianGouApiService mApiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_sex);
        //不加会报空指针
        mApiService = RetrofitClient.getInstance(this).create(XianGouApiService.class);
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
            // TODO: 2017/4/24 请求存储修改后的用户信息
            int Sex_tag=1;
            Intent intent=new Intent();
            if (Rtb_nan.isChecked()){
                Sex_tag=1;
                intent.putExtra("sex","男");
                setResult(RESULT_OK,intent);
            }else if (Rtb_nv.isChecked()){
                Sex_tag=2;
                intent.putExtra("sex","女");
                setResult(RESULT_OK,intent);
            }
            uploadSex(Sex_tag);
        }
    }

    private void uploadSex(int sex_tag) {
        Log.e("sex", "uploadSex: "+sex_tag +"\nbUser:"+bUser.getUser_id());
        addSubscription(mApiService.uploadUserNickname(bUser.getUser_id(),sex_tag,null),
                new BaseSubscriber<PersonalDetialsBean>(this) {
                    @Override
                    public void onNext(PersonalDetialsBean Detial) {
                        Log.e("Detial", "onNext: "+Detial.getData().toString() );
                        if (Detial.getState().getCode()==200){
                            showToast("昵称修改成功！");

                        }else{
                            showToast("修改昵称失败！code:"+Detial.getState().getCode());
                        }
                    }

                    @Override
                    public void onFinish() {
                        finish();
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("error", "onError: "+e.toString());
                    }
                });
    }
}
