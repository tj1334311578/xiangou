package com.example.administrator.xiangou.mine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
import com.example.administrator.xiangou.mine.followpage.FollowPageActivity;
import com.example.administrator.xiangou.mine.myfootprint.MyFootPrintActivity;
import com.example.administrator.xiangou.mine.myorder.MyOrderActivity;
import com.example.administrator.xiangou.mine.mystore.MyStoreActivity;
import com.example.administrator.xiangou.mine.setting.SettingActivity;
import com.example.administrator.xiangou.mine.store_application.StoreApplicationActivity;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;
import com.example.administrator.xiangou.tool.CustomImageView;
import com.example.administrator.xiangou.tool.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.administrator.xiangou.R.mipmap.personal_footprint_icon;

public class MineFragment extends MVPBaseFragment<MineContract.View, MinePresenter>
        implements MineContract.View {
    private static final int APPLYCODE=101;
    private ListView listView;
    private int content_imgC[]={personal_footprint_icon, R.mipmap.personal_comment_icon,
            R.mipmap.mine_share_icon,R.mipmap.mine_shop_icon};
    private int content_imgS[]={R.mipmap.mine_shop_icon ,R.mipmap.personal_footprint_icon,
            R.mipmap.personal_comment_icon,R.mipmap.mine_share_icon};
    private String content_textC[]= {"我的足迹","我的评论","我的分享","申请店铺"};
    private String content_textS[]= {"我的店铺","我的足迹","我的评论","我的分享"};
    private CustomImageView mHeadImgCiv;
    private TextView mUserLevelTv,mLevelNumberTv,mUserNameTv,mMessageTv,mUnpaidTv,mWaitDekiveryTv,mReceiveTv,mEvaluationTv,mReturnsOrSalesTv;
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
        mPresenter.IDlogin( getSP().getString("IDLogin_TelNumber",null),
                getSP().getString("IDLogin_PWD",null) );
        //        setLoginCall(new LoginCall() {
        //            @Override
        //            public void callSuccess(LoginBean.DataBean data) {
        //                Log.e("miancall", "callSuccess: " );
        //
        //            }
        //
        //            @Override
        //            public void callError(ExceptionHandle.ResponeThrowable e) {
        //                Log.e("maincall", "callError: "  );
        //            }
        //
        //            @Override
        //            public void callDealMore(Object o) {
        //
        //            }
        //        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,R.layout.fragment_mine);
    }

    @Override
    public void onStart() {
        super.onStart();
        showToast("isVisible:"+getUserVisibleHint()+"----isLogin"+getSP().isLogined()+"user id:"+getUser().getUser_id());
        if (getUserVisibleHint()){
            if (!getSP().isLogined()) {
                startNewUI(IDLoginActivity.class);
            }
        }
//        //先从本地保存的数据中获取用户信息
//        if (getSP().getString("user_info",null)!=null) {
//            Log.e("minefg", "onCreate inituser" + getSP().getString("user_info",null));
//            setbUserBySP(getSP().getString("user_info", null));
//        }
        initDate();
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
        findContentView(R.id.returns_Rl);
        mUnpaidTv = findContentView(R.id.mine_unpaid_tv);
        mWaitDekiveryTv = findContentView(R.id.mine_wait_delivery_tv);
        mReceiveTv = findContentView(R.id.mine_receive_goods_tv);
        mEvaluationTv = findContentView(R.id.mine_pending_evaluation_tv);
        mReturnsOrSalesTv = findContentView(R.id.mine_returns_sales_tv);
        //底下4小只--列表
        listView= findContentView(R.id.mine_list,false);
        //ListView点击事件设置
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) listView.getChildAt(position).findViewById(R.id.mine_item_text);
                Log.e("MineFg", "onItemClick: "+tv.getText() +" 被点击了 "+position);
                switch (tv.getText().toString()){
                    case "我的足迹":
                        startNewUI(MyFootPrintActivity.class);
                        break;
                    case "我的评论":
                        startNewUI(Goods_rankingActivity.class);
                        break;
                    case "我的分享":
                        startNewUI(ClassificationTabActivity.class);
                        break;
                    case "申请店铺":
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


//        initDate();
    }

    private void initDate() {
//        if (getUser().getHead_pic()!=null){
//            loadImg(getUser().getHead_pic(),mHeadImgCiv);
//        }
        initImageView(mHeadImgCiv);

        setTextToTv(mMessageTv,mine_MsgCount);
        setTextToTv(mUserLevelTv,"V"+getUser().getLevel());
        setTextToTv(mLevelNumberTv,getUser().getExperience());
        setTextToTv(mUserNameTv,getUser().getNickname());
        setTextToTv(mUnpaidTv,getUser().getWaitPay());
        setTextToTv(mWaitDekiveryTv,getUser().getWaitSend());
        setTextToTv(mReceiveTv,getUser().getWaitReceive());
        setTextToTv(mEvaluationTv,getUser().getWaitCcomment());
        setTextToTv(mReturnsOrSalesTv,getUser().getRefund());

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

    private void initImageView(ImageView imageView){
        Uri uri = getSP().getImgUri();
        if (uri!=null){
            ImageUtils.loadLocationImg(getContext(),uri,imageView);
        }else if (getUser().getHead_pic()!=null){
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
                Toast.makeText(getActivity(), "点击关注", Toast.LENGTH_SHORT).show();
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
            case R.id.returns_Rl:
                Toast.makeText(getActivity(), "点击退货or售后", Toast.LENGTH_SHORT).show();
//                startNewUICarryStr(MyOrderActivity.class,"position","0");
                break;
        }

    }


    @Override
    public void sendFialRequest(String message) {
        showToast(message);
    }

    @Override
    public void ReLoginidSuccess(LoginBean.DataBean data) {
        initDate();
        showToast("success");
    }

    public class MineAdapter extends BaseAdapter {
        private Context mContext;
        private List<ItemImage> Datas;

        public MineAdapter(Context context, List<ItemImage> datas) {
            this.mContext = context;
            this.Datas = datas;
        }

        @Override
        public int getCount() {
            return Datas.size();
        }

        @Override
        public Object getItem(int position) {
            return Datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder=new ViewHolder();
            if (convertView==null)
            {
                convertView= LayoutInflater.from(mContext).inflate(R.layout.mine_item,null,false);
                viewHolder.imageView= (ImageView) convertView.findViewById(R.id.mine_item_img);
                Log.e("MineFg", "getView: "+viewHolder.imageView);
                viewHolder.textView= (TextView) convertView.findViewById(R.id.mine_item_text);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.imageView.setImageResource(Datas.get(position).getSrc());
            viewHolder.textView.setText(Datas.get(position).getStr());
            return convertView;
        }
        public class ViewHolder{
            public ImageView imageView;
            public TextView textView;
        }
    }
}
