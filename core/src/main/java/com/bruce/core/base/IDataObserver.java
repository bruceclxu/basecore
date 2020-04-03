package com.bruce.core.base;

/**
 * Created by Zeekey on 2020/3/14
 */
public interface IDataObserver<T> {
    void onResponse(T result);
    void onError();
}
