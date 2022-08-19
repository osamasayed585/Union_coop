package com.visionstech.demoapp.ui.$base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.visionstech.demoapp.core.network.responses.BaseResponse;
import com.visionstech.demoapp.core.repository.Repository;
import com.visionstech.demoapp.core.repository.RepositoryImp;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.CompositeDisposable;
import lombok.Getter;
import timber.log.Timber;

@Getter
@HiltViewModel
public class BaseViewModel extends ViewModel {

    @Inject
    public BaseViewModel() {
    }

    @Inject
    RepositoryImp repository;
    @Inject
    CompositeDisposable compositeDisposable;
    @Inject
    MutableLiveData<Boolean> onLoadingMutableLiveData;
    @Inject
    MutableLiveData<BaseResponse> onApiErrorMutableLiveData;


    protected boolean isSuccess(BaseResponse response) {
        if (response.getNumResults() == null) {
            onApiErrorMutableLiveData.setValue(response);
            Timber.e("From new BaseViewModel %s", response.getFault().getFaultstring());
            return false;
        } else
            return true;
    }

    protected void onFailure(Throwable throwable) {
        onApiErrorMutableLiveData.setValue(BaseResponse.builder().copyright(throwable.toString()).build());
        Timber.e(throwable);
    }

    public boolean isInternetAvailable(Object object) {
        return true;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
