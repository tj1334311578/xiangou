package com.example.administrator.xiangou.mine.mystore.datamanager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DataManagerActivity extends MVPBaseActivity<DataManagerContract.View, DataManagerPresenter> implements DataManagerContract.View {
    private ImageView backBtn;
    private Spinner spinner;
    private RecyclerView recyclerView;
    private TextView TitleTv;
    private int oldposition;
    private boolean isFirst=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_center_datamanager);
        initView();
    }

    private void initView() {
        backBtn=findContentView(R.id.store_headback);
        TitleTv=findContentView(R.id.store_headTitleTv);
        TitleTv.setText("数据统计");
        spinner=findContentView(R.id.seller_center_datamanager_spinner,false);
        recyclerView=findContentView(R.id.seller_center_datamanager_recycle,false);
        mPresenter.requestDataInfo(1,null);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.store_headback:
                finish();
                break;
        }
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void dataToView(TotalDataBean dataInfo) {
         spinnerView(dataInfo);//设置spinner数据并显示在View上

    }
    //网络数据设置到recycle上
    private void dataToRecycleView(TotalDataBean dataInfo) {
        recyclerView.setLayoutManager(new GridLayoutManager(this,4, LinearLayoutManager.VERTICAL,false));
        String [] conditions=new String[]{"月份","交易量/件","交易金额/元","访客量/人"};
        /**
         * 初始化请求的数据
         */
        List<String> datas=new ArrayList<>();
        for (int i = 0; i <conditions.length ; i++) {
            datas.add(conditions[i]);
        }
        for (int i = 0; i <(dataInfo.getData().getRecord().size()) ; i++) {
                    datas.add(dataInfo.getData().getRecord().get(i).getMonth());
                    datas.add(dataInfo.getData().getRecord().get(i).getMonth_order()+"");
                    datas.add(dataInfo.getData().getRecord().get(i).getMonth_amount()+"");
                    datas.add(dataInfo.getData().getRecord().get(i).getBrowser()+"");
                             }
        /**
         * 初始化请求的数据
         */
        Log.e("data", "dataToRecycleView: "+datas.toString() );
        recyclerView.setAdapter(new DataManagerAdapter(this,datas));
    }
    //网络数据设置到spinner上
    private void spinnerView(TotalDataBean dataInfo) {
        if (isFirst) {
            isFirst =false;
            final List<Integer>spinnerlist = new ArrayList<>();
            for (int i = 0; i < dataInfo.getData().getYear().size(); i++) {
                spinnerlist.add(dataInfo.getData().getYear().get(i));
            }

            ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerlist);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setSelection(spinnerlist.size()-1);
            adapter.notifyDataSetChanged();
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if (oldposition!=position) {
                        oldposition=position;
                        mPresenter.requestDataInfo(1, spinnerlist.get(position).toString());
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        dataToRecycleView(dataInfo);
    }
}
