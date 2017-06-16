package com.example.administrator.xiangou.mine;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.classification.fragment.ClassificationTabActivity;
import com.example.administrator.xiangou.goods_sort.Goods_rankingActivity;
import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.login.idlogin.IDLoginActivity;
import com.example.administrator.xiangou.mine.couponpage.CouponPageActivity;
import com.example.administrator.xiangou.mine.feedback.FeedBackActivity;
import com.example.administrator.xiangou.mine.followpage.FollowPageActivity;
import com.example.administrator.xiangou.mine.myfootprint.MyFootPrintActivity;
import com.example.administrator.xiangou.mine.myorder.MyOrderActivity;
import com.example.administrator.xiangou.mine.mystore.MyStoreActivity;
import com.example.administrator.xiangou.mine.setting.SettingActivity;
import com.example.administrator.xiangou.mine.store_application.StoreApplicationActivity;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;
import com.example.administrator.xiangou.tool.CustomImageView;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.administrator.xiangou.tool.MySharedPreferences.KEY_USERIMG;

public class MineFragment extends MVPBaseFragment<MineContract.View, MinePresenter>
        implements MineContract.View {
    private static final int APPLYCODE=101;
    private ListView listView;
    private int content_imgC[]={R.mipmap.mine_share_icon,R.mipmap.mine_footmark_icon,
            R.mipmap.mine_feedback_icon,R.mipmap.mine_shop_icon};
    private int content_imgS[]={R.mipmap.mine_shop_icon ,R.mipmap.mine_share_icon,
            R.mipmap.mine_footmark_icon,R.mipmap.mine_feedback_icon};
    private String content_textC[]= {"我的分享","我的足迹","意见反馈","申请店铺"};
    private String content_textS[]= {"我的店铺","我的分享","我的足迹","意见反馈"};
    private CustomImageView mHeadImgCiv;
    private TextView mUserLevelTv,mLevelNumberTv,mUserNameTv,mMessageTv,mUnpaidTv,mWaitDekiveryTv,mReceiveTv,mEvaluationTv;
    private int mine_MsgCount=0;
    private boolean isFirst=true;
    private List<ItemImage> mList;
    private MineAdapter mListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //登录校验或更新
        loginCaptcha();
    }
    //登录校验或更新用户信息
    private void loginCaptcha(){
        if (getSP().isLogined()) {
            mPresenter.IDlogin(getSP().getString("IDLogin_TelNumber", null),
                    getSP().getString("IDLogin_PWD", null));
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,R.layout.fragment_mine);
    }

    @Override
    public void onStart() {
        super.onStart();
//        Log.e("MineFg", "onStart: " );
        showToast("isVisible:"+getUserVisibleHint()+"----isLogin"+getSP().isLogined()+"user id:"+getUser().getUser_id());
        if (getUserVisibleHint()){
            if (!getSP().isLogined()) {
                createDialog("用户账号尚未登录，是否去登录？");
            }
        }
        //先从本地保存的数据中获取用户信息
        initDate(true);
    }

    @Override
    public void initView() {
        findContentView(R.id.mine_message_rl);
        findContentView(R.id.mine_setup_iv);
        //用户信息展示
        mMessageTv = findContentView(R.id.mine_message_tv);
        mHeadImgCiv = findContentView(R.id.mine_user_img_iv);
        mUserLevelTv = findContentView(R.id.mine_level_tv);
        mLevelNumberTv = findContentView(R.id.mine_level_number_tv);
        mUserNameTv = findContentView(R.id.mine_username_tv);

        //场上三巨头
        findContentView(R.id.mine_attention);
        findContentView(R.id.mine_Coupon);
        findContentView(R.id.mine_sign_in);

        //我的订单
        findContentView(R.id.see_all_orders);
        findContentView(R.id.unpaid_Rl);
        findContentView(R.id.delivery_Rl);
        findContentView(R.id.receive_Rl);
        findContentView(R.id.evaluation_Rl);
//        findContentView(R.id.returns_Rl);
        mUnpaidTv = findContentView(R.id.mine_unpaid_tv);
        mWaitDekiveryTv = findContentView(R.id.mine_wait_delivery_tv);
        mReceiveTv = findContentView(R.id.mine_receive_goods_tv);
        mEvaluationTv = findContentView(R.id.mine_pending_evaluation_tv);
//        mReturnsOrSalesTv = findContentView(R.id.mine_returns_sales_tv);
        //底下4小只--列表
        listView= findContentView(R.id.mine_list,false);
        //ListView点击事件设置
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) listView.getChildAt(position).findViewById(R.id.mine_item_text);
//                Log.e("MineFg", "onItemClick: "+tv.getText() +" 被点击了 "+position);
                switch (tv.getText().toString()){
                    case "我的足迹":
                        startNewUI(MyFootPrintActivity.class);
                        break;
                    case "意见反馈":
//                        startNewUI(Goods_rankingActivity.class);
                        startNewUI(FeedBackActivity.class);
                        break;
                    case "我的分享":
                        startNewUI(ClassificationTabActivity.class);
                        break;
                    case "申请店铺":
                        //待修改--传操作类型
                        startNewUIForResult(StoreApplicationActivity.class,APPLYCODE,"user_id",getUser().getUser_id());
                        break;
                    case "我的店铺":
                        startNewUI(MyStoreActivity.class);
                        break;
                    default:
                        showToast(tv.getText().toString());
                        break;
                }
            }
        });
        //ListView禁止滑动设置
//        listView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction())
//                {
//                    case MotionEvent.ACTION_MOVE:
//                        return true ;
//                    default:
//                        return false;
//                }
//            }
//        });
    }

    private void initDate(boolean b) {
        initImageView(mHeadImgCiv,b);
        setTextToTv(mMessageTv,mine_MsgCount);//待修改
        setTextToTv(mUserLevelTv,"V"+getUser().getLevel());
        setTextToTv(mLevelNumberTv,getUser().getExperience());
        setTextToTv(mUserNameTv,getUser().getNickname());
        setTextToTv(mUnpaidTv,getUser().getWaitPay());
        setTextToTv(mWaitDekiveryTv,getUser().getWaitSend());
        setTextToTv(mReceiveTv,getUser().getWaitReceive());
        setTextToTv(mEvaluationTv,getUser().getWaitCcomment());
//        setTextToTv(mReturnsOrSalesTv,getUser().getRefund());
        initList();
    }
    private void initList() {
        //初始化数据
        if (mList==null) {
            mList = new ArrayList<>();
        }else {
            mList.clear();
        }
        switch (getUser().getType()){
            case 1://是用户
                switch (getUser().getStatus()) {//店铺审核状态：1审核中、2通过审核、3审核失败
                    case 1:
                        content_textC[content_textC.length - 1] = "申请审核中";
                        break;
//                    case 3:
//                        content_textC[content_textC.length - 1] = "店铺申请失败";
//                        break;
                }
                for (int i = 0; i < content_textC.length; i++) {
                    mList.add(new ItemImage(content_imgC[i], content_textC[i]));
                }
                break;
            case 3://即是卖家也是用户
                for (int i = 0; i < content_textC.length; i++) {
                    mList.add(new ItemImage(content_imgS[i], content_textS[i]));
                }
                break;
        }
        mListAdapter = new MineAdapter(getContext(), mList);
        listView.setAdapter(mListAdapter);
    }

    private void initImageView(ImageView imageView, boolean isInitImg){
        Uri uri = getSP().getImgUri(KEY_USERIMG);
        if (uri!=null){
            //            ImageUtils.loadLocationImg(getContext(),uri,imageView);
            if (isInitImg) {//只在初始化加载图片时才调用，刷新时不调用
//                Log.e("loadimg", "initImageView: by uri "+uri );
                loadImg(uri, imageView);
            }
            return;
        }else if (getUser().getHead_pic()!=null){
//            Log.e("loadimg", "initImageView: by getHead_pic" );
            loadImg(getUser().getHead_pic(),imageView);
        }
    }

    @Override
    public void setTextToTv(TextView textView, Object data) {
        if (data instanceof Integer){
            if (((int)data)==0 && textView!=mLevelNumberTv){
                textView.setVisibility(View.INVISIBLE);
            }else if (textView.getVisibility()!=View.VISIBLE){
                textView.setVisibility(View.VISIBLE);
            }
        }
        super.setTextToTv(textView, data);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null && resultCode == RESULT_OK) {
            switch (requestCode){
                case APPLYCODE:
                    // 店铺申请中  str = "申请审核中"
                    String str = data.getStringExtra("applying");
                    if (mList!=null) {
                        mList.get(mList.size() - 1).setStr(str);
                        mListAdapter.notifyDataSetChanged();
                    }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //设置
            case R.id.mine_setup_iv:
                Toast.makeText(getActivity(), "点击设置", Toast.LENGTH_SHORT).show();
                startNewUI(SettingActivity.class);
                break;
            //消息
            case R.id.mine_message_rl:
                Toast.makeText(getActivity(), "点击消息", Toast.LENGTH_SHORT).show();
                mPresenter.ceshi("alipay","xg201705221833249336",20);
                break;
            //用户图标
            case R.id.mine_user_img_iv:
                Toast.makeText(getActivity(), "点击用户图标", Toast.LENGTH_SHORT).show();
                startNewUI(IDLoginActivity.class);
                break;
            //等级图标
            case R.id.mine_level_tv:
                Toast.makeText(getActivity(), "点击等级图标", Toast.LENGTH_SHORT).show();
                break;
            //等级经验
            case R.id.mine_level_number_tv:
                Toast.makeText(getActivity(), "点击等级经验", Toast.LENGTH_SHORT).show();
                break;
            //用户名
            case R.id.mine_username_tv:
                Toast.makeText(getActivity(), "点击用户名", Toast.LENGTH_SHORT).show();
                break;
            //关注
            case R.id.mine_attention:
                startNewUI(FollowPageActivity.class);
                break;
            //优惠券
            case R.id.mine_Coupon:
                Toast.makeText(getActivity(), "点击优惠券", Toast.LENGTH_SHORT).show();
                startNewUI(CouponPageActivity.class);
                break;
            //签到
            case R.id.mine_sign_in:
                Toast.makeText(getActivity(), "点击签到", Toast.LENGTH_SHORT).show();
                mPresenter.callsigns(getUser().getUser_id());
                break;
            //查看所有订单
            case R.id.see_all_orders:
                Toast.makeText(getActivity(), "点击查看所有订单", Toast.LENGTH_SHORT).show();
                startNewUICarryStr(MyOrderActivity.class,"position","0");
                break;
            //未付款
            case R.id.unpaid_Rl:
                Toast.makeText(getActivity(), "点击未付款", Toast.LENGTH_SHORT).show();
                startNewUICarryStr(MyOrderActivity.class,"position","1");
                break;
            //等待发货
            case R.id.delivery_Rl:
                Toast.makeText(getActivity(), "点击等待发货", Toast.LENGTH_SHORT).show();
                startNewUICarryStr(MyOrderActivity.class,"position","2");
                break;
            //待收货
            case R.id.receive_Rl:
                Toast.makeText(getActivity(), "点击待收货", Toast.LENGTH_SHORT).show();
                startNewUICarryStr(MyOrderActivity.class,"position","3");
                break;
            //待评价
            case R.id.evaluation_Rl:
                Toast.makeText(getActivity(), "点击待评价", Toast.LENGTH_SHORT).show();
                startNewUICarryStr(MyOrderActivity.class,"position","4");
                break;
            //退货or售后
//            case R.id.returns_Rl:
//                Toast.makeText(getActivity(), "点击退货or售后", Toast.LENGTH_SHORT).show();
////                startNewUICarryStr(MyOrderActivity.class,"position","0");
//                break;
        }

    }
    @Override
    public void dataToView(int code, int value) {
        switch (code){
            case 100://今日已签到
                showToast("今日已签到");
//                getUser().setExperience(getUser().getExperience()+1);//得到后的数据
//                mLevelNumberTv.post( new Runnable() {
//                    @Override
//                    public void run() {
//                        mLevelNumberTv.setText(getUser().getExperience()+"");
//                        mLevelNumberTv.invalidate();
//                    }
//                });
                break;
            case 101://服务器繁忙
                showToast("服务器繁忙，请稍后再试");
                break;
            case 200://成功
                showToast("签到成功\n   +"+value+"闲购值");
                getUser().setExperience(getUser().getExperience()+value);//得到后的数据
                mLevelNumberTv.post( new Runnable() {
                    @Override
                    public void run() {
                        mLevelNumberTv.setText(getUser().getExperience()+"");
                        mLevelNumberTv.invalidate();
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void sendFialRequest(String message) {
        showToast(message);
    }

    @Override
    public void ReLoginidSuccess(LoginBean.DataBean data) {
        initDate(false);
    }

    public interface CallMineFragmentUnLoad {
        void callBackUnLoad();
    }
    public CallMineFragmentUnLoad mCallMineFragmentUnLoad;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallMineFragmentUnLoad = (CallMineFragmentUnLoad) context;
    }

    private void createDialog(String msg){
        final AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
        View contentLayout = LayoutInflater.from(getContext()).inflate(R.layout.custom_hintdialog,null);
        dialog.setView(contentLayout);
        TextView contentTv = (TextView) contentLayout.findViewById(R.id.content_hintdialog_tv);
        contentTv.setText(msg);
        Button cancelBtn = (Button) contentLayout.findViewById(R.id.cancel_hintdialog_btn);
        Button sureBtn = (Button) contentLayout.findViewById(R.id.sure_hintdialog_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mCallMineFragmentUnLoad.callBackUnLoad();
            }
        });
        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startNewUI(IDLoginActivity.class);
            }
        });
        dialog.setCancelable(false);//点击外部不关闭
        dialog.show();
    }
}
