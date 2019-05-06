package com.example.gankgirl.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gankgirl.R;
import com.example.gankgirl.bean.GirlsBean.ResultsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecyclerAdapter extends BaseQuickAdapter<ResultsBean, BaseViewHolder> {

    public MyRecyclerAdapter(int layoutResId, @Nullable List<ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ResultsBean item) {
        Picasso.get()
                .load(item.getUrl())
                .into((ImageView) helper.getView(R.id.iv_top_image));
    }

//    public MyRecyclerAdapter(Context context, List<ResultsBean> resultsBeans) {
//        this.context = context;
//        this.resultsBeans = resultsBeans;
//    }
//
//    @Override
//    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = View.inflate(context, R.layout.layout_top, null);
//        return new PhotoViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(PhotoViewHolder holder, int position) {
//        Picasso.get()
//                .load(resultsBeans.get(position).getUrl())
//                .into(holder.mImageView);
//    }
//
//    @Override
//    public int getItemCount() {
//        return resultsBeans.size();
//    }
//
//    public class PhotoViewHolder extends RecyclerView.ViewHolder {
//        private ImageView mImageView;
//
//        public PhotoViewHolder(View itemView) {
//            super(itemView);
//            mImageView = itemView.findViewById(R.id.iv_top_image);
//        }
//    }

}
