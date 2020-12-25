package com.example.shopping.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.example.shopping.R;
import com.example.shopping.adapter.BannerAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView rv1;
    private Banner banner;
    private ArrayList<Integer> integers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv1 = view.findViewById(R.id.rv1);
        rv1.setLayoutManager(new LinearLayoutManager(getActivity()));
//        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getActivity());
//        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
//        rv1.setRecycledViewPool(pool);
//        pool.setMaxRecycledViews(0,10);

        integers = new ArrayList<>();
        integers.add(R.drawable.m1);
        integers.add(R.drawable.m2);
        integers.add(R.drawable.m3);

        BannerAdapter bannerAdapter = new BannerAdapter(integers, getActivity());
        rv1.setAdapter(bannerAdapter);
    }
}