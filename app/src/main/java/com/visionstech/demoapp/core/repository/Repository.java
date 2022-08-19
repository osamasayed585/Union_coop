package com.visionstech.demoapp.core.repository;

import com.visionstech.demoapp.core.network.responses.NYResponse;

import io.reactivex.Observable;

public interface Repository {

    Observable<NYResponse> fetchAllData(String key);
}
