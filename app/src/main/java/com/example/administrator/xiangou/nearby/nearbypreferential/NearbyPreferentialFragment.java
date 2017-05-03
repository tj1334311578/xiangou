package com.example.administrator.xiangou.nearby.nearbypreferential;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;
import com.example.administrator.xiangou.nearby.apimodel.NearbyBenifitDataBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NearbyPreferentialFragment extends MVPBaseFragment<NearbyPreferentialContract.View, NearbyPreferentialPresenter> implements NearbyPreferentialContract.View {
    private RecyclerView recyclerView;
    private NearbyPreferentialAdapter mPreferentialAdapter;
    private long mPreTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,R.layout.fragment_nearby_preferential);
    }

    @Override
    public void initView() {
        recyclerView= findContentView(R.id.nearby_recycler_preferential,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        mPreTime = System.currentTimeMillis();
        mPresenter.dealNearbyPreferentialCall(null,null);
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void onClick(View v) {

    }

    long mCountDownTime;
    @Override
    public void sendPreferentialDataToView(final NearbyBenifitDataBean.DataBean data) {
        List<NearbyBenifitDataBean.DataBean> mDataList = new ArrayList<>();
        mDataList.add(data);
        mDataList.add(data);
        mDataList.add(data);
        mPreferentialAdapter = new NearbyPreferentialAdapter(getContext(), mDataList);
        mPreferentialAdapter.setNearbyPrefertialCall(new NearbyPreferentialAdapter.NearbyPreferentialCall() {
            @Override
            public void dealTimeLimitModule(final TextView hTv, final TextView mTv, final TextView sTv) {
//                if (hasDownTieme) {
//                    hasDownTieme = false;
//                }
//                mPresenter.dealTimeLimitModuleCall(hTv, mTv, sTv);

                mCountDownTime = data.getFlash().getEnd_time()-data.getFlash().getCurrent_time()-(System.currentTimeMillis()-mPreTime);
                Log.e("downtiem", "bindHolder2: " + mCountDownTime);
                CountDownTimer timer = new CountDownTimer(mCountDownTime,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (millisUntilFinished>0) {
                            mCountDownTime = millisUntilFinished;
                            Date date = new Date(millisUntilFinished);
                            hTv.setText(setTime(date.getDay()*24+date.getHours()));
                            mTv.setText(setTime(date.getMinutes()));
                            sTv.setText(setTime(date.getSeconds()));
                        }
                    }

                    @Override
                    public void onFinish() {
//                        holder.getTextView(R.id.item_hour_next).setText("此活动已结束！");
                    }
                };
                timer.start();
            }
        });
        Log.e("NearbyBenifitDataBean", "sendPreferentialDataToView: " +data.toString() );
        recyclerView.setAdapter(mPreferentialAdapter);
    }

    private String setTime(int time){
        String str = null;
        if (time<10){
            str = "0"+time;
        }else {
            str = String.valueOf(time%100);
        }
        return str;
    }
}
