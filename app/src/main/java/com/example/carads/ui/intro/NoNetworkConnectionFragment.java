package com.example.carads.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.carads.R;
import com.example.carads.ui.primary.CarActivity;
import com.example.carads.ui.utilities.NetInspector;
import com.example.carads.ui.utilities.Notification;

/**
 * Created by Максим on 08.02.2018.
 */

public class NoNetworkConnectionFragment extends Fragment {


    public NoNetworkConnectionFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {




        return inflater.inflate(R.layout.fragment_no_network,container,false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initComponents(view);

    }


    private void initComponents(View view){
        Button btnCheckNet = (Button)view.findViewById(R.id.btnCheckNet);

        btnCheckNet.setOnClickListener(v -> checkNet(v));

    }


    private void checkNet(View v){

        if(NetInspector.isOnline(getContext())){

            startActivity(new Intent(getContext(), CarActivity.class));
           getActivity().finish();
        }else{showMessage(getContext().getString(R.string.snack_no_network),v);}

    }



    private void showMessage(String message,View view){
        Notification notification = new Notification(view,getContext());
        notification.showMessage(message);
    }



}
