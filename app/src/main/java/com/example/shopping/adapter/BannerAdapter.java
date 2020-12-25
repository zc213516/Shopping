package com.example.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter {
    private ArrayList<Integer> list;
    private Context context;

    public BannerAdapter(ArrayList<Integer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_banner, parent, false);
        return new BannerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BannerHolder bannerHolder= (BannerHolder) holder;
        bannerHolder.banner.setImages(list).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(list).into(imageView);
            }
        });
        bannerHolder.banner.start();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BannerHolder extends RecyclerView.ViewHolder {
        private Banner banner;
        public BannerHolder(@NonNull View itemView) {
            super(itemView);
             banner = itemView.findViewById(R.id.banner);
        }
    }
}
