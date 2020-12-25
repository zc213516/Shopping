package com.example.shopping.fragment;

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

public class MyRcvAdapter extends RecyclerView.Adapter {
    private ArrayList<Integer> list;
    private Context context;
    private Banner ban;

    public MyRcvAdapter(ArrayList<Integer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_banner, parent, false);
        return new MyHoler1(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHoler1 myHoler1= (MyHoler1) holder;
        myHoler1.ban.setImages(list).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHoler1 extends RecyclerView.ViewHolder {
        private final Banner ban;
        public MyHoler1(@NonNull View itemView) {
            super(itemView);
            ban = itemView.findViewById(R.id.banner);
        }
    }
}
