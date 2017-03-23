//package com.example.administrator.xiangou.main.login;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.os.CountDownTimer;
//import android.support.annotation.Nullable;
//import android.text.Editable;
//import android.text.InputFilter;
//import android.text.InputType;
//import android.text.Spannable;
//import android.text.SpannableString;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.text.style.ForegroundColorSpan;
//import android.util.Log;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.administrator.xiangou.R;
//import com.example.administrator.xiangou.tool.BaseActivity;
//import com.example.administrator.xiangou.tool.Constant;
//
//import okhttp3.HttpUrl;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.scalars.ScalarsConverterFactory;
//
//
///**
// * Created by Administrator on 2017/3/17.
// * 注册activity
// */
//
//public class RegisterActivity extends BaseActivity implements View.OnClickListener{
//    private EditText phoneNumber,verificationCode;
//    private ImageView register_clean,register_cancel;
//    private TextView verificationBtn;
//    private TextView registerBtn;
//    private String phonenumber,register_verificationcode,psw;
//    private  static int state=1;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//        initView();
//    }
//    private void initView() {
//        verificationCode= (EditText) findViewById(R.id.register_verification_code);
//        phoneNumber= (EditText) findViewById(R.id.register_number_edit);
//        register_clean= (ImageView) findViewById(R.id.register_clean);
//        verificationBtn= (TextView) findViewById(R.id.register_verificationBtn);
////      verificationBtn.setClickable(false);
//
//        Toast.makeText(this, "clickable:"+verificationBtn.isClickable(), Toast.LENGTH_SHORT).show();
//
//        registerBtn= (TextView) findViewById(R.id.registerBtn);
//        register_cancel= (ImageView) findViewById(R.id.register_cancel);
//        firstStateView();
//        verificationCode.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (state==1){
//                    if (s.length()==4 || s.length()==0){
//                        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
//                    }else{
//                        imm.showSoftInput(verificationCode,InputMethodManager.SHOW_FORCED);
//                    }
//                }else if (state==2){
//                    if (s.length()==16 || s.length()==0){
//                        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
//                    }else{
//                        imm.showSoftInput(verificationCode,InputMethodManager.SHOW_FORCED);
//                    }
//                }
//            }
//        });
//        //设置edittext监听
//        phoneNumber.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                Log.e("TGA", "beforeTextChanged: "+s.toString()+"START:"+start );
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Log.e("TGA", "onTextChanged: "+s.toString()+"START:"+start );
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//                Log.e("TGA", "afterTextChanged: "+s.toString()+"Start"+s.length() );
//                if (state==1){
//
//                    if (s.length()==0){
//                        register_clean.setVisibility(View.INVISIBLE);
//                        register_clean.setClickable(false);
//                        register_clean.setFocusable(false);
//                        verificationBtn.setFocusable(false);
//                        verificationBtn.setClickable(false);
//                        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
//                    }else if (s.length()==11){
//                        verificationBtn.setClickable(true);
//                        verificationBtn.setFocusable(true);
//                        verificationBtn.setTextColor(getResources().getColor(R.color.textcolor_pink));
//                        register_clean.setVisibility(View.VISIBLE);
//                        register_clean.setClickable(true);
//                        register_clean.setFocusable(true);
//                        //隐藏键盘
//                        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
//                    }
//                    else{
//                        register_clean.setVisibility(View.VISIBLE);
//                        register_clean.setClickable(true);
//                        register_clean.setFocusable(true);
//                        verificationBtn.setClickable(false);
//                        verificationBtn.setFocusable(false);
//                        verificationBtn.setTextColor(getResources().getColor(R.color.gray));
//                        imm.showSoftInput(phoneNumber, InputMethodManager.SHOW_FORCED);
//                    }
//                }else if (state==2)
//                {
//                    if (s.length()==0){
//                        register_clean.setVisibility(View.GONE);
//                        register_clean.setClickable(false);
//                        register_clean.setFocusable(false);
//                        verificationBtn.setFocusable(false);
//                        verificationBtn.setClickable(false);
//                        imm.hideSoftInputFromWindow(phoneNumber.getWindowToken(), 0);
//                    }else if (s.length()==16){
//                        verificationBtn.setClickable(true);
//                        verificationBtn.setFocusable(true);
//                        verificationBtn.setTextColor(getResources().getColor(R.color.textcolor_pink));
//                        register_clean.setVisibility(View.VISIBLE);
//                        register_clean.setClickable(true);
//                        register_clean.setFocusable(true);
//                        //隐藏键盘
//                        imm.hideSoftInputFromWindow(phoneNumber.getWindowToken(), 0);
//                    }else
//                    {
//                        register_clean.setVisibility(View.VISIBLE);
//                        register_clean.setClickable(true);
//                        register_clean.setFocusable(true);
//                        verificationBtn.setClickable(false);
//                        verificationBtn.setFocusable(false);
//                        verificationBtn.setTextColor(getResources().getColor(R.color.gray));
//                        imm.showSoftInput(phoneNumber, InputMethodManager.SHOW_FORCED);
//                    }
//                }
//
//                Log.e("TAG", "textchangenext: "+verificationBtn.isClickable() );
////                Toast.makeText(RegisterActivity.this, "clickablffefefe:"+verificationBtn.isClickable(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        verificationBtn.setOnClickListener(this);
//        registerBtn.setOnClickListener(this);
//        register_clean.setOnClickListener(this);
//        register_cancel.setOnClickListener(this);
//    }
//    @Override
//    public void onClick(View v) {
//        Log.e("TAG", "onClick: "+v.isClickable() );
//        switch (v.getId()){
//            case R.id.register_verificationBtn:
////                verificationBtn.setClickable(false);
//                //倒计时功能实现
//                Toast.makeText(this, "clickablefe:"+verificationBtn.isClickable(), Toast.LENGTH_SHORT).show();
//                showVerificationView(v);
//                Toast.makeText(this, "clickablefe:"+verificationBtn.isClickable(), Toast.LENGTH_SHORT).show();
////               sendVerification();
//                break;
//            case R.id.registerBtn:
//                //注册页面的切换;
//                showAboutNextView(++state);
//                break;
//            case R.id.register_cancel:
//                if (state>0)
//                state--;
//                else
//                state=0;
//                showAboutNextView(state);
//                break;
//            case R.id.register_clean:
//                phoneNumber.setText("");
//            default:return ;
//        }
//    }
//
//    /**
//     * 动态显示再次发送所需间隔时间
//     */
//    private void showVerificationView(View v) {
//        new CountDownTimerUtils((TextView)v,60000,1000).start();
//    }
//
//    /**
//     * 注册登录进行下一步的视图
//     */
//    private void showAboutNextView(int state) {
//        switch (state){
//            case 0:
//                // TODO: 2017/3/20 goto login_pager
//                this.finish();
//                startActivity(new Intent(this,LoginActivity.class));
//                Toast.makeText(this, "跳到登录页面", Toast.LENGTH_SHORT).show();
//                break;
//            case 1:
//                Log.e("TAG", "showAboutNextView1 ^|: "+state );
//                firstStateView();
//                Log.e("TAG", "showAboutNextView: "+state );
//                break;
//            case 2:
//                secondStateView();
//                Log.e("TAG", "showAboutNextView2 |^: "+state );
//                break;
//            case 3:
//                Toast.makeText(this, "注册事件完成并自动登录", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }
//
//    /**
//     * 状态一
//     */
//    private void firstStateView() {
//        register_clean.setVisibility(View.VISIBLE);
//        verificationBtn.setVisibility(View.VISIBLE);
//
//        verificationBtn.setClickable(false);
//        verificationBtn.setFocusable(false);
//        verificationBtn.setTextColor(getResources().getColor(R.color.gray));
//
//        findViewById(R.id.register_ll).setVisibility(View.VISIBLE);
//        phoneNumber.setText("");
//        verificationCode.setText("");
//        registerBtn.setText("下一步");
//        phoneNumber.setHint("请输入手机号");
//        verificationCode.setHint("请输入验证码");
//        if (phoneNumber.getText().length()==0){
//            register_clean.setVisibility(View.INVISIBLE);
//        }
//        //设置可以输入任意字符
//        verificationCode.setInputType(InputType.TYPE_CLASS_NUMBER);
//        //手动设置maxLength为4
//        InputFilter[] filters = {new InputFilter.LengthFilter(4)};
//        verificationCode.setFilters(filters);
//
//        phoneNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
//        InputFilter[] filters1={new InputFilter.LengthFilter(11)};
//        phoneNumber.setFilters(filters1);
//
//        Log.e("TAG", "firstStateView: "+verificationBtn.isClickable());
//    }
//    /**
//     * 状态二
//     */
//    private void secondStateView() {
//        //设置布局
//        register_clean.setVisibility(View.GONE);
//        verificationBtn.setVisibility(View.GONE);
//        findViewById(R.id.register_ll).setVisibility(View.INVISIBLE);
//        phoneNumber.setText("");
//        verificationCode.setText("");
//        registerBtn.setText("注册");
//        phoneNumber.setHint("输入8--12位字母（区分大小写）、数字组合密码");
//        verificationCode.setHint("请再次输入密码");
//        //设置可以输入限制字符
//        verificationCode.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);//设置不可见密码规则设置
//        phoneNumber.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        //手动设置maxLength为20
//        InputFilter[] filters = {new InputFilter.LengthFilter(16)};
//        verificationCode.setFilters(filters);
//        phoneNumber.setFilters(filters);
//        Log.e("TAG", "secondStateView: "+verificationBtn.isClickable());
//
//    }
//
//    //发送验证码
//    private void sendVerification() {
//        phonenumber=phoneNumber.getText().toString();
//        Log.e("verification", "sendVerification: "+phonenumber);
//        if (!TextUtils.isEmpty(phonenumber)){
//            Retrofit retrofit =new Retrofit.Builder()
//                    .baseUrl(HttpUrl.parse(Constant.KEY_INFO))
//                    //增加返回值为String的支持
//                    .addConverterFactory(ScalarsConverterFactory.create())
//                    //增加返回值为Oservable<T>的支持
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .build();
//            RequestService requestServices=retrofit.create(RequestService.class);
//            Call<ResponseBody> call=requestServices.getCode(phonenumber);
////            call.enqueue(new Callback<Response>() {
////                @Override
////                public void onResponse(Call<Response> call, Response<Response> response) {
////                    Log.e("===","getCode:" +response.body().toString());
////                }
////
////                @Override
////                public void onFailure(Call<Response> call, Throwable t) {
////                    Log.e("===","getCode error!");
////                }
////            });
//            call.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                    Log.e("===","getCode:" +response.body().toString());
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Log.e("===","getCode error!");
//                }
//            });
//        }
//    }
//
//    /**
//     * 注册方法
//     */
//    private void registerMessage() {
//        //获得用户输入的信息
//        register_verificationcode=verificationCode.getText().toString();
////        psw=userPassword.getText().toString();
//        if (!TextUtils.isEmpty(phonenumber) &&
//                !TextUtils.isEmpty(register_verificationcode)) {
//            if (Utils.isNumber(phonenumber)) {  //验证号码格式是否符合
//                //调用RequestApiData中的getRegisterData方法进行注册，传入用户输入
//                //的昵称，邮箱、密码，以及解析数据的bean对象和callback对象（回调到自身）
//                // RequestApiData.getInstance()
//                Retrofit retrofit=new Retrofit.Builder()
//                        .baseUrl(Constant.KEY_INFO)
//                        //增加返回值为String的支持
//                        .addConverterFactory(ScalarsConverterFactory.create())
//                        //增加返回值为Gson的支持(以实体类返回)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        //增加返回值为Oservable<T>的支持
//                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                        .build();
//                RequestService requestServices=retrofit.create(RequestService.class);
//                Call<ResponseBody> call=requestServices.getRegister(phonenumber,register_verificationcode,psw);
//                call.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                        Log.e("===","return:" +response.body().toString());
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Log.e("===", "onFailure: 失败" );
//                    }
//                });
//
//            }
//
//        }
//
//    }
//
//    /**
//     * 设置倒计时工具类
//     */
//
//    public class CountDownTimerUtils extends CountDownTimer {
//        private TextView mTextView;
//
//        /**
//         * @param textView          The TextView
//         *
//         *
//         * @param millisInFuture    The number of millis in the future from the call  倒计总时间
//         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
//         *                          is called.
//         * @param countDownInterval The interval along the way to receiver   每几个单位时间接受一次
//         *                          {@link #onTick(long)} callbacks.
//         */
//        public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval) {
//            super(millisInFuture, countDownInterval);
//            this.mTextView = textView;
//        }
//
//        @Override
//        public void onTick(long millisUntilFinished) {
//            mTextView.setClickable(false); //设置不可点击
//            mTextView.setText(millisUntilFinished / 1000 + "秒后可重新发送");  //设置倒计时时间
////            mTextView.setBackgroundResource(R.drawable.bg_identify_code_press); //设置按钮为灰色，这时是不能点击的
//            mTextView.setTextColor(getResources().getColor(R.color.gray));
//            /**
//             * 超链接 URLSpan
//             * 文字背景颜色 BackgroundColorSpan
//             * 文字颜色 ForegroundColorSpan
//             * 字体大小 AbsoluteSizeSpan
//             * 粗体、斜体 StyleSpan
//             * 删除线 StrikethroughSpan
//             * 下划线 UnderlineSpan
//             * 图片 ImageSpan
//             * http://blog.csdn.net/ah200614435/article/details/7914459
//             */
//            SpannableString spannableString = new SpannableString(mTextView.getText().toString());  //获取按钮上的文字
//            ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
//            /**
//             * public void setSpan(Object what, int start, int end, int flags) {
//             * 主要是start跟end，start是起始位置,无论中英文，都算一个。
//             * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
//             */
//            spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
//            mTextView.setText(spannableString);
//        }
//        @Override
//        public void onFinish() {
//            mTextView.setText("重新获取验证码");
//            mTextView.setClickable(true);//重新获得点击
////            mTextView.setBackgroundResource(R.drawable.bg_identify_code_normal);  //还原背景色
//            if (phoneNumber.getText().length()==0){
//                mTextView.setTextColor(getResources().getColor(R.color.gray));
//            }
//            else mTextView.setTextColor(getResources().getColor(R.color.textcolor_advs_topic_title));
//        }
//    }
//}