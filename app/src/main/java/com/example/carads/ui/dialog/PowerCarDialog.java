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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carads.R;
import com.example.carads.ui.utilities.Constants;

/**
 * Created by Максим on 26.11.2017.
 */

public class PowerCarDialog extends DialogFragment {


    private  PowerCarDialogListener dialogListener;
    private String fromPower;
    private String toPower;
    private String[] powers;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View contentView = inflater.inflate(R.layout.dialog_power, null);

        initComponentDialog(contentView);

        builder.setView(contentView);


        builder.setNegativeButton(Constants.CANCEL,negativListener);
        builder.setPositiveButton(Constants.OK,positivListener);

        builder.setTitle(Constants.SEARCH_POWER);

        return builder.create();
    }


    private void initComponentDialog(View contentView) {

        Spinner fromSpinner=(Spinner)contentView.findViewById(R.id.from_power);
        Spinner toSpinner=(Spinner)contentView.findViewById(R.id.to_power);

        powers = getResources().getStringArray(R.array.power);

        ArrayAdapter<String> dateAdapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,powers);

        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(dateAdapter);
        toSpinner.setAdapter(dateAdapter);

        fromSpinner.setOnItemSelectedListener(fromPowerListener);
        toSpinner.setOnItemSelectedListener(toPowerListener);

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

            if(Integer.valueOf(toPower)>Integer.valueOf(fromPower)||Integer.valueOf(toPower).equals(Integer.valueOf(fromPower))){

                dialogListener.onPowerSet(fromPower,toPower);

            }else{

                Toast.makeText(getContext(),Constants.SEARCH_POWER_HINT,Toast.LENGTH_LONG).show();

            }
        }
    };

    private  final AdapterView.OnItemSelectedListener fromPowerListener =new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            fromPower = powers[i];
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };


    private  final AdapterView.OnItemSelectedListener toPowerListener =new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            toPower = powers[i];
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PowerCarDialogListener) {
            dialogListener = (PowerCarDialogListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dialogListener = null;
    }


    public interface  PowerCarDialogListener {
        void onPowerSet(String from_power,String to_power);
    }
}
