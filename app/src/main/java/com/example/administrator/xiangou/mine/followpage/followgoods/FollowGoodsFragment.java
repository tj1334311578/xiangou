package com.example.administrator.xiangou.mine.followpage.followgoods;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mine.followpage.FollowPageActivity;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

public class FollowGoodsFragment extends MVPBaseFragment<FollowGoodsContract.View, FollowGoodsPresenter>
        implements FollowGoodsContract.View, FollowPageActivity.CallEditGoodsFoolow {
    private List<FollowGoodsBean.DataBean> mGoodsBeanList;
    private ListView listView;
    private ImageView mNodataBgIv;
    private FollowGoodsAdapter mAdapter;
    private String goodsIds;
    private List<Integer> mSelectGoodsList;

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

//    public interface CallGoodsFollowChanged{
//        void callBackGoodsChaged(List<Integer> goodsIds);
////        void notifyEditGoodsFoolow(boolean toEdit);
//    }
//    public CallGoodsFollowChanged mCallGoodsFollowChanged;
//    public void notifyEditGoodsFoolow(boolean toEdit){
//
//    }

//    @Override
//    public void onAttach(Context context) {
//        mCallGoodsFollowChanged = (CallMineFragmentUnLoad) context;
//        super.onAttach(context);
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGoodsBeanList = new ArrayList<>();
        mSelectGoodsList = new ArrayList<>();
        mPresenter.getCollectGoodsList(1/*getUser().getUser_id()*/,0,null);
//        Bundle bundle = getArguments();
//        if (bundle!=null) {
//            FollowGoodsBean goodsBean = (FollowGoodsBean) bundle.getSerializable("goods_data");
//            mGoodsBeanList = goodsBean.getData();
//        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container, R.layout.listview_style);
    }

    @Override
    public void sendFialRequest(String message) {
        showBgImg();
        showToast(message);
    }
    private void showBgImg(){
        if (mGoodsBeanList == null && mNodataBgIv !=null){
            mNodataBgIv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initView() {
        listView=findContentView(R.id.listview_style_list,false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        mNodataBgIv = findContentView(R.id.nogoods_bgimg_iv,false);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * get notify of activity
     * @param toEdit it's activity's data what notify fragment show or hide the checkbox
     */
    @Override
    public void notifyEditGoodsFoolow(boolean toEdit) {
        mAdapter.setToEdit(toEdit);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyRemoveGoods() {
        for (int i:mSelectGoodsList) {
            mGoodsBeanList.remove(i);
        }
//        mAdapter.notifyDataSetChanged();
        updataAdapter();
    }

    private void updataAdapter(){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void getGoodsListSuccess(FollowGoodsBean data) {
        mGoodsBeanList = data.getData();
        if (mGoodsBeanList!=null && mNodataBgIv.getVisibility()!=View.GONE){
            mNodataBgIv.setVisibility(View.GONE);
        }
        mAdapter = new FollowGoodsAdapter(getContext(), mGoodsBeanList);
        mAdapter.setDealGoodsFollowChanged(new FollowGoodsAdapter.DealGoodsFollowChanged() {
            @Override
            public void dealBackGoodsChaged(List<Integer> goodsIds) {
                mSelectGoodsList = goodsIds;
                StringBuffer goodsId = new StringBuffer();
                goodsId.append(mGoodsBeanList.get(goodsIds.get(0)).getGoods_id());
                for (int i = 1; i < goodsIds.size(); i++){
                    int id = mGoodsBeanList.get(goodsIds.get(i)).getGoods_id();
                    goodsId.append(","+id);
                }
                Log.e("goodsids", "dealBackGoodsChaged: " + goodsId);
                setGoodsIds(goodsId.toString());
            }
        });
        listView.setAdapter(mAdapter);
    }
}
