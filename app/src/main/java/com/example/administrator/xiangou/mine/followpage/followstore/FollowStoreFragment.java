package com.example.administrator.xiangou.mine.followpage.followstore;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.goods_sort.storehome.StoreHomeActivity;
import com.example.administrator.xiangou.mine.followpage.FollowPageActivity;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

public class FollowStoreFragment extends MVPBaseFragment<FollowStoreContract.View, FollowStorePresenter>
        implements FollowStoreContract.View, FollowPageActivity.CallEditStoresFoolow {
    private List<FollowStoreBean.DataBean> mBeanList;
    private ListView listView;
    private ImageView mNodataBgIv;
    private FollowStoreAdapter mAdapter;
    private List<Integer> mSelectStoresList;
    private String storeIds;
    private int page_no;
    private boolean hasData;

    public boolean isHasData() {
        return hasData;
    }

    public void setHasData(boolean hasData) {
        this.hasData = hasData;
    }

    public String getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(String storeIds) {
        this.storeIds = storeIds;
    }
    //    public interface CallStoresFollowChanged{
//        void callBackStoresChaged(int[] goodsIds);
//        void notifyEditStoresFoolow(boolean toEdit);
//    }
//    private CallStoresFollowChanged mCallStoresFollowChanged;
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        mCallStoresFollowChanged = (CallStoresFollowChanged) context;
//}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeanList =new ArrayList<>();
        mSelectStoresList =new ArrayList<>();
        mPresenter.getCollectStoresListApi(getUser().getUser_id(),page_no);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container, R.layout.listview_style);
    }

    @Override
    public void sendFialRequest(String message) {
        showToast(message);
        showBgImg();
    }

    private void showBgImg(){
        if (mBeanList == null && mNodataBgIv !=null){
            mNodataBgIv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initView() {
        listView=findContentView(R.id.listview_style_list,false);
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
    public void notifyEditStoresFoolow(boolean toEdit) {
        mAdapter.setToEdit(toEdit);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyRemoveStore() {
        for (int i: mSelectStoresList) {
            mBeanList.remove(i);
        }
        if (mBeanList.size()==0){
            setHasData(false);
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
    public void getStoresListSuccess(final List<FollowStoreBean.DataBean> data) {
        mBeanList = data;
        setHasData(true);
        if (mNodataBgIv.getVisibility()!=View.GONE){
            mNodataBgIv.setVisibility(View.GONE);
        }
        mAdapter = new FollowStoreAdapter(getContext(),mBeanList);
        mAdapter.setDealSotresFollowChanged(new FollowStoreAdapter.DealSotresFollowChanged() {
            @Override
            public void dealBackStoresChaged(List<Integer> storesIds) {
                mSelectStoresList = storesIds;//被选择的店铺在list里的position的记录list
                StringBuffer storesId = new StringBuffer();
                storesId.append( mBeanList.get(storesIds.get(0)).getDid() );
                for (int i = 1; i < storesIds.size(); i++){
//                    int id = storesIds.get(i);
                    int id = mBeanList.get(storesIds.get(i)).getDid();
                    storesId.append(","+id);
                }
                setStoreIds(storesId.toString());
            }

            @Override
            public void dealClickEnterStore(int did) {
                startNewUICarryStr(StoreHomeActivity.class,"store_id",did);
            }
        });
        listView.setAdapter(mAdapter);
    }
}
