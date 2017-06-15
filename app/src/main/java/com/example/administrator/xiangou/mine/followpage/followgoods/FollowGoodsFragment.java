package com.example.administrator.xiangou.mine.followpage.followgoods;


import android.app.Activity;
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

public class FollowGoodsFragment extends MVPBaseFragment<FollowGoodsContract.View, FollowGoodsPresenter>
        implements FollowGoodsContract.View{
//        , FollowPageActivity.CallEditGoodsFoolow {
    private List<FollowGoodsBean> lists;
    private ListView listView;

    public interface CallGoodsFollowChanged{
        void notifyGoodsChaged(int[] goodsIds);
        void notifyEditGoodsFoolow(boolean toEdit);
    }
    public CallGoodsFollowChanged mCallGoodsFollowChanged;
    public void setCallGoodsFollowChanged(CallGoodsFollowChanged mCallGoodsFollowChanged){
        this.mCallGoodsFollowChanged = mCallGoodsFollowChanged;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallGoodsFollowChanged = (CallGoodsFollowChanged) activity;
    }

//    @Override
//    public void onAttach(Context context) {
//        mCallGoodsFollowChanged = (CallGoodsFollowChanged) context;
//        super.onAttach(context);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container, R.layout.listview_style);
    }

    @Override
    public void sendFialRequest(String message) {
        showToast(message);
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
        listView.setAdapter(new FollowGoodsAdapter(getContext(),lists));

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
//    public void notifyEditGoodsFoolow(boolean toEdit) {
//        if (toEdit){
//            //show checkbox
//        }else {
//            //hide checkbox
//        }
//    }
}
