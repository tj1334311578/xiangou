package com.example.administrator.xiangou.mine.setting.manageraddress;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.AutoRVAdapter;
import com.example.administrator.xiangou.mine.setting.manageraddress.editaddress.EditAddressActivity;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.DrawableTextView;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ManagerAddressActivity extends MVPBaseActivity<ManagerAddressContract.View, ManagerAddressPresenter> implements ManagerAddressContract.View {
    private RecyclerView mRecyclerView;
    private Button newaddressBtn;
    private ImageView backBtn;
    private TextView TitleTv,SaveTv;
    private DrawableTextView editText,delText;
    private List<AddressBean> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manageraddress);
        initView();
    }

    private void initView() {

        mRecyclerView=findContentView(R.id.manageraddress_recycle);
        newaddressBtn=findContentView(R.id.manageraddress_newaddressBtn,true);
        backBtn= (ImageView) findViewById(R.id.manageraddress_head).findViewById(R.id.setting_head_back);
        TitleTv= (TextView) findViewById(R.id.manageraddress_head).findViewById(R.id.setting_head_center);
        SaveTv= (TextView) findViewById(R.id.manageraddress_head).findViewById(R.id.setting_head_right);
        findContentView(backBtn,true);
        SaveTv.setVisibility(View.GONE);
        TitleTv.setText("管理收货地址");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);//关闭动画。
        list=new ArrayList<>();
        list.add(new AddressBean("张某某","1814652568","四川省成都市成华区驷马桥杨子姗圣地亚1懂2单元"));
        list.add(new AddressBean("张某某","1814652568","四川省成都市成华区驷马桥杨子姗圣地亚1懂2单元"));
        list.add(new AddressBean("张某某","1814652568","四川省成都市成华区驷马桥杨子姗圣地亚1懂2单元"));

        AddressAdapter adapter=new AddressAdapter(this, list);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void onClick(View v) {
        if (v==backBtn){
            finish();
        }else if (v==newaddressBtn){
            // TODO: 2017/4/24 添加新地址 
        }
    }
    public class AddressAdapter extends AutoRVAdapter{
        public List<AddressBean> getList() {
            return list;
        }

        public void setList(List<AddressBean> list) {
            this.list = list;
        }

        private List<AddressBean> list;
        public AddressAdapter(Context context, List<AddressBean> list) {
            super(context, list);
            this.list= list;
        }
        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.manageraddress_item;
        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            Log.e("enterbnd", "onBindViewHolder: " +position);
            CheckBox checkBox;
            holder.setTextView(R.id.manageraddress_username,list.get(position).getName());
            holder.setTextView(R.id.manageraddress_usernumber,list.get(position).getNumber());
            holder.setTextView(R.id.manageraddress_useraddress,list.get(position).getAddress());
            checkBox=holder.getCheckBox(R.id.manageraddress_defaultaddress);
            checkBox.setTag(position);
            checkBox.setChecked(list.get(position).isDefaultAddress());

            editText=holder.getDrawableTextView(R.id.manageraddress_editTv);
            delText=holder.getDrawableTextView(R.id.manageraddress_delTv);
            //编辑监听跳转到编辑页面
            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   startNewUICarryStr(EditAddressActivity.class,"name",list.get(position));
                }
            });
            //删除监听，删除当前item 的数据
            delText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                        for (int i = 0; i <list.size() ; i++) {
//                            list.get(i).setDefaultAddress(false);
//                            if (i != position && holder.getCheckBox(R.id.manageraddress_defaultaddress).getTag(i)!=null){
//                                CheckBox cb = (CheckBox) holder.getCheckBox(R.id.manageraddress_defaultaddress).getTag(i);
//                                cb.setClickable(true);
//                            }
//                        }
//                        list.get(position).setDefaultAddress(true);
//                        ((CheckBox)v).setClickable(false);
//                        notifyItemRangeChanged(0,list.si ze());
                    if (!list.get(position).isDefaultAddress()) {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setDefaultAddress(false);
                        }
                        list.get(position).setDefaultAddress(true);
                    }
                    notifyItemRangeChanged(0,list.size());
                }
            });
        }
    }
}
