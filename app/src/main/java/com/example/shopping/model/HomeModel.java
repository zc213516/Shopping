package com.example.shopping.model;

import com.example.shopping.contract.HomeContract;
import com.example.shopping.net.ResultCallback;
import com.example.shopping.net.RetrofitUtils;
import com.example.shopping.presenter.HomePresenter;

public class HomeModel implements HomeContract.IMainModel {


    private HomeContract.IMainPresenter presenter;

    public HomeModel(HomeContract.IMainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public <T> void getLoginData(String newlist, ResultCallback<T> beanCallBackinterface) {
        RetrofitUtils.getInstance().getLogin(newlist,beanCallBackinterface);
    }
}
