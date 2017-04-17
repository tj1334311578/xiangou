package com.example.administrator.xiangou.classification.fragment;

import android.os.Bundle;
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
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
//		View view = inflater.inflate(R.layout.fragment_pro_type, null);
		 view = inflater.inflate(R.layout.goods_classfication_recycle, null);
		initView();
		return view;

	}

	private void initView() {
		List<String> list=new ArrayList<>();
		list.add("jefi");
		recyclerView = (RecyclerView) view.findViewById(R.id.goods_classfication_recycle);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
		recyclerView.setAdapter(new ClassificationAdapter(getContext(),list));
	}

	@Override
	public void sendFialRequest(String message) {

	}
}
