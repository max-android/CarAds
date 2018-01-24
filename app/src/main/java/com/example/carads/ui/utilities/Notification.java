package com.example.carads.ui.utilities;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.example.carads.R;
import com.example.carads.ui.callbacks.FuncVoid;

/**
 * Created by Максим on 16.01.2018.
 */

public class Notification {

    private View view;
    private Context context;

    public Notification(View view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void showMessage(String message){

        Snackbar snackbar= Snackbar.make(view,message, Snackbar.LENGTH_SHORT);
        snackbar.setDuration(Constants.TIME);
        setStyleSnackbar(snackbar);
        snackbar.show();

    }


    public void showMessageWithAction(String message, FuncVoid funcVoid){

        Snackbar snackbar = Snackbar.make(view,message, Snackbar.LENGTH_INDEFINITE);

        setStyleSnackbar(snackbar);
        snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.btnSnack));
        snackbar.setAction(context.getString(R.string.yes),v -> {funcVoid.action();}).show();

    }


    private void setStyleSnackbar(Snackbar snackbar){

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));

        TextView snackTextView = (TextView)
                snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        snackTextView.setTextColor(ContextCompat.getColor(context,R.color.colorWhite));
        snackTextView.setTextSize(context.getResources().getDimension(R.dimen.text_snack));


    }



}
