package com.example.administrator.xiangou.tool;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.xiangou.R;

/**
 * Created by Administrator on 2017/4/24.
 */

public class MySelfDialog extends Dialog {
    private Button yes;//确定按钮
    private Button no;//取消按钮
    private TextView messageTv;//提示信息。
    private String messageStr;//从外界设置的消息文本
    //确定文本和取消文本的显示内容
    private String yesStr, noStr;
    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    private RelativeLayout rl;

    public void setOtherOnclickListener(MySelfDialog.otherOnclickListener otherOnclickListener) {
        this.otherOnclickListener = otherOnclickListener;
    }

    private otherOnclickListener otherOnclickListener;//点击控件外的监听

    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param str
     * @param onNoOnclickListener
     */
    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param str
     * @param onYesOnclickListener
     */
    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myself_dialog);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
        
    }
    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (messageStr!=null){
            messageTv.setText(messageStr);
        }
        //如果设置按钮的文字
        if (yesStr!=null){
            yes.setText(yesStr);
        }
        if (noStr!=null){
            no.setText(noStr);
        }
    }
    /**
     * 初始化界面控件
     */
    private void initView() {
        yes= (Button) findViewById(R.id.dialog_makeSure);
        no= (Button) findViewById(R.id.dialog_cancel);
        messageTv= (TextView) findViewById(R.id.dialog_message);
        rl = (RelativeLayout) findViewById(R.id.myself_dialog_rl);

    }
    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent(){
        rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height =findViewById(R.id.myself_dialog_rl).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
//                        mySelfDialog.dismiss();
                        otherOnclickListener.onOtherClick();
                    }
                }
                return true;
            }
        });
//        rl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (otherOnclickListener!=null){
//                    otherOnclickListener.onOtherClick();
//                }
//            }
//        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener!=null){
                    yesOnclickListener.onYesClick();
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   if (noOnclickListener!=null){
                       noOnclickListener.onNoClick();
                   }
            }
        });
    }
    public MySelfDialog(Context context) {
        super(context,R.style.custom_dialog);
    }

    public MySelfDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MySelfDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        public void onYesClick();
    }

    public interface onNoOnclickListener {
        public void onNoClick();
    }
    public interface otherOnclickListener{
        public void onOtherClick();
    }
    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     */
//    public void setTitle(String title) {
//        titleStr = title;
//    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     */
    public void setMessage(String message) {
        messageStr = message;
    }
}
