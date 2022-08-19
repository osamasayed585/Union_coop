package com.visionstech.demoapp.core.di;

import androidx.lifecycle.MutableLiveData;

import com.visionstech.demoapp.core.network.apiServices.DemoApi;
import com.visionstech.demoapp.core.network.interceptors.HeadersInterceptor;
import com.visionstech.demoapp.core.network.interceptors.NetworkInterceptor;
import com.visionstech.demoapp.core.network.responses.BaseResponse;
import com.visionstech.demoapp.core.network.responses.NYResponse;
import com.visionstech.demoapp.core.utails.Constants;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
@InstallIn(ViewModelComponent.class)
public class NetworkModule {


    @Provides
    @ViewModelScoped
    public DemoApi provideApi(OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(DemoApi.class);
    }

    @Provides
    @ViewModelScoped
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, HeadersInterceptor headersInterceptor, NetworkInterceptor networkInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .addInterceptor(headersInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @ViewModelScoped
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(Timber::i);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    public MutableLiveData<NYResponse> mNYResponseMutableLiveData() {
        return new MutableLiveData<>();
    }

    @Provides
    public MutableLiveData<Boolean> ProvidesBooleanMutableLiveData() {
        return new MutableLiveData<>();
    }

    @Provides
    public CompositeDisposable ProvidesCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    public MutableLiveData<BaseResponse> ProvidesBaseResponse() {
        return new MutableLiveData<>();
    }

}
