package com.example.carads.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.carads.R;
import com.example.carads.ui.utilities.Constants;

/**
 * Created by Максим on 26.11.2017.
 */

public class ColorCarDialog extends DialogFragment {


    private  ColorCarDialogListener dialogListener;
    //private String color;

    private Button btnOrange;
    private Button btnBrown;
    private Button btnMet;
    private Button btnBlue1;
    private Button btnBlack;
    private Button btnAur;
    private Button btnWhite;
    private Button btnYel;
    private Button btnRed;
    private Button btnGray;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View contentView = inflater.inflate(R.layout.dialog_color, null);

        initComponentDialog(contentView);
        choiceColorAuto();

        builder.setView(contentView);

        builder.setTitle(Constants.SEARCH_COLOR);
        builder.setNegativeButton(Constants.CANCEL,negativListener);
      //  builder.setPositiveButton(Constants.OK,positivListener);

        return builder.create();
    }

    private void initComponentDialog(View contentView) {
        btnOrange =(Button)contentView.findViewById(R.id.btnOrange);
        btnBrown=(Button)contentView.findViewById(R.id.btnBrown);
        btnMet=(Button)contentView.findViewById(R.id.btnMet);
        btnBlue1=(Button)contentView.findViewById(R.id.btnBlue1);

        btnBlack=(Button)contentView.findViewById(R.id.btnBlack);
        btnAur=(Button)contentView.findViewById(R.id.btnAur);
        btnWhite=(Button)contentView.findViewById(R.id.btnWhite);
        btnYel=(Button)contentView.findViewById(R.id.btnYel);
        btnRed=(Button)contentView.findViewById(R.id.btnRed);
        btnGray=(Button)contentView.findViewById(R.id.btnGray);
    }

private void choiceColorAuto(){

    View.OnClickListener listener=(v -> {

        switch (v.getId()) {

            case R.id.btnOrange:

                dialogListener.onColorSet(btnOrange.getText().toString());

                break;

            case R.id.btnBrown:

                dialogListener.onColorSet(btnBrown.getText().toString());
                break;

            case R.id.btnMet:

                dialogListener.onColorSet(btnMet.getText().toString());
                break;
            case R.id.btnBlue1:
                dialogListener.onColorSet(btnBlue1.getText().toString());

                break;

            case R.id.btnBlack:
                dialogListener.onColorSet(btnBlack.getText().toString());

                break;
            case R.id.btnAur:
                dialogListener.onColorSet(btnAur.getText().toString());

                break;
            case R.id.btnWhite:
                dialogListener.onColorSet(btnWhite.getText().toString());

                break;
            case R.id.btnYel:
                dialogListener.onColorSet(btnYel.getText().toString());

                break;
            case R.id.btnRed:
                dialogListener.onColorSet(btnRed.getText().toString());

                break;
            case R.id.btnGray:
                dialogListener.onColorSet(btnGray.getText().toString());
        }
    });

    btnOrange.setOnClickListener(listener);
    btnBrown.setOnClickListener(listener);
    btnMet.setOnClickListener(listener);
    btnBlue1.setOnClickListener(listener);
    btnBlack.setOnClickListener(listener);
    btnAur.setOnClickListener(listener);
    btnWhite.setOnClickListener(listener);
    btnYel.setOnClickListener(listener);
    btnRed.setOnClickListener(listener);
    btnGray.setOnClickListener(listener);
}


    private final DialogInterface.OnClickListener negativListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            dismiss();

        }
    };


//    private final DialogInterface.OnClickListener positivListener=new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialogInterface, int i) {
//
//            dialogListener.onColorSet(color);
//
//        }
//    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ColorCarDialogListener) {
            dialogListener = (ColorCarDialogListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dialogListener = null;
    }



    public interface  ColorCarDialogListener {
        void onColorSet(String color);
    }


}
