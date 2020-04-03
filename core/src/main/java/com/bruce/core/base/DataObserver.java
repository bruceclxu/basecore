package com.bruce.core.base;

import com.bruce.core.network.entity.HttpResponse;
import androidx.lifecycle.Observer;

/**
 * Created by Zeekey on 2020-03-14.
 */
public class DataObserver<T > implements IDataObserver<T>, Observer<HttpResponse<T>> {

    @Override
    public void onResponse(T result) {
    }

    @Override
    public void onError() {

    }

    @Override
    public void onChanged(HttpResponse<T> result) {
        onResponse(result.getData());

    }
}
