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

public class DateIssueCarDialog extends DialogFragment {


    private DateIssueCarDialogListener dialogListener;
//       private Spinner fromSpinner;
//       private Spinner toSpinner;


    private String fromDate;
    private String toDate;
    private String[] dates;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View contentView = inflater.inflate(R.layout.dialog_date, null);

        initComponentDialog(contentView);

        builder.setView(contentView);

        builder.setTitle(Constants.SEARCH_DATE);

        builder.setNegativeButton(Constants.CANCEL,negativListener);
        builder.setPositiveButton(Constants.OK,positivListener);

        return builder.create();
    }

    private void initComponentDialog(View contentView) {

        Spinner fromSpinner=(Spinner)contentView.findViewById(R.id.from_sp);
        Spinner toSpinner=(Spinner)contentView.findViewById(R.id.to_sp);

         dates = getResources().getStringArray(R.array.date);

        ArrayAdapter<String> dateAdapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,dates);

        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(dateAdapter);
        toSpinner.setAdapter(dateAdapter);

        fromSpinner.setOnItemSelectedListener(fromDataListener);
        toSpinner.setOnItemSelectedListener(toDataListener);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DateIssueCarDialogListener) {
            dialogListener = (DateIssueCarDialogListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dialogListener = null;
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

            if(Integer.valueOf(toDate)>Integer.valueOf(fromDate)||Integer.valueOf(toDate).equals(Integer.valueOf(fromDate))){

            dialogListener.onDateIssueSet(fromDate,toDate);

            }else{

                Toast.makeText(getContext(),Constants.SEARCH_DATE_HINT,Toast.LENGTH_LONG).show();

            }

        }
    };

    private  final AdapterView.OnItemSelectedListener fromDataListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            fromDate = dates[i];
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };


    private  final AdapterView.OnItemSelectedListener toDataListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            toDate = dates[i];
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };


    public interface DateIssueCarDialogListener {
        void onDateIssueSet(String from_date,String to_date);
    }

}
