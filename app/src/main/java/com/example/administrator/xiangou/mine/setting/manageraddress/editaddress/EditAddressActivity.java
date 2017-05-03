package com.example.administrator.xiangou.mine.setting.manageraddress.editaddress;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mine.setting.manageraddress.AddressBean;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class EditAddressActivity extends MVPBaseActivity<EditAddressContract.View, EditAddressPresenter> implements EditAddressContract.View {
    private ImageView back;
    private TextView TitleTv,SuccessTv;
    private EditText receiveEt,numEt,locationEt,detailedEt;
    private AddressBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editaddress);
        initView();
    }

    private void initView() {
        receiveEt=findContentView(R.id.editaddress_receivename_edit);
        numEt=findContentView(R.id.editaddress_number_edit);
        locationEt=findContentView(R.id.editaddress_locationEt);
        detailedEt=findContentView(R.id.editaddress_detailed);

        back = (ImageView) findContentView(R.id.editaddress_head).findViewById(R.id.setting_head_back);
        TitleTv = (TextView) findContentView(R.id.editaddress_head).findViewById(R.id.setting_head_center);
        SuccessTv = (TextView) findContentView(R.id.editaddress_head).findViewById(R.id.setting_head_right);
        findContentView(back, true);
        if (getIntent().getStringExtra("addresstype")!= null && getIntent().getStringExtra("addresstype").equals("add")){
            Log.e("添加", "initView: "+getIntent().getStringExtra("addresstype"));
            //添加数据地址
            receiveEt.setText("");
            numEt.setText("");
            locationEt.setText("");
            detailedEt.setText("");

        }else if (getIntent().getSerializableExtra("name")!=null){
            Log.e("编辑", "initView: "+getIntent().getStringExtra("name"));
            bean =(AddressBean) getIntent().getSerializableExtra("name");
            receiveEt.setText(bean.getName());
            numEt.setText(bean.getNumber());
            locationEt.setText(bean.getAddress().substring(0, bean.getAddress().indexOf("省") + 1) + "    "
                    + bean.getAddress().substring(bean.getAddress().indexOf("省") + 1, bean.getAddress().indexOf("市") + 1) + "    "
                    + bean.getAddress().substring(bean.getAddress().indexOf("市") + 1, bean.getAddress().indexOf("区") + 1)
            );
            detailedEt.setText(bean.getAddress());
            TitleTv.setText("编辑收货地址");
            SuccessTv.setText("完成");
            SuccessTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        }
        findContentView(SuccessTv,true);
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void onClick(View v) {
        if (v==back){
            finish();
        }else if (v==SuccessTv){
            // TODO: 2017/4/25 请求修改信息
            if (getIntent().getStringExtra("addresstype")!=null && !getIntent().getStringExtra("addresstype").equals("add")){
                Log.e("addresstype", "onClick: " +getIntent().getStringExtra("addresstype"));
                this.setResult(200,new Intent().putExtra("data",bean));
            }else{
                if (!receiveEt.getText().toString().equals("") && !numEt.getText().toString().equals("") && locationEt.getText()!=null && !detailedEt.getText().toString().equals(""))
                     {
                    Intent intent =new Intent();
                    AddressBean bean=new AddressBean(receiveEt.getText().toString(),numEt.getText().toString(),detailedEt.getText().toString());
                    intent.putExtra("data",bean);
                    this.setResult(200,intent);
                    Log.e("onClick", "onClick: " +getIntent().getStringExtra("addresstype"));
                    finish();
                         }else{
                showToast("信息不全，无法保存！");
            }
            }
        }
//        super.onClick(v);
    }
}
