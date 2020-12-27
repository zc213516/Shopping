package com.example.shopping.net;

public interface NetWork {
    public <T> void getLogin(String url,ResultCallback<T> callBack);
}
