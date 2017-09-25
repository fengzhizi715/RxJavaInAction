package com.safframework.study.rxbinding.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.safframework.study.rxbinding.R;
import com.safframework.tony.common.utils.Preconditions;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by tony on 2017/9/18.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public MyRecyclerAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {

        return Preconditions.isNotBlank(mDatas) ? mDatas.size() : 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String item = mDatas.get(position);

        if (Preconditions.isNotBlank(item)) {

            holder.text.setText(item);

            RxView.clicks(holder.itemView)
                    .subscribe(new Consumer<Object>() {

                        @Override
                        public void accept(Object o) throws Exception {

                            Toast.makeText(mContext,"点击"+item,Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_my_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;

        public ViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.textview);
        }

    }
}
