package com.example.administrator.xiangou.mine.followpage.followstore;


import android.content.Context;
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

public class FollowStoreFragment extends MVPBaseFragment<FollowStoreContract.View, FollowStorePresenter>
        implements FollowStoreContract.View{
//        , FollowPageActivity.CallEditStoresFoolow {
    private List<FollowStoreBean> lists;
    private ListView listView;

    public interface CallStoresFollowChanged{
        void notifyStoresChaged(int[] goodsIds);
        void notifyEditStoresFoolow(boolean toEdit);
    }
    private CallStoresFollowChanged mCallStoresFollowChanged;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallStoresFollowChanged = (CallStoresFollowChanged) context;
}

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
        lists=new ArrayList<>();
        lists.add(new FollowStoreBean(R.mipmap.cart_store_icon,"喜欢就点辞谢",340));
        lists.add(new FollowStoreBean(R.mipmap.cart_store_icon,"喜欢就点辞谢",310));
        lists.add(new FollowStoreBean(R.mipmap.cart_store_icon,"喜欢就点辞谢",50));
        lists.add(new FollowStoreBean(R.mipmap.cart_store_icon,"喜欢就点辞谢",140));
        listView=findContentView(R.id.listview_style_list,false);
        listView.setAdapter(new FollowStoreAdapter(getContext(),lists));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    @Override
    public void onClick(View v) {

    }
//
//    /**
//     * get notify of activity
//     * @param toEdit it's activity's data what notify fragment show or hide the checkbox
//     */
//    @Override
//    public void notifyEditStoresFoolow(boolean toEdit) {
//        if (toEdit){
//            //show checkbox
//        }else {
//            //hide checkbox
//        }
//    }
}
