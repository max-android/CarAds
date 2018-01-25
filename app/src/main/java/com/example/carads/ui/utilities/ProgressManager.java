package com.example.carads.ui.utilities;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.carads.R;

/**
 * Created by Максим on 25.01.2018.
 */

public class ProgressManager {


    public ProgressDialog mProgressDialog;
    public Context context;


    public ProgressManager(Context context) {
        this.context = context;
        mProgressDialog = new ProgressDialog(context);
    }


    public void showProgressDialog() {
        if (mProgressDialog == null) {

            mProgressDialog.setMessage(context.getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }



    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }





}
