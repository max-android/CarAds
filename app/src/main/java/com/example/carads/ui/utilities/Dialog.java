package com.example.carads.ui.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.carads.R;
import com.example.carads.ui.callbacks.FuncVoid;


/**
 * Created by Максим on 22.12.2017.
 */

public class Dialog {



//    public void showDialog(Context context,FuncVoid funcDelete,FuncVoid funcDetail){
//
//        AlertDialog.Builder establishBuilder = new AlertDialog.Builder(context);
//
//        establishBuilder
//                .setTitle(context.getString(R.string.select_ad))
//                .setMessage(context.getString(R.string.question))
//                .setCancelable(false);
//
//        establishBuilder.setNegativeButton(context.getString(R.string.cancel),(dialog,which)->{dialog.cancel();});
//
//        establishBuilder.setNeutralButton(context.getString(R.string.delete),(dialog,which)->{funcDelete.action();});
//
//        establishBuilder.setPositiveButton(context.getString(R.string.detail),(dialog,which)->{funcDetail.action();});
//
//        AlertDialog alert = establishBuilder.create();
//
//        alert.show();
//
//    }


    public void showDialog(Context context,Drawable drawable,FuncVoid funcDelete,FuncVoid funcDetail,int title,int message,int positive){

        AlertDialog.Builder establishBuilder = new AlertDialog.Builder(context);

        establishBuilder.setIcon(drawable)
                .setTitle(context.getString(title))
                .setMessage(context.getString(message))
                .setCancelable(false);

        establishBuilder.setNegativeButton(context.getString(R.string.cancel),(dialog,which)->{dialog.cancel();});

        establishBuilder.setNeutralButton(context.getString(R.string.delete),(dialog,which)->{funcDelete.action();});

        establishBuilder.setPositiveButton(context.getString(positive),(dialog,which)->{funcDetail.action();});

        AlertDialog alert = establishBuilder.create();

        alert.show();
    }






}
