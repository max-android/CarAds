package com.example.carads.ui.setting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carads.R;
import com.example.carads.ui.utilities.Constants;
import com.example.carads.ui.utilities.Notification;

/**
 * Created by Максим on 06.02.2018.
 */

public class HelpFragment extends Fragment {

    private EditText etMessage;
    private EditText etThemeMessage;

    private Button btnAskQuest;
    private Notification notification;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        return  inflater.inflate(R.layout.fragment_help,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initComponents(view);



    }


    private void initComponents(View view){

        etMessage=((TextInputLayout)view.findViewById(R.id.til_message)).getEditText();
        etThemeMessage=((TextInputLayout)view.findViewById(R.id.til_theme)).getEditText();
        btnAskQuest=(Button)view.findViewById(R.id.btnAskQuest);
        btnAskQuest.setOnClickListener(this::sendMessage);
        notification = new Notification(view.findViewById(R.id.lin_help_frag),getContext());
    }





    private void sendMessage(View view) {


        if(validateForm()) {

            String message = etMessage.getText().toString();
            String theme = etThemeMessage.getText().toString();

            Intent mail = new Intent(Intent.ACTION_SENDTO);
            mail.putExtra(Intent.EXTRA_SUBJECT, theme);
            mail.putExtra(Intent.EXTRA_TEXT, message);
            mail.setData(Uri.parse("mailto:" + Constants.OFFICIAL_MAIL));

            if (mail.resolveActivity(getContext().getPackageManager()) != null) {

                startActivity(mail);
            } else {
                showMessage(R.string.check_connection);
            }
        }else{

            showMessage(R.string.not_filled);
        }

    }


    private void showMessage(int notif){

        notification.showMessage(getString(notif));

    }


    private boolean validateForm() {

        boolean valid = true;

        if (TextUtils.isEmpty(etMessage.getText().toString())) {
            etMessage.setError(getString(R.string.fill_in_the_field));
            valid = false;
        } else {
            etMessage.setError(null);
        }


        if (TextUtils.isEmpty(etThemeMessage.getText().toString())) {
            etThemeMessage.setError(getString(R.string.fill_in_the_field));
            valid = false;
        } else {
            etThemeMessage.setError(null);
        }

        return valid;
    }



}
