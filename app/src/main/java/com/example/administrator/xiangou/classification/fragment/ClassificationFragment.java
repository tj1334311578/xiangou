package com.example.administrator.xiangou.classification.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.classification.adapter.ClassificationAdapter;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

public class ClassificationFragment extends MVPBaseFragment<ClassificationContract.View,ClassificationPresenter> implements ClassificationContract.View {

	private RecyclerView recyclerView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return setContextView(inflater,container,R.layout.goods_classfication_recycle);
	}

	@Override
	public void initView() {
		List<String> list=new ArrayList<>();
		list.add("jefi");
		recyclerView = (RecyclerView) mContextView.findViewById(R.id.goods_classfication_recycle);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
		recyclerView.setAdapter(new ClassificationAdapter(getContext(),list));
	}


	@Override
	public void sendFialRequest(String message) {

	}

	@Override
	public void onClick(View v) {

	}
}
