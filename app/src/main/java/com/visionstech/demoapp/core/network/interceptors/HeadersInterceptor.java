package com.visionstech.demoapp.core.network.interceptors;


import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.scopes.ViewModelScoped;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;


@ViewModelScoped
public class HeadersInterceptor implements Interceptor {

    @Inject
    public HeadersInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder();


        requestBuilder.addHeader("App-ID", "c5555ca5-304f-4f7a-918f-bc6963b55c5e");
        requestBuilder.addHeader("Password", "51Kjxaeudnnadb060520190ADMIN3360520190345@ucs.ae");
        requestBuilder.addHeader("Content-Type", "application/json");
        requestBuilder.addHeader("lang", "en");
        requestBuilder.addHeader("DeviceType", "IOS/Android");

        Timber.d("Osama Auth: sent");


        return chain.proceed(requestBuilder.build());
    }


}
