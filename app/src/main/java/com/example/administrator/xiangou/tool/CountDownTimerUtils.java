package com.example.administrator.xiangou.tool;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;

/**
 * Created by Administrator on 2017/3/22.
 */

public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;
    private Context mContext;
    private EditText editText;

    /**
     * @param textView          The TextView
     *
     *
     * @param millisInFuture    The number of millis in the future from the call  倒计总时间
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receiver   每几个单位时间接受一次
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimerUtils(EditText editText, TextView textView, long millisInFuture, long countDownInterval, Context mcontext) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
        this.mContext=mcontext;
        this.editText=editText;
        Toast.makeText(mcontext, "countdowntimerutils", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false); //设置不可点击
        mTextView.setText( "重新发送("+millisUntilFinished / 1000 +"s)");  //设置倒计时时间
//      mTextView.setBackgroundResource(R.drawable.bg_identify_code_press); //设置按钮为灰色，这时是不能点击的
        mTextView.setTextColor(mContext.getResources().getColor(R.color.gray));
        /**
         * 超链接 URLSpan
         * 文字背景颜色 BackgroundColorSpan
         * 文字颜色 ForegroundColorSpan
         * 字体大小 AbsoluteSizeSpan
         * 粗体、斜体 StyleSpan
         * 删除线 StrikethroughSpan
         * 下划线 UnderlineSpan
         * 图片 ImageSpan
         * http://blog.csdn.net/ah200614435/article/details/7914459
         */
        SpannableString spannableString = new SpannableString(mTextView.getText().toString());  //获取按钮上的文字
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        /**
         * public void setSpan(Object what, int start, int end, int flags) {
         * 主要是start跟end，start是起始位置,无论中英文，都算一个。
         * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
         */
        spannableString.setSpan(span, 5, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
        mTextView.setText(spannableString);
    }

    @Override
    public void onFinish() {
        mTextView.setText("重新获取验证码");
        mTextView.setClickable(true);//重新获得点击
//            mTextView.setBackgroundResource(R.drawable.bg_identify_code_normal);  //还原背景色
        if (editText.getText().length()==0){
            mTextView.setTextColor(mContext.getResources().getColor(R.color.gray));
        }
        else mTextView.setTextColor(mContext.getResources().getColor(R.color.color_pink_fb4a89));
    }
}
