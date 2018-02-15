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
import android.widget.TextView;
import com.example.carads.R;
import com.example.carads.ui.utilities.Constants;

/**
 * Created by Максим on 25.11.2017.
 */

public class MarkaDialog extends DialogFragment {


private MarkaDialogListener dialogListener;



    private TextView tvAudi;
    private TextView tvBmw;
    private  TextView tvInf;
    private  TextView tvMers;
    private  TextView tvPorche;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        LayoutInflater inflater = getActivity().getLayoutInflater();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View contentView = inflater.inflate(R.layout.dialog_marka, null);

        initComponentDialog(contentView);

        choiceAuto();

        builder.setView(contentView);

        builder.setTitle(Constants.SEARCH_MARKA);

        builder.setNegativeButton(Constants.CANCEL,negativListener);

        return builder.create();

    }

    private void initComponentDialog(View contentView){

      tvAudi=(TextView)contentView.findViewById(R.id.tvAudi);
       tvBmw =(TextView)contentView.findViewById(R.id.tvBmw);
        tvInf =(TextView)contentView.findViewById(R.id.tvInf);
         tvMers =(TextView)contentView.findViewById(R.id.tvMers);
        tvPorche =(TextView)contentView.findViewById(R.id.tvPorche);
    }


    private void choiceAuto() {

        View.OnClickListener listener=(v -> {

            switch (v.getId()) {

                case R.id.tvAudi:

                    dialogListener.onMarkaSet(tvAudi.getText().toString());

                    break;

                case R.id.tvBmw:
                    dialogListener.onMarkaSet(tvBmw.getText().toString());
                    break;

                case R.id.tvInf:
                    dialogListener.onMarkaSet(tvInf.getText().toString());
                    break;
                case R.id.tvMers:
                    dialogListener.onMarkaSet(tvMers.getText().toString());
                    break;
                case R.id.tvPorche:
                    dialogListener.onMarkaSet(tvPorche.getText().toString());
                    break;

            }
        });

        tvAudi.setOnClickListener(listener);
        tvBmw.setOnClickListener(listener);
        tvInf.setOnClickListener(listener);
        tvMers.setOnClickListener(listener);
        tvPorche.setOnClickListener(listener);

    }

    private final DialogInterface.OnClickListener negativListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            dismiss();

        }
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MarkaDialogListener) {
            dialogListener = (MarkaDialogListener) context;
        }
    }




    @Override
    public void onDetach() {
        super.onDetach();
        dialogListener = null;
    }


    public interface MarkaDialogListener {
        void onMarkaSet(String marka);
    }
}
