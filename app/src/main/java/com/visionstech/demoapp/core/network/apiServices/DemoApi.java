package com.visionstech.demoapp.core.network.apiServices;

import com.visionstech.demoapp.core.network.responses.NYResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DemoApi {


    @GET("all-sections/7.json")
    Observable<NYResponse> fetchAllData(@Query("api-key") String key);
}
