package com.example.carads.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.example.carads.R;
import com.example.carads.ui.utilities.Constants;

/**
 * Created by Максим on 26.11.2017.
 */

public class PriceCarDialog extends DialogFragment {

    private  PriceCarDialogListener dialogListener;
    private TextInputEditText etPriceFrom;
    private TextInputEditText etPriceTo;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

       View contentView = inflater.inflate(R.layout.dialog_price, null);

        initComponentDialog(contentView);

        builder.setView(contentView);

        builder.setTitle(Constants.SEARCH_PRICE);
        builder.setNegativeButton(Constants.CANCEL,negativListener);
        builder.setPositiveButton(Constants.OK,positivListener);

        return builder.create();
    }
    private final DialogInterface.OnClickListener negativListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            dismiss();

        }
    };


    private final DialogInterface.OnClickListener positivListener=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

          String fromPrice= etPriceFrom.getText().toString();
          String toPrice= etPriceTo.getText().toString();

            if (fromPrice.trim().isEmpty() || toPrice.trim().isEmpty()){

                Toast.makeText(getContext(),Constants.SEARCH_PRICE_EMPTY,Toast.LENGTH_LONG).show();

            }else{

                if(Integer.valueOf(toPrice)>Integer.valueOf(fromPrice)){

                    dialogListener.onPriceSet(fromPrice,toPrice);

                }else{

                    Toast.makeText(getContext(),Constants.SEARCH_PRICE_HINT,Toast.LENGTH_LONG).show();
                }

            }

        }
    };


    private void initComponentDialog(View contentView) {
        etPriceFrom = (TextInputEditText) contentView.findViewById(R.id.etPriceFrom);
        etPriceTo = (TextInputEditText) contentView.findViewById(R.id.etPriceTo);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PriceCarDialogListener) {
            dialogListener = (PriceCarDialogListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dialogListener = null;
    }


    public interface  PriceCarDialogListener {
        void onPriceSet(String from_price,String to_price);
    }


}
