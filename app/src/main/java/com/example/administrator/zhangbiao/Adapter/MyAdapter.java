package com.example.administrator.zhangbiao.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zhangbiao.Beans.MyBean;
import com.example.administrator.zhangbiao.R;
import com.example.administrator.zhangbiao.ui.OtherActivity;

import java.util.List;

/**
 * Created by 晴天 on 2018/8/19.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private List<MyBean.ResultsBean> mResultsbeansList;

    public MyAdapter(Context mContext, List<MyBean.ResultsBean> mResultsbeansList) {
        this.mContext = mContext;
        this.mResultsbeansList = mResultsbeansList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     MyBean.ResultsBean resultsBean = mResultsbeansList.get(position);
     holder.who.setText(resultsBean.getWho());
     holder.desc.setText(resultsBean.getDesc());
        Glide.with(mContext).load(resultsBean.getUrl()).placeholder(R.drawable.img_default).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mResultsbeansList == null ? 0 : mResultsbeansList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private  ImageView img;
        private  TextView who;
        private  TextView desc;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            who = itemView.findViewById(R.id.who);
            desc = itemView.findViewById(R.id.desc);
        }
    }
}
