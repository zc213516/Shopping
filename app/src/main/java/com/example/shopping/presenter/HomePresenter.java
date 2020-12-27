package com.example.shopping.presenter;

import com.example.shopping.base.BasePresenter;
import com.example.shopping.bean.ShopBean;
import com.example.shopping.contract.HomeContract;
import com.example.shopping.fragment.HomeFragment;
import com.example.shopping.model.HomeModel;
import com.example.shopping.net.ResultCallback;
import com.example.shopping.net.URLConstant;

public class HomePresenter extends BasePresenter<HomeContract.IMainView,HomeContract.IMainModel> implements HomeContract.IMainPresenter {

    @Override
    public HomeContract.IMainModel getiModel() {
        return new HomeModel(this);
    }

    @Override
    public void getData() {
        iModel.getLoginData(URLConstant.NEWLIST, new ResultCallback<ShopBean>() {
            @Override
            public void onSuccess(ShopBean shopBean) {
                iView.onSuccess(shopBean);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }


}
