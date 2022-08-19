package com.visionstech.demoapp.core.repository;

import com.visionstech.demoapp.core.network.apiServices.DemoApi;
import com.visionstech.demoapp.core.network.responses.NYResponse;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ViewModelScoped;
import io.reactivex.Observable;

@ViewModelScoped
public class RepositoryImp implements Repository{

    @Inject
    DemoApi mDemoApi;

    @Inject
    public RepositoryImp(DemoApi demoApi) {
        mDemoApi = demoApi;
    }

    @Override
    public Observable<NYResponse> fetchAllData(String key) {
        return mDemoApi.fetchAllData(key);
    }
}
