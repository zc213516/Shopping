package com.example.shopping.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.bean.ShopBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

public class SingleAdapter extends DelegateAdapter.Adapter  {
    private SingleLayoutHelper singleLayoutHelper;

    public SingleAdapter(SingleLayoutHelper singleLayoutHelper) {
        this.singleLayoutHelper = singleLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return singleLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_two, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }
    class MyviewHolder extends RecyclerView.ViewHolder {
        private EditText tv;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.ed_tv);
        }
    }
}
