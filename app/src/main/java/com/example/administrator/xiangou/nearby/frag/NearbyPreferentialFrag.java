package com.example.administrator.xiangou.nearby.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xiangou.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhouzongyao on 2017/3/9.
 */

public class NearbyPreferentialFrag extends Fragment {
    private RecyclerView recyclerView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nearby_preferential,container,false);
        initView();
        return view;
    }

    private void initView() {
        List<String> list=new ArrayList<>();
        list.add("jefi");
        list.add("ijfe");
        list.add("fbj");
        Log.e("tag", "initView: "+list.size() );
        recyclerView= (RecyclerView) view.findViewById(R.id.nearby_recycler_preferential);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new NearbyPreferentialAdapter(getContext(),list));
    }
}
