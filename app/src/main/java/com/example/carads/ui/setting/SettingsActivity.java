package com.example.carads.ui.setting;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.example.carads.R;
import com.example.carads.ui.utilities.Constants;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initComponents();
    }

    private void initComponents(){

        Toolbar toolbar=(Toolbar)findViewById(R.id.tbSetting);
        //setSupportActionBar(toolbar);
       // toolbar.setSubtitle(R.string.setting);
        toolbar.setTitle(R.string.setting);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this,R.drawable.ic_arrow_back_24dp));
        toolbar.setNavigationOnClickListener(exit -> onBackPressed());



        TextView tvHelp=(TextView)findViewById(R.id.tvHelp);
        TextView tvAppInfo=(TextView)findViewById(R.id.tvAppInfo);
        TextView tvLicense=(TextView)findViewById(R.id.tvLicense);
        TextView tvConfid=(TextView)findViewById(R.id.tvConfid);

        tvHelp.setOnClickListener(this::setListener);
        tvAppInfo.setOnClickListener(this::setListener);
        tvLicense.setOnClickListener(this::setListener);
        tvConfid.setOnClickListener(this::setListener);
    }


    private void setListener(View v){

        switch (v.getId()){
            case R.id.tvHelp:
                launchDetail(Constants.HELP);
                break;
            case R.id.tvAppInfo:
                launchDetail(Constants.INFO_APP);
                break;
            case R.id.tvLicense:
                launchDetail(Constants.LICEN);
                break;
            case R.id.tvConfid:
                launchDetail(Constants.CONF);
                break;
        }
    }

    private void launchDetail(String type) {

        Intent intent = new Intent(this,DetailSettingsActivity.class);

        intent.putExtra(Constants.DET_SETTINGS,type);

        startActivity(intent);
    }

}
