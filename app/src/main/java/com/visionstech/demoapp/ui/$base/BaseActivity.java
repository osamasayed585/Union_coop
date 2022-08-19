package com.visionstech.demoapp.ui.$base;

import android.content.DialogInterface;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.SpinKitView;
import com.visionstech.demoapp.core.dialog.ErrorHandleDialog;
import com.visionstech.demoapp.core.network.responses.BaseResponse;
import com.visionstech.demoapp.databinding.ActivityBaseBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BaseActivity extends AppCompatActivity {

    @Inject
    ErrorHandleDialog errorHandleDialog;
    public SpinKitView progressView;


    @Override
    public void setContentView(View view) {
        ActivityBaseBinding binding = ActivityBaseBinding.inflate(getLayoutInflater());
        progressView = binding.progressView;
        binding.fContainer.addView(view);
        super.setContentView(binding.getRoot());
    }

    public void onApiError(BaseResponse response) {
        errorHandleDialog.setBaseResponse(response, this::onErrorHandlerDialogOkClick);
        errorHandleDialog.show();
    }

    private void onErrorHandlerDialogOkClick(DialogInterface dialog, int which) {
        errorHandleDialog.dismiss();
        finish();
        startActivity(getIntent());
    }

    public void onLoading(boolean isLoading) {
        progressView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }
}
