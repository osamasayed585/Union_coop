package com.visionstech.demoapp.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.visionstech.demoapp.R;
import com.visionstech.demoapp.core.network.beans.NY;
import com.visionstech.demoapp.databinding.RowItemNyBinding;

import java.util.List;

import javax.inject.Inject;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyAViewHolder> {


    private List<NY> mData;
    private NyAdapterOnClickHandler mClickHandler;
    private Context context;

    @Inject
    public MainAdapter() {
    }

    public void setItemClickListener(NyAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public void setData(List<NY> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_ny, parent, false);
        return new MyAViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAViewHolder holder, int position) {
        NY currently = mData.get(position);
        holder.bind(currently, position);
    }

    @Override
    public int getItemCount() {
        if (null == mData) return 0;
        return mData.size();
    }


    public class MyAViewHolder extends RecyclerView.ViewHolder {

        public static final int DEFAULT_POSITION = 0;
        RowItemNyBinding mBinding;

        public MyAViewHolder(@NonNull View itemView) {
            super(itemView);

            mBinding = RowItemNyBinding.bind(itemView);
            itemView.setOnClickListener(this::OnItemClick);
        }

        private void OnItemClick(View view) {
            int currentPosition = getAdapterPosition();
            if (mClickHandler != null) {
                mClickHandler.onSizeItemClick(mData.get(currentPosition));
            }
        }


        public void bind(NY currently, int position) {
            mBinding.txvTitle.setText(currently.getTitle());
            mBinding.txvByline.setText(currently.getByline());
            mBinding.txvPublishedDate.setText(currently.getPublishedDate());

            Glide.with(context)
                    .load(currently.getMedia().get(DEFAULT_POSITION).getMediaMetadata().get(DEFAULT_POSITION).getUrl())
                    .placeholder(R.drawable.place_holder).
                    error(R.drawable.place_holder)
                    .into(mBinding.imgItem);

        }
    }

    public interface NyAdapterOnClickHandler {
        void onSizeItemClick(NY ny);
    }

}