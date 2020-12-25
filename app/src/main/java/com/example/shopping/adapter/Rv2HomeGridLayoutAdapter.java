package com.example.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.bean.ShopBean;

import java.util.ArrayList;

public class Rv2HomeGridLayoutAdapter extends DelegateAdapter.Adapter {
    private GridLayoutHelper gridLayoutHelper;
    private Context context;
    private ArrayList<ShopBean.DataDTO.BrandListDTO> listDTOS;



    public Rv2HomeGridLayoutAdapter(GridLayoutHelper gridLayoutHelper, Context context, ArrayList<ShopBean.DataDTO.BrandListDTO> listDTOS) {
        this.gridLayoutHelper = gridLayoutHelper;
        this.context = context;
        this.listDTOS = listDTOS;
    }

    public Rv2HomeGridLayoutAdapter(GridLayoutHelper gridLayoutHelper, Context context) {
        this.gridLayoutHelper = gridLayoutHelper;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_rv2, parent, false);
            return new FirstHolder(view);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.layout_rv2other, parent, false);
        return new OtherHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        ShopBean.DataDTO.BrandListDTO brandListDTO = listDTOS.get(position);
        if(itemViewType==1){
        }
        if (itemViewType==2){
            OtherHolder otherHolder= (OtherHolder) holder;
            otherHolder.tvRv2title.setText(listDTOS.get(position).getClass().getName());
            Glide.with(context).load(listDTOS.get(position).getClass().getClassLoader()).into(otherHolder.ivRv2Image);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        }
        return 2;
    }

    @Override
    public int getItemCount() {
        return listDTOS.size();
    }

    class FirstHolder extends RecyclerView.ViewHolder {
        private TextView tvRv2;

        public FirstHolder(@NonNull View itemView) {
            super(itemView);
            tvRv2 = (TextView) itemView.findViewById(R.id.tv_rv2);
        }
    }

    class OtherHolder extends RecyclerView.ViewHolder {
        private ImageView ivRv2Image;
        private TextView tvRv2title;
        public OtherHolder(@NonNull View itemView) {
            super(itemView);
            ivRv2Image = (ImageView) itemView.findViewById(R.id.iv_rv2_image);
            tvRv2title = (TextView) itemView.findViewById(R.id.tv_rv2title);
        }
    }
}
