package com.example.shopping.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.example.shopping.R;
import com.example.shopping.adapter.SingleFourAdapter;
import com.example.shopping.adapter.SingleThreeAdapter;
import com.example.shopping.adapter.SingleTwoAdapter;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.bean.ShopBean;
import com.example.shopping.contract.HomeContract;
import com.example.shopping.presenter.HomePresenter;

import java.util.ArrayList;


public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.IMainView {


    private RecyclerView rel;
    private ArrayList<ShopBean.DataDTO.BannerDTO> dtos;
    private SingleTwoAdapter singleTwoAdapter;
    private ArrayList<ShopBean.DataDTO.ChannelDTO> channelDTOS;
    private SingleThreeAdapter singleThreeAdapter;
    private SingleAdapter singleAdapter;
    private SingleFourAdapter singleFourAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
    }

    @Override
    protected void initView(View inflate) {
        rel = inflate.findViewById(R.id.rv2);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getContext());
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
        rel.setRecycledViewPool(pool);
        pool.setMaxRecycledViews(0,10);

        //第一行
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        singleLayoutHelper.setItemCount(1);// 设置布局里Item个数
        singleLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        singleLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        singleLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        singleLayoutHelper.setAspectRatio(12);// 设置设置布局内每行布局的宽与高的比
        //第一行适配器
        singleAdapter = new SingleAdapter(singleLayoutHelper);
        //第二行
        //banner图片集合
        dtos = new ArrayList<>();
        SingleLayoutHelper helper = new SingleLayoutHelper();
        singleTwoAdapter = new SingleTwoAdapter(dtos, helper);
        //第三行
        channelDTOS = new ArrayList<>();
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
        gridLayoutHelper.setItemCount(5);// 设置布局里Item个数
        gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //gridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的
        // gridLayoutHelper特有属性（下面会详细说明）
        gridLayoutHelper.setWeights(new float[]{20, 20, 20, 20, 20});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper.setSpanCount(5);// 设置每行多少个网格
        singleThreeAdapter = new SingleThreeAdapter(channelDTOS, getContext(), gridLayoutHelper);


        //第四行

        SingleLayoutHelper singleFourHelper = new SingleLayoutHelper();
        singleFourAdapter = new SingleFourAdapter(singleFourHelper);

        //设置适配器
        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager, false);
        delegateAdapter.addAdapter(singleAdapter);
        delegateAdapter.addAdapter(singleTwoAdapter);
        delegateAdapter.addAdapter(singleThreeAdapter);
        delegateAdapter.addAdapter(singleFourAdapter);
        rel.setLayoutManager(virtualLayoutManager);
        rel.setAdapter(delegateAdapter);
    }

    @Override
    protected void initData() {
        per.getData();
    }

    @Override
    public HomePresenter getPer() {
        return new HomePresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void onSuccess(ShopBean bean) {
        dtos.addAll(bean.getData().getBanner());
        channelDTOS.addAll(bean.getData().getChannel());
        singleTwoAdapter.notifyDataSetChanged();
        singleThreeAdapter.notifyDataSetChanged();
    }
}