package com.example.administrator.xiangou.classification.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.classification.adapter.ClassificationAdapter;
import com.example.administrator.xiangou.classification.bean.FirstLevelBean;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;

public class ClassificationFragment extends MVPBaseFragment<ClassificationContract.View,ClassificationPresenter> implements ClassificationContract.View {

	private RecyclerView recyclerView;
	private int cat_id;

	public ClassificationFragment(int cat_id) {
		super();
		this.cat_id=cat_id;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return setContextView(inflater,container,R.layout.goods_classfication_recycle);
	}

	@Override
	public void initView() {
		Log.e("index", "initView: "+this.getArguments().getInt("index")+"position:"+cat_id);
//		List<String> list=new ArrayList<>();
//		list.add("jefi");
		recyclerView = (RecyclerView) mContextView.findViewById(R.id.goods_classfication_recycle);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
		mPresenter.callclassificationarray(cat_id);
	}


	@Override
	public void sendFialRequest(String message) {

	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void datatoView(FirstLevelBean data) {
		recyclerView.setAdapter(new ClassificationAdapter(getContext(),data));
	}
}
