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

public class ValueCarDialog extends DialogFragment {

    private ValueCarDialogListener dialogListener;
    private String fromValue;
    private String toValue;
    private String[] values;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        View contentView = inflater.inflate(R.layout.dialog_value, null);

        initComponentDialog(contentView);

        builder.setView(contentView);


        builder.setNegativeButton(Constants.CANCEL,negativListener);
        builder.setPositiveButton(Constants.OK,positivListener);

        builder.setTitle(Constants.SEARCH_VALUE);

        return builder.create();
    }

    private void initComponentDialog(View contentView) {

        Spinner fromSpinner=(Spinner)contentView.findViewById(R.id.from_value);
        Spinner toSpinner=(Spinner)contentView.findViewById(R.id.to_value);

        values = getResources().getStringArray(R.array.valume);

        ArrayAdapter<String> dateAdapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,values);

        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(dateAdapter);
        toSpinner.setAdapter(dateAdapter);

        fromSpinner.setOnItemSelectedListener(fromValueListener);
        toSpinner.setOnItemSelectedListener(toValueListener);
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

            if(Double.valueOf(toValue)>Double.valueOf(fromValue)){

                dialogListener.onValueSet(fromValue,toValue);

            }else{

                Toast.makeText(getContext(),Constants.SEARCH_VALUE_HINT,Toast.LENGTH_LONG).show();

            }

        }
    };


    private  final AdapterView.OnItemSelectedListener fromValueListener =new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            fromValue = values[i];
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };


    private  final AdapterView.OnItemSelectedListener toValueListener =new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            toValue = values[i];
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ValueCarDialogListener) {
            dialogListener = (ValueCarDialogListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dialogListener = null;
    }



    public interface ValueCarDialogListener {
        void onValueSet(String from_value,String to_value);
    }
}
