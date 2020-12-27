package com.example.shopping.net;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitUtils implements NetWork {
    private static RetrofitUtils retrofitUtils;
    private final ApiService apiService;

    public RetrofitUtils() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConstant.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static RetrofitUtils getInstance(){

        if(retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if(retrofitUtils==null){
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }

        return retrofitUtils;
    }

    @Override
    public <T> void getLogin(String url, ResultCallback<T> callBack) {
        apiService.getData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        Log.d("TAG", "onNext: "+responseBody.toString());
                        try {
                            String json = responseBody.string();
                            Type[] genericInterfaces = callBack.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
                            Type type = actualTypeArguments[0];
                            T result =new Gson().fromJson(json,type);
                            callBack.onSuccess(result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("TAG", "onError: "+e.toString());
                        callBack.onFail(e.toString());
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }
}
