package com.example.administrator.xiangou.mine.setting;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mine.setting.feedback.FeedBackActivity;
import com.example.administrator.xiangou.mine.setting.manageraddress.ManagerAddressActivity;
import com.example.administrator.xiangou.mine.setting.personal.PersonalActivity;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SettingActivity extends MVPBaseActivity<SettingContract.View, SettingPresenter> implements SettingContract.View,View.OnClickListener {
//    @BindView(R.id.setting_back)
    ImageView settingBack;
//    @BindView(R.id.person_setting_Btn)
//    ImageView personalBtn;
////    @BindView(R.id.setting_address_Btn)
//    ImageView addressBtn;
////    @BindView(R.id.Version_information_Btn)
//    ImageView versionBtn;
////    @BindView(R.id.Help_counseling_Btn)
//    ImageView helpBtn;
////    @BindView(R.id.Clear_cache_img)
//    ImageView clean_cacheBtn;
////    @BindView(R.id.Feedback_Btn)
//    ImageView feedBtn;
    Button unloginBtn;
    private Dialog mySelfDialog;
    //    @BindViews({R.id.person_setting_Btn,R.id.setting_address_Btn,R.id.Version_information_Btn,R.id.Help_counseling_Btn,R.id.Clear_cache_img,R.id.Feedback_Btn})
//    List<ImageButton> Btns;
    LinearLayout personalBtn,addressBtn,versionBtn,helpBtn,clean_cacheBtn,feedBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        initView();
    }

    private void initView() {
        settingBack= findContentView(R.id.setting_back,true);
        personalBtn= findContentView(R.id.person_setting_Ll,true);
        addressBtn= findContentView(R.id.person_address_Ll,true);
        versionBtn= findContentView(R.id.Version_information_Ll,true);
        helpBtn= findContentView(R.id.Help_counseling_Ll,true);
        clean_cacheBtn= findContentView(R.id.Clear_cache_Ll,true);
        feedBtn= findContentView(R.id.Feedback_Ll,true);
        unloginBtn= findContentView(R.id.exit_login,true);
    }

    @Override
    public void sendFialRequest(String message) {

    }
//    //注解点击事件，注意不要放在Oncreate()等生命周期中，否则编译报错
//    @OnClick({R.id.setting_back,R.id.person_setting_Btn,R.id.setting_address_Btn,R.id.Version_information_Btn,
//            R.id.Help_counseling_Btn,R.id.Clear_cache_img,R.id.Feedback_Btn})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_back:
                finish();
                showToast("返回");
                break;
            case R.id.person_setting_Ll:
                showToast("0");
                startNewUI(PersonalActivity.class);
                break;
            case R.id.person_address_Ll:
                showToast("1");
                startNewUI(ManagerAddressActivity.class);
                break;
            case R.id.Version_information_Ll:
                showToast("2版本信息：V1.00版本\n我们正在持续更新中，谢谢支持！");
                break;
            case R.id.Help_counseling_Ll:
                showToast("3");
                break;
            case R.id.Clear_cache_Ll:
                showToast("4");
                break;
            case R.id.Feedback_Ll:
                startNewUI(FeedBackActivity.class);
                showToast("5");
                break;
            case R.id.exit_login:
                showToast("退出当前账号");
                exit_login();
//                mySelfDialog.setOtherOnclickListener(new MySelfDialog.otherOnclickListener() {
//                    @Override
//                    public void onOtherClick() {
//                        mySelfDialog.dismiss();
//                    }
//                });
//                mySelfDialog.setMessage("亲，您确定要退出当前账号吗？");
//                mySelfDialog.setNoOnclickListener("取消", new MySelfDialog.onNoOnclickListener() {
//                    @Override
//                    public void onNoClick() {
//                        showToast("点击了取消按钮");
//                        mySelfDialog.dismiss();
//                    }
//                });
//                mySelfDialog.setYesOnclickListener("确定", new MySelfDialog.onYesOnclickListener() {
//                    @Override
//                    public void onYesClick() {
//                        showToast("点击了确定按钮");
//                        logout();
//                        mySelfDialog.dismiss();
//                        finish();
//                    }
//                });

//                mySelfDialog.show();
//                logout();
//                finish();
                break;
            default:
                break;
        }

    }
    //退出登录方法
    private void exit_login() {
        mySelfDialog=new Dialog(this,R.style.custom_dialog);
        mySelfDialog.getWindow().setGravity(Gravity.CENTER);
        // 设置全屏
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = mySelfDialog.getWindow().getAttributes();
        lp.width = display.getWidth(); // 设置宽度
        mySelfDialog.getWindow().setAttributes(lp);

        final View location= LayoutInflater.from(this).inflate(R.layout.myself_dialog,null);
        mySelfDialog.setContentView(location);
        Button cancelBtn,makeSureBtn;
        cancelBtn= (Button) location.findViewById(R.id.dialog_cancel);
        makeSureBtn= (Button) location.findViewById(R.id.dialog_makeSure);
        //取消按钮
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySelfDialog.dismiss();
            }
        });
        //点击确定退出账号
        makeSureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                mySelfDialog.dismiss();
                finish();
            }
        });

        //设置点击区域外的监听
        location.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height =location.findViewById(R.id.myself_dialog_rl).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        mySelfDialog.dismiss();
                    }
                }
                return true;
            }
        });
        mySelfDialog.show();
    }
}
