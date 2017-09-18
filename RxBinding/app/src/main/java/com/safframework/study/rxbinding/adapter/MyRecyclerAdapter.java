package com.safframework.study.rxbinding.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.safframework.study.rxbinding.R;

import java.util.List;

/**
 * Created by tony on 2017/9/18.
 */

public class MyRecyclerAdapter  extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public MyRecyclerAdapter(Context context, List<String> datas){
        this. mContext=context;
        this. mDatas=datas;
        inflater=LayoutInflater. from(mContext);
    }

    @Override
    public int getItemCount() {

        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tv.setText( mDatas.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_my_recyclerview,parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv;

        public ViewHolder(View view) {
            super(view);
            tv=(TextView) view.findViewById(R.id.textview);
        }

    }
}
