package com.example.administrator.xiangou.classification.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.classification.adapter.ClassificationAdapter1;
import com.example.administrator.xiangou.classification.bean.FirstLevelBean;
import com.example.administrator.xiangou.goods_sort.Goods_rankingActivity;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;


public class ClassificationFragment1 extends MVPBaseFragment<ClassificationContract.View,ClassificationPresenter> implements ClassificationContract.View {

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

	@Override
	public void initView() {

		Log.e("index", "initView: "+this.getArguments().getInt("index") );
		recyclerView = (RecyclerView) view.findViewById(R.id.goods_classfication_recycle);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
		mPresenter.callclassificationarray(0);
	}

	@Override
	public void sendFialRequest(String message) {

	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void datatoView(FirstLevelBean data) {
		ClassificationAdapter1 adapter1;
		recyclerView.setAdapter(adapter1=new ClassificationAdapter1(getContext(),data));
		adapter1.setOnitemClickListener(new ClassificationAdapter1.OnitemClickListener() {
			@Override
			public void setrecommendItemClickListener(int position, int cat_id) {
				Log.e("recommemd", "setrecommendItemClickListener: position:"+position+"cat_id:"+cat_id);
//				mPresenter.callClassificationSort(cat_id,0,0,null,null,null,null,null);//x待解析数据输入,请求放到排行页
				startNewUICarryStr(Goods_rankingActivity.class,"cat_id",cat_id);
			}

			@Override
			public void setHot_cateItemClickListener(int position, int cat_id) {
				Log.e("recommemd", "setrecommendItemClickListener: position:"+position+"cat_id:"+cat_id);
//				mPresenter.callClassificationSort(cat_id,0,0,null,null,null,null,null);
				startNewUICarryStr(Goods_rankingActivity.class,"cat_id",cat_id);//x待解析数据输入
			}
		});
	}

}
