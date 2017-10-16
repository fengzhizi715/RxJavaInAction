package com.safframework.study.imageloader.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.safframework.study.imageloader.R;
import com.safframework.study.imageloader.app.App;
import com.safframework.study.imageloader.domain.Picture;

import java.util.List;

/**
 * Created by tony on 2017/10/16.
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private List<Picture> mList;

    public PictureAdapter(List<Picture> data) {
        this.mList = data;
    }

    @Override
    public PictureAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PictureAdapter.ViewHolder(parent, R.layout.cell_gridview);
    }

    @Override
    public void onBindViewHolder(final PictureAdapter.ViewHolder holder, int position) {

        Picture picture = mList.get(position);

        holder.text.setText(picture.title);

        App.getInstance().imageLoader.displayImage(picture.url,holder.image);
    }

    @Override
    public int getItemCount() {
        return mList!=null?mList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        private TextView text;

        public ViewHolder(ViewGroup parent, @LayoutRes int resId) {
            super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));

            image = (ImageView)itemView.findViewById(R.id.image);

            text = (TextView)itemView.findViewById(R.id.text);
        }
    }
}
