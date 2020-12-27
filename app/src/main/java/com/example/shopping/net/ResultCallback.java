package com.example.shopping.net;

public interface ResultCallback<T>{
    void onSuccess(T t);
    void onFail(String msg);
}
