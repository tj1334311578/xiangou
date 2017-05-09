package com.example.administrator.xiangou.mine.followpage.followgoods;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FollowGoodsFragment extends MVPBaseFragment<FollowGoodsContract.View, FollowGoodsPresenter> implements FollowGoodsContract.View {
    private List<FollowGoodsBean> lists;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container, R.layout.listview_style);
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void initView() {
        listView=findContentView(R.id.listview_style_list,false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        lists=new ArrayList<>();
        lists.add(new FollowGoodsBean(R.mipmap.girl_h,"副书记佛司法欧式佛你佛司法色佛山if师傅送方是否是仿佛色剂ofo减肥舞覅偶我佛方法",110.0,100.00,true));
        lists.add(new FollowGoodsBean(R.mipmap.girl_h,"副书记佛司法欧式佛你佛司法色佛山if师傅送方是否是仿佛色剂ofo减肥舞覅偶我佛方法",110.0,100.00,true));
        lists.add(new FollowGoodsBean(R.mipmap.girl_h,"副书记佛司法欧式佛你佛司法色佛山if师傅送方是否是仿佛色剂ofo减肥舞覅偶我佛方法",110.0,100.00,false));
        lists.add(new FollowGoodsBean(R.mipmap.girl_h,"副书记佛司法欧式佛你佛司法色佛山if师傅送方是否是仿佛色剂ofo减肥舞覅偶我佛方法",110.0,100.00,false));
        listView.setAdapter(new FollowGoodsAdapter(getContext(),lists));

    }

    @Override
    public void onClick(View v) {

    }
}
