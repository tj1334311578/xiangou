package com.example.administrator.xiangou.mine.myorder.myorder.waitdelivery;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.AutoRVAdapter;
import com.example.administrator.xiangou.tool.ContextUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */
public class mWaitDeliveryAdapter extends AutoRVAdapter implements View.OnClickListener {
    private Context mcontext;
    private List<DeliveryBean> lists;
    private TextView storeTv, status, goodsAcount, allprice, freight, seeLogistics, evaluate;
    private int position;
    private RecyclerView mrecycle;
    private ViewClickListener viewClick;

    @Override
    public void onClick(View v) {

    }

    public  interface ViewClickListener{
        void itemviewclick(View view,int position);  }
    public void setOnitemClickListener(final ViewClickListener listener){
        this.viewClick=listener;
    }
    public mWaitDeliveryAdapter(Context context, List<DeliveryBean> lists, int position) {
        super(context, lists);
        this.mcontext = context;
        this.lists = lists;
        this.position = position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected int onCreateViewHolderID(int viewType) {
        return R.layout.myorder_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.e("position", "onBindViewHolder: "+position );
        storeTv = findContextView(holder, R.id.myorder_item_storename);
        status = findContextView(holder, R.id.myorder_item_statu);
        goodsAcount = findContextView(holder, R.id.myorder_item_allacount);
        allprice = findContextView(holder, R.id.myorder_item_allPrice);
        freight = findContextView(holder, R.id.myorder_item_freight);
        seeLogistics = findContextView(holder, R.id.myorder_item_seeLogistics);
        evaluate = findContextView(holder, R.id.myorder_item_evaluate);
        mrecycle = holder.getRecycleView(R.id.myorder_item_recycle);
        switch (this.position) {
            //全部信息
            case 0:
                showall(position);
                break;
            //待付款信息
            case 1:
                waitpaid(position);
                break;
            //待发货
            case 2:
                delivery(position);
                break;
            //待收货
            case 3:
                receive(position);
                break;
            //待评价
            case 4:
                evaluation(position);
                break;
            default:
                break;
        }

        seeLogistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewClick.itemviewclick(v,position);
            }
        });
        evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewClick.itemviewclick(v,position);
            }
        });
        storeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewClick.itemviewclick(v,position);
            }
        });
    }

    private void showall(int position) {
//        lists.get(position).setStatus("全部信息");
        switch (lists.get(position).getStatus()) {
            case "等待付款":
                waitpaid(position);
                break;
            case "买家已付款":
                delivery(position);
                break;
            case "卖家已发货":
                receive(position);
                break;
            case "交易成功":
                evaluation(position);
                break;
        }
    }

    private void waitpaid(int position) {
        lists.get(position).setStatus("等待付款");
        status.setText("等待付款");
//        seeLogistics.setVisibility(View.INVISIBLE);
//        seeLogistics.setFocusable(false);
        seeLogistics.setText("取消订单");
        seeLogistics.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        evaluate.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        evaluate.setTextColor(mcontext.getResources().getColor(R.color.whiteBgColor));
        evaluate.setBackgroundColor(mcontext.getResources().getColor(R.color.color_pink_fb4a89));
        evaluate.setText("付款");
        initLocationView(position);
    }

    //待发货方法
    private void delivery(int position) {
        lists.get(position).setStatus("买家已付款");
        status.setText("买家已付款");
//      seeLogistics.setText("查看物流");
//      evaluate.setText("确定收货");
        seeLogistics.setVisibility(View.GONE);
        evaluate.setVisibility(View.GONE);
        initLocationView(position);
    }

    private void initLocationView(int position) {
        double allprices = 0;
        int acounts = 0;
        final int tempposition=position;
        //合计价格
        for (int i = 0; i < lists.get(position).getList().size(); i++) {
            allprices += lists.get(position).getList().get(i).getPrice() * lists.get(position).getList().get(i).getAcounts();
            acounts += lists.get(position).getList().get(i).getAcounts();
        }
        goodsAcount.setText("共" + acounts + "件商品  总计：");
        allprice.setText("￥" + ContextUtils.S2places(allprices + lists.get(position).getFreight()));
        freight.setText(" （含运费 ￥" + ContextUtils.S2places(lists.get(position).getFreight()) + "）");
        storeTv.setText(lists.get(position).getStoreName());
        mrecycle.setLayoutManager(new LinearLayoutManager(mcontext, LinearLayoutManager.VERTICAL, false));
        EvaluationAdapter adapter = new EvaluationAdapter(mcontext, lists.get(position).getList());
        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mcontext, ""+lists.get(tempposition).getList().get(position).getDescription()+"   "+position, Toast.LENGTH_SHORT).show();
            }
        });
        mrecycle.setAdapter(adapter);
    }

    //待收货方法
    private void receive(int position) {
        lists.get(position).setStatus("卖家已发货");
        status.setText("卖家已发货");
        seeLogistics.setText("查看物流");
        evaluate.setText("确定收货");
        initLocationView(position);

    }

    private <T extends View> T findContextView(ViewHolder holder, int id) {
        return findContextView(holder, id, false);
    }

    private <T extends View> T findContextView(ViewHolder holder, int id, boolean b) {
        View v = holder.getTextView(id);
        if (b)
            v.setOnClickListener(this);
        return (T) v;
    }

    //待评价布局设置方法
    private void evaluation(int position) {
        lists.get(position).setStatus("交易成功");
        status.setText("交易成功");
        seeLogistics.setText("删除订单");
        evaluate.setText("评价");
        initLocationView(position);
    }


    //所有信息监听事件方法
    private void allclick(View v) {

    }


    private static class EvaluationAdapter extends AutoRVAdapter implements View.OnClickListener{
        List<DeliveryItemBean> lists;
        public EvaluationAdapter(Context mcontext, List<DeliveryItemBean> lists) {
            super(mcontext,lists);
            this.lists=lists;
        }
        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.myorder_item_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.getImgeView(R.id.myorder_item_item_img).setImageResource(lists.get(position).getImg());
            holder.setTextView(R.id.myorder_item_item_description,lists.get(position).getDescription());
            holder.setTextView(R.id.myorder_item_item_price,"￥"+ContextUtils.S2places(lists.get(position).getPrice()));
            holder.setTextView(R.id.myorder_item_item_oldprice,"￥"+ ContextUtils.S2places(lists.get(position).getOldprice()));
            holder.getTextView(R.id.myorder_item_item_oldprice).setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
            holder.setTextView(R.id.myorder_item_item_color,"颜色："+lists.get(position).getColor());
            holder.setTextView(R.id.myorder_item_item_size,"尺寸："+lists.get(position).getSize());
            holder.setTextView(R.id.myorder_item_item_acount,"X"+lists.get(position).getAcounts());
        }

        @Override
        public void onClick(View v) {

        }
    }
}
