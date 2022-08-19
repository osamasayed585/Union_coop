package com.visionstech.demoapp.ui.main;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.visionstech.demoapp.R;
import com.visionstech.demoapp.core.adapters.MainAdapter;
import com.visionstech.demoapp.core.network.beans.NY;
import com.visionstech.demoapp.core.network.responses.NYResponse;
import com.visionstech.demoapp.databinding.ActivityHomeBinding;
import com.visionstech.demoapp.ui.$base.BaseActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends BaseActivity {

    private ActivityHomeBinding mBinding;
    private HomeViewModel mViewModel;

    @Inject
    MainAdapter mMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        // setup view model
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        mViewModel.getOnApiErrorMutableLiveData().observe(this, this::onApiError);
        mViewModel.getOnLoadingMutableLiveData().observe(this, this::onLoading);
        mViewModel.mNYResponseMutableLiveData.observe(this, this::onNyResponse);

        // setup list
        mBinding.rvItems.setAdapter(mMainAdapter);
        mMainAdapter.setItemClickListener(this::onItemClicked);

        //pull-refresh
        mBinding.refreshLayout.setOnRefreshListener(this::onRefreshed);

    }


    private void onRefreshed() {
        mViewModel.requestApi(getResources().getString(R.string.sample_key));
    }

    private void onItemClicked(NY ny) {
        // here you can make anything such as (open details screen)
        Snackbar.make(mBinding.getRoot(), "(Osama) -> This is " + ny.getTitle(), Snackbar.LENGTH_LONG)
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                .show();
    }

    private void onNyResponse(NYResponse nyResponse) {
        // here I populate data to recycler view
        mBinding.refreshLayout.setRefreshing(false);
        mMainAdapter.setData(nyResponse.getResults());
    }


    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.requestApi(getResources().getString(R.string.sample_key));
    }
}