package com.example.shopping.contract;

import com.example.shopping.base.BaseModel;
import com.example.shopping.base.BaseView;
import com.example.shopping.bean.ShopBean;
import com.example.shopping.net.ResultCallback;
import com.example.shopping.net.RetrofitUtils;

public class HomeContract {
    public interface IMainView extends BaseView {

        void onSuccess(ShopBean bean);

    }
    public interface IMainPresenter{
        void getData();
    }
    public interface IMainModel extends BaseModel {
        <T>void getLoginData(String newlist, ResultCallback<T> beanCallBackinterface);
    }
}
