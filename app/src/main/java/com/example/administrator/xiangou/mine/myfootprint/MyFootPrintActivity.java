package com.example.administrator.xiangou.mine.myfootprint;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyFootPrintActivity extends MVPBaseActivity<MyFootPrintContract.View, MyFootPrintPresenter> implements MyFootPrintContract.View {
    private ImageView back;
    private ListView listView;
    private List<FootPrintBean>  lists;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.footprintpage);
        initView();
    }

    private void initView() {
        lists=new ArrayList<>();
        back=findContentView(R.id.footprintpage_back);
        listView=findContentView(R.id.footprintpage_listview,false);
        lists.add(new FootPrintBean(R.mipmap.nodata_bgimg,"积分是奇偶额飞机哦if就饿哦我就佛额佛节佛搜额积分法喔",100,150));
        lists.add(new FootPrintBean(R.mipmap.nodata_bgimg,"积分是奇偶额飞机哦if就饿哦我就佛额佛节佛搜额积分法喔",150,180));
        lists.add(new FootPrintBean(R.mipmap.nodata_bgimg,"积分是奇偶额飞机哦if就饿哦我就佛额佛节佛搜额积分法喔",110,150));
        lists.add(new FootPrintBean(R.mipmap.nodata_bgimg,"积分是奇偶额飞机哦if就饿哦我就佛额佛节佛搜额积分法喔",100,140));
        listView.setAdapter(new MyFootPrintAdapter(this,lists));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.footprintpage_back:
                finish();
        }
    }

    @Override
    public void sendFialRequest(String message) {

    }
}
