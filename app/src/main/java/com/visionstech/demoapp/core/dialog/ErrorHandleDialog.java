package com.visionstech.demoapp.core.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.visionstech.demoapp.R;
import com.visionstech.demoapp.core.network.responses.BaseResponse;
import com.visionstech.demoapp.ui.main.HomeActivity;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;

public class ErrorHandleDialog {


    private Context mContext;
    private AlertDialog errorAlertDialog;

    @Inject
    public ErrorHandleDialog(@ActivityContext Context context, AlertDialog errorAlertDialog) {
        mContext = context;
        this.errorAlertDialog = errorAlertDialog;

        errorAlertDialog.setTitle(context.getResources().getString(R.string.error));
        errorAlertDialog.setCancelable(false);

    }


    public void setBaseResponse(BaseResponse response, DialogInterface.OnClickListener onClickListener) {

        if (!response.getStatus().equals("OK")) {
            errorAlertDialog.setMessage(mContext.getString(R.string.tryAgain));
            errorAlertDialog.setButton(DialogInterface.BUTTON_POSITIVE, mContext.getResources().getString(R.string.ok), onClickListener);
        } else if (response.getFault() != null) {
            errorAlertDialog.setMessage(response.getFault().getFaultstring());
            errorAlertDialog.setButton(DialogInterface.BUTTON_POSITIVE, mContext.getResources().getString(R.string.ok), (dialog, which) -> {

                Intent intent = new Intent(mContext, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(intent);
            });
        } else {
            errorAlertDialog.setMessage(response.getMessage());
            errorAlertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                    mContext.getResources().getString(R.string.ok),
                    (dialog, which) -> errorAlertDialog.dismiss());
        }

    }

    public void show() {
        errorAlertDialog.show();
    }

    public void dismiss() {
        errorAlertDialog.dismiss();
    }
}
