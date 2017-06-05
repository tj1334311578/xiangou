package com.example.administrator.xiangou.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class MineFragment extends MVPBaseFragment<MineContract.View, MinePresenter>
        implements MineContract.View {
    private static final int APPLYCODE=101;
    private ListView listView;
    private int content_img[]={R.mipmap.personal_footprint_icon,
            R.mipmap.personal_comment_icon,R.mipmap.mine_share_icon,R.mipmap.mine_shop_icon};
    private String content_text[]=
//            new String[4];
            {"我的足迹","我的评论","我的分享","申请店铺"};
    private CustomImageView mHeadImgCiv;
    private TextView mUserLevelTv,mLevelNumberTv,mUserNameTv,mMessageTv,mUnpaidTv,mWaitDekiveryTv,mReceiveTv,mEvaluationTv,mReturnsOrSalesTv;
    private int mine_MsgCount=0;
    private static int userType=0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,R.layout.fragment_mine);
    }


    @Override
    public void onResume() {
        super.onResume();
        showToast("isVisible:"+getUserVisibleHint()+"----isLogin"+isLogined()+"user id:"+bUser.getUser_id());
        if (getUserVisibleHint()){
            if (!isLogined()) {
                startNewUI(IDLoginActivity.class);
            }
        }
        if (bUser!= null){
            initDate();
        }
    }

    @Override
    public void initView() {
        findContentView(R.id.mine_message_rl);
        listView= findContentView(R.id.mine_list,false);
        findContentView(R.id.mine_setup_iv);
        mMessageTv = findContentView(R.id.mine_message_tv);
        mHeadImgCiv = findContentView(R.id.mine_user_img_iv);
        mUserLevelTv = findContentView(R.id.mine_level_tv);
        mLevelNumberTv = findContentView(R.id.mine_level_number_tv);
        mUserNameTv = findContentView(R.id.mine_username_tv);
        Log.e("name","initView: " + bUser.getNickname()+"\ntype:"+bUser.getType()+"\nstatus:"+bUser.getStatus());

        findContentView(R.id.mine_attention);
        findContentView(R.id.mine_Coupon);
        findContentView(R.id.mine_sign_in);
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

        //ListView禁止滑动设置
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_MOVE:
                        return true ;
                    default:
                        return false;
                }
            }
        });
        //获取用户类型
        userType=bUser.getType();
//        userType=3;

        //ListView点击事件设置
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAG", "onItemClick: "+""+"被点击了");
                TextView tv = (TextView) listView.getChildAt(position).findViewById(R.id.mine_item_text);
                //等价于=>((TextView)(listView.getChildAt(position).findViewById(R.id.mine_item_text)))
                Toast.makeText(getContext(),tv.getText() +"被点击了", Toast.LENGTH_SHORT).show();
                //设置点击位置改变条件
                switch (userType){
                    case 3:
                        if (position==0)
                            position=3;
                        else
                            position-=1;
                        break;
                    default:break;
                }
                Log.e("jjf", "onItemClick: "+position );
                switch (position){
                    case 0:
//                        startNewUI(StoreHomeActivity.class);
                        startNewUI(MyFootPrintActivity.class);
                        break;
                    case 1:
                        startNewUI(Goods_rankingActivity.class);
                        break;
                    case 2:
                        startNewUI(ClassificationTabActivity.class);
                        break;
                    case 3:
                        switch (userType){
                            case 1:
                                startNewUIForResult(StoreApplicationActivity.class,APPLYCODE,"user_id",bUser.getUser_id());
                                break;
                            case 3:
                                startNewUI(MyStoreActivity.class);
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        initDate();
    }

    private void initDate() {
        if (bUser.getHead_pic()!=null){
            loadImg(bUser.getHead_pic(),mHeadImgCiv);
        }
        setTextToTv(mMessageTv,mine_MsgCount);
        setTextToTv(mUserLevelTv,"V"+bUser.getLevel());
        setTextToTv(mLevelNumberTv,bUser.getExperience());
        setTextToTv(mUserNameTv,bUser.getNickname());
        setTextToTv(mUnpaidTv,bUser.getWaitPay());
        setTextToTv(mWaitDekiveryTv,bUser.getWaitSend());
        setTextToTv(mReceiveTv,bUser.getWaitReceive());
        setTextToTv(mEvaluationTv,bUser.getWaitCcomment());
        setTextToTv(mReturnsOrSalesTv,bUser.getRefund());
        ////初始化数据
        initSet();
    }

    private void initSet() {
        //初始化数据
//        Log.e("usertype", "initSet: " + bUser.getType());

        switch (userType){
            case 1:
                content_text[content_text.length-1]="申请店铺";
                break;
            case 3:
                content_text[content_text.length-1]="我的店铺";
                break;
            default:
                break;
        }
        List<ItemImage> list=new ArrayList<>();
        for (int i = 0; i <content_img.length ; i++) {
            list.add(new ItemImage(content_img[i],content_text[i]));
        }
        switch (userType){
            case 3:
                list.add(0, new ItemImage(content_img[list.size()-1],content_text[list.size()-1]));
                list.remove(list.get(list.size()-1));
                break;
            default:
                break;
        }
        MineAdapter adapter=new MineAdapter(getContext(),list);
        listView.setAdapter(adapter);
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
                    // TODO: 2017/5/16 店铺申请中  str = "申请审核中"
                    String str = data.getStringExtra("applying");
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
                Log.e("null", "getView: "+viewHolder.imageView);
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
