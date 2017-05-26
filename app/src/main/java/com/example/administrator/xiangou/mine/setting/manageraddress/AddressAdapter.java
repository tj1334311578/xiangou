package com.example.administrator.xiangou.mine.setting.manageraddress;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.mine.setting.manageraddress.model.UserAddressBean;
import com.example.administrator.xiangou.tool.DrawableTextView;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 地址适配器
 * @email 18482195579@163.com
 * @Date 2017-05-22 18:39
 */
public class AddressAdapter extends RVBaseAdapter<UserAddressBean.DataBean> {

    private DrawableTextView editText,delText;
    private CheckBox mCheckBox;

    public AddressAdapter(Context context, List<UserAddressBean.DataBean> mDatas) {
        super(context, mDatas);
        setLayoutResId(R.layout.manageraddress_item);
    }

    public interface AddressManagerListener{
//        void dealCheckBox(CompoundButton buttonView, boolean isChecked, int position);
        void dealCheckBox(CompoundButton buttonView, boolean isChecked, int position);
        void dealEditTextTv(View v, int position);
        void dealDelTextTv(View v, int position);
    }
    public AddressManagerListener mAddressManagerListener;
    public void setAddressManagerListener(AddressManagerListener mAddressManagerListener){
        this.mAddressManagerListener = mAddressManagerListener;
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, final UserAddressBean.DataBean dataBean, final int position) {

        Log.e("enterbnd", "onBindViewHolder: " +position+" =="+dataBean.toString());
        holder.getTextView(R.id.manageraddress_username).setText(dataBean.getConsignee());
        holder.getTextView(R.id.manageraddress_usernumber).setText(dataBean.getMobile());
        holder.getTextView(R.id.manageraddress_useraddress).setText(dataBean.getAddress());
        mCheckBox = holder.getCheckBox(R.id.manageraddress_defaultaddress);
        mCheckBox.setOnCheckedChangeListener(null);
//        if (holder.getCheckBox(R.id.manageraddress_defaultaddress).getTag()!=dataBean) {
//            mCheckBox.setTag(dataBean);
//        }
        if (dataBean.getIs_default()==1) {
            mCheckBox.setChecked(true);
            mCheckBox.setClickable(false);
        }else {
            mCheckBox.setChecked(false);
            mCheckBox.setClickable(true);
        }
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("adaper", "onCheckedChanged: " + position + " :" +isChecked );
                mAddressManagerListener.dealCheckBox(buttonView,isChecked,position);
            }
        });

        editText= (DrawableTextView) holder.getView(R.id.manageraddress_editTv);
        delText= (DrawableTextView) holder.getView(R.id.manageraddress_delTv);
        //编辑监听跳转到编辑页面
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddressManagerListener.dealEditTextTv(v,position);
            }
        });
        //删除监听，删除当前item 的数据
        delText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mDatas.remove(position);
//                notifyDataSetChanged();
                mAddressManagerListener.dealDelTextTv(v,position);
            }
        });
    }
}
