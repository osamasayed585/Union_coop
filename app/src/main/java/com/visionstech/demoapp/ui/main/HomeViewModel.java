package com.visionstech.demoapp.ui.main;

import androidx.lifecycle.MutableLiveData;

import com.visionstech.demoapp.core.network.responses.NYResponse;
import com.visionstech.demoapp.ui.$base.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class HomeViewModel extends BaseViewModel {

    @Inject
    public HomeViewModel() {
    }

    @Inject
    public MutableLiveData<NYResponse> mNYResponseMutableLiveData;


    public void requestApi(String key) {

        getCompositeDisposable().add(

                Observable.just(key)
                        .doOnNext(__ -> getOnLoadingMutableLiveData().setValue(true))
                        .observeOn(Schedulers.io())
                        .flatMap(data -> getRepository().fetchAllData(data))
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(() -> getOnLoadingMutableLiveData().setValue(false))
                        .takeWhile(this::isSuccess)
                        .subscribe(response -> mNYResponseMutableLiveData.setValue(response), this::onFailure)
        );
    }


}
