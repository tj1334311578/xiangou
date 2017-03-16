package com.example.administrator.xiangou.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzongyao on 2017/2/28.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private ListView listView;
    private View view;
    private int content_img[]={R.mipmap.personal_wallet_icon,R.mipmap.personal_footprint_icon,
            R.mipmap.personal_comment_icon,R.mipmap.personal_member_icon,R.mipmap.personal_more_icon};
    private String content_text[]={"我的钱包","我的足迹","我的评论","会员中心","更多",};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine,container,false);
        initSet();
        return view;
    }

    private void initSet() {
        initView();
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

        //ListView点击事件设置
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAG", "onItemClick: "+""+"被点击了");
                TextView tv = (TextView) listView.getChildAt(position).findViewById(R.id.mine_item_text);
                //等价于=>((TextView)(listView.getChildAt(position).findViewById(R.id.mine_item_text)))
                Toast.makeText(getContext(),tv.getText() +"被点击了", Toast.LENGTH_SHORT).show();
            }
        });

        //初始化数据
        List<ItemImage> list=new ArrayList<>();
        for (int i = 0; i <content_img.length ; i++) {
            list.add(new ItemImage(content_img[i],content_text[i]));
        }
        MineAdapter adapter=new MineAdapter(getContext(),list);
        listView.setAdapter(adapter);
    }

    private void initView() {
        listView= (ListView) view.findViewById(R.id.mine_list);
        view.findViewById(R.id.mine_setup).setOnClickListener(this);
        view.findViewById(R.id.mine_message).setOnClickListener(this);
        view.findViewById(R.id.mine_circle_imageview).setOnClickListener(this);
        view.findViewById(R.id.Level_img).setOnClickListener(this);
        view.findViewById(R.id.Level_number).setOnClickListener(this);
        view.findViewById(R.id.username).setOnClickListener(this);
        view.findViewById(R.id.mine_attention).setOnClickListener(this);
        view.findViewById(R.id.mine_Coupon).setOnClickListener(this);
        view.findViewById(R.id.mine_sign_in).setOnClickListener(this);
        view.findViewById(R.id.see_all_orders).setOnClickListener(this);
        view.findViewById(R.id.mine_unpaid).setOnClickListener(this);
        view.findViewById(R.id.mine_wait_delivery).setOnClickListener(this);
        view.findViewById(R.id.mine_receive_goods).setOnClickListener(this);
        view.findViewById(R.id.mine_pending_evaluation).setOnClickListener(this);
        view.findViewById(R.id.mine_returns_sales).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //设置
            case R.id.mine_setup:
                Toast.makeText(getActivity(), "点击设置", Toast.LENGTH_SHORT).show();
                break;
            //消息
            case R.id.mine_message:
                Toast.makeText(getActivity(), "点击消息", Toast.LENGTH_SHORT).show();
                break;
            //用户图标
            case R.id.mine_circle_imageview:
                Toast.makeText(getActivity(), "点击用户图标", Toast.LENGTH_SHORT).show();
                break;
            //等级图标
            case R.id.Level_img:
                Toast.makeText(getActivity(), "点击等级图标", Toast.LENGTH_SHORT).show();
                break;
            //等级经验
            case R.id.Level_number:
                Toast.makeText(getActivity(), "点击等级经验", Toast.LENGTH_SHORT).show();
                break;
            //用户名
            case R.id.username:
                Toast.makeText(getActivity(), "点击用户名", Toast.LENGTH_SHORT).show();
                break;
            //关注
            case R.id.mine_attention:
                Toast.makeText(getActivity(), "点击关注", Toast.LENGTH_SHORT).show();
                break;
            //优惠券
            case R.id.mine_Coupon:
                Toast.makeText(getActivity(), "点击优惠券", Toast.LENGTH_SHORT).show();
                break;
            //签到
            case R.id.mine_sign_in:
                Toast.makeText(getActivity(), "点击签到", Toast.LENGTH_SHORT).show();
                break;
            //查看所有订单
            case R.id.see_all_orders:
                Toast.makeText(getActivity(), "点击查看所有订单", Toast.LENGTH_SHORT).show();
                break;
            //未付款
            case R.id.mine_unpaid:
                Toast.makeText(getActivity(), "点击未付款", Toast.LENGTH_SHORT).show();
                break;
            //等待发货
            case R.id.mine_wait_delivery:
                Toast.makeText(getActivity(), "点击等待发货", Toast.LENGTH_SHORT).show();
                break;
            //待收货
            case R.id.mine_receive_goods:
                Toast.makeText(getActivity(), "点击待收货", Toast.LENGTH_SHORT).show();
                break;
            //待评价
            case R.id.mine_pending_evaluation:
                Toast.makeText(getActivity(), "点击待评价", Toast.LENGTH_SHORT).show();
                break;
            //退货or售后
            case R.id.mine_returns_sales:
                Toast.makeText(getActivity(), "点击退货or售后", Toast.LENGTH_SHORT).show();
                break;
        }

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
                viewHolder= (ViewHolder) convertView.getTag();
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
