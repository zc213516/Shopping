package com.example.shopping.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.example.shopping.R;
import com.example.shopping.adapter.Rv2HomeGridLayoutAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private ArrayList<Integer> integers;
    private MyRcvAdapter myRcvAdapter;
    private RecyclerView rv1;
    private RecyclerView rv2;
    private DelegateAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        integers.add(R.drawable.m1);
        integers.add(R.drawable.m2);
        integers.add(R.drawable.m3);
        myRcvAdapter.notifyDataSetChanged();
    }

    private void initView(View view) {
        rv1 = view.findViewById(R.id.rv1);
        rv2 = view.findViewById(R.id.rv2);
        rv1.setLayoutManager(new LinearLayoutManager(getContext()));
        integers = new ArrayList<>();
        myRcvAdapter = new MyRcvAdapter(integers,getContext());
        rv1.setAdapter(myRcvAdapter);


        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getActivity());
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
        rv2.setRecycledViewPool(pool);
        pool.setMaxRecycledViews(0, 10);

        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        gridLayoutHelper.setItemCount(6);
        // 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setPadding(20, 20, 20, 20);
        // 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        gridLayoutHelper.setMargin(20, 20, 20, 20);
        // 设置背景颜色
        gridLayoutHelper.setBgColor(Color.GRAY);
        // 设置设置布局内每行布局的宽与高的比
        gridLayoutHelper.setAspectRatio(6);
        // gridLayoutHelper特有属性
        //设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setWeights(new float[]{20, 20, 20, 20, 20});
        // 控制子元素之间的垂直间距
        gridLayoutHelper.setVGap(20);
        // 控制子元素之间的水平间距
        gridLayoutHelper.setHGap(20);
        //是否自动填充空白区域
        gridLayoutHelper.setAutoExpand(false);
        // 设置每行多少个网格
        gridLayoutHelper.setSpanCount(2);

        //创建适配器并设置
        Rv2HomeGridLayoutAdapter rv2HomeGridLayoutAdapter = new Rv2HomeGridLayoutAdapter(gridLayoutHelper, getActivity());


        adapter = new DelegateAdapter(virtualLayoutManager, true);
        adapter.addAdapter(rv2HomeGridLayoutAdapter);
        rv2.setLayoutManager(virtualLayoutManager);
        rv2.setAdapter(adapter);
    }
}