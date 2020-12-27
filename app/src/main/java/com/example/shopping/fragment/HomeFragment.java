package com.example.shopping.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.example.shopping.R;
import com.example.shopping.adapter.GridLayoutAdapter;
import com.example.shopping.adapter.GridLayoutSixAdapter;
import com.example.shopping.adapter.HorizontalAdapter;
import com.example.shopping.adapter.SingleFourAdapter;
import com.example.shopping.adapter.SingleSixAdapter;
import com.example.shopping.adapter.SingleThreeAdapter;
import com.example.shopping.adapter.SingleTwoAdapter;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.bean.ShopBean;
import com.example.shopping.contract.HomeContract;
import com.example.shopping.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.IMainView {


    private RecyclerView rel;
    private ArrayList<ShopBean.DataDTO.BannerDTO> dtos;
    private SingleTwoAdapter singleTwoAdapter;
    private ArrayList<ShopBean.DataDTO.ChannelDTO> channelDTOS;
    private SingleThreeAdapter singleThreeAdapter;
    private SingleAdapter singleAdapter;
    private SingleFourAdapter singleFourAdapter;
    private ArrayList<ShopBean.DataDTO.NewGoodsListDTO> newGoodsListDTOS;
    private GridLayoutAdapter gridLayoutAdapter;
    private ArrayList<ShopBean.DataDTO.HotGoodsListDTO> hotGoodsListDTOS;
    private GridLayoutSixAdapter gridLayoutSixAdapter;
    private ArrayList<ShopBean.DataDTO.TopicListDTO> topicListDTOS;
    private HorizontalAdapter horizontalAdapter;
    private ArrayList<ShopBean.DataDTO.CategoryListDTO> goodsListDTOS;
    private TitleAdapter titleAdapter;
    private DelegateAdapter delegateAdapter;
    private FuAdapter fuAdapter;

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

        //第五行
        newGoodsListDTOS = new ArrayList<>();
        GridLayoutHelper FivegridLayoutHelper = new GridLayoutHelper(4);
        FivegridLayoutHelper.setItemCount(4);
        FivegridLayoutHelper.setPadding(20,20,20,20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        FivegridLayoutHelper.setMargin(20,20,20,20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        FivegridLayoutHelper.setAspectRatio(2);// 设置设置布局内每行布局的宽与高的
        // gridLayoutHelper特有属性（下面会详细说明）
        FivegridLayoutHelper.setWeights(new float[]{50,50});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        FivegridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        FivegridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        FivegridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        FivegridLayoutHelper.setSpanCount(2);// 设置每行多少个网格
        gridLayoutAdapter = new GridLayoutAdapter(newGoodsListDTOS,getContext(),FivegridLayoutHelper);


        //人气推荐
        SingleLayoutHelper SixsingleLayoutHelper = new SingleLayoutHelper();
        SingleSixAdapter singleSixAdapter = new SingleSixAdapter(SixsingleLayoutHelper);

        //第六行
        hotGoodsListDTOS = new ArrayList<>();
        GridLayoutHelper SixgridLayoutHelper = new GridLayoutHelper(3);
        SixgridLayoutHelper.setItemCount(3);
        SixgridLayoutHelper.setPadding(20,20,20,20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        SixgridLayoutHelper.setMargin(20,20,20,20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //SixgridLayoutHelper.setAspectRatio(2);// 设置设置布局内每行布局的宽与高的
        // gridLayoutHelper特有属性（下面会详细说明）
        //SixgridLayoutHelper.setWeights(new float[]{50,50});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        SixgridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        SixgridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        SixgridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        SixgridLayoutHelper.setSpanCount(1);// 设置每行多少个网格
        gridLayoutSixAdapter = new GridLayoutSixAdapter(hotGoodsListDTOS, getContext(), SixgridLayoutHelper);

        //第七行
        topicListDTOS = new ArrayList<>();
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        horizontalAdapter = new HorizontalAdapter(getContext(), linearLayoutHelper, topicListDTOS);

        goodsListDTOS = new ArrayList<>();

        //设置适配器
        delegateAdapter = new DelegateAdapter(virtualLayoutManager, false);
        delegateAdapter.addAdapter(singleAdapter);
        delegateAdapter.addAdapter(singleTwoAdapter);
        delegateAdapter.addAdapter(singleThreeAdapter);
        delegateAdapter.addAdapter(singleFourAdapter);
        delegateAdapter.addAdapter(gridLayoutAdapter);
        delegateAdapter.addAdapter(singleSixAdapter);
        delegateAdapter.addAdapter(gridLayoutSixAdapter);
        delegateAdapter.addAdapter(initTitle("专题精选"));
        delegateAdapter.addAdapter(horizontalAdapter);
        rel.setLayoutManager(virtualLayoutManager);
        rel.setAdapter(delegateAdapter);
    }

    private DelegateAdapter.Adapter initTitle(String title) {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setBgColor(Color.WHITE);
        linearLayoutHelper.setMarginTop(20);
        titleAdapter = new TitleAdapter(linearLayoutHelper,getContext(),title);
        return titleAdapter;
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
        newGoodsListDTOS.addAll(bean.getData().getNewGoodsList());
        hotGoodsListDTOS.addAll(bean.getData().getHotGoodsList());
        topicListDTOS.addAll(bean.getData().getTopicList());
        goodsListDTOS.addAll(bean.getData().getCategoryList());

        for (int i = 0; i < goodsListDTOS.size(); i++) {
            delegateAdapter.addAdapter(initTitle(goodsListDTOS.get(i).getName()));
            delegateAdapter.addAdapter(initCategory(goodsListDTOS.get(i).getGoodsList()));
        }
        singleAdapter.notifyDataSetChanged();
        singleThreeAdapter.notifyDataSetChanged();
        gridLayoutAdapter.notifyDataSetChanged();
        gridLayoutSixAdapter.notifyDataSetChanged();
        horizontalAdapter.notifyDataSetChanged();
    }

    private DelegateAdapter.Adapter initCategory(List<ShopBean.DataDTO.CategoryListDTO.GoodsListDTO> goodsList) {
        //每行个数
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        //公共属性
        //总共个数
        gridLayoutHelper.setItemCount(goodsList.size());
        gridLayoutHelper.setBgColor(Color.WHITE);// 设置背景颜色

        //子元素垂直间距
        gridLayoutHelper.setVGap(50);
        gridLayoutHelper.setHGap(20);
        //是否自动填充空白区域
        gridLayoutHelper.setAutoExpand(false);
        //每行多少网格
        gridLayoutHelper.setSpanCount(2);
        fuAdapter = new FuAdapter(goodsList, getContext(), gridLayoutHelper);
        return fuAdapter;
    }
}