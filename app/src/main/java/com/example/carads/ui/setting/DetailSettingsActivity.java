package com.example.carads.ui.setting;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.carads.R;
import com.example.carads.ui.utilities.Constants;

public class DetailSettingsActivity extends AppCompatActivity {

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_settings);

        initComponents();

        showDetailInfo(initData());

    }


  private void  initComponents(){
     toolbar=(Toolbar)findViewById(R.id.tbDetSettings);
      setSupportActionBar(toolbar);
      getSupportActionBar().setTitle(getString(R.string.empty_body));
      toolbar.setNavigationIcon(R.drawable.ic_exit_24dp);
      toolbar.setNavigationOnClickListener(exit -> finish());
  }



private String initData(){
    return   getIntent().getStringExtra(Constants.DET_SETTINGS);
}


private void showDetailInfo(String type){

    FragmentManager fragmentManager = getSupportFragmentManager();


    switch (type){

        case Constants.HELP:


            HelpFragment helpFragment=new HelpFragment();

            fragmentManager.beginTransaction().replace(R.id.settings_frame,helpFragment).commitAllowingStateLoss();

            toolbar.setSubtitle(R.string.help);

            break;

        case Constants.INFO_APP:

     InfoAppFragment infoAppFragment=new InfoAppFragment();

            fragmentManager.beginTransaction().replace(R.id.settings_frame,infoAppFragment).commitAllowingStateLoss();

            toolbar.setSubtitle(R.string.app_info);
            break;

        case Constants.LICEN:

            LicenseFragment licenseFragment=new LicenseFragment();

            fragmentManager.beginTransaction().replace(R.id.settings_frame,licenseFragment).commitAllowingStateLoss();

            toolbar.setSubtitle(R.string.license);

            break;
        case Constants.CONF:

ConfidentialityFragment confidentialityFragment=new ConfidentialityFragment();

            fragmentManager.beginTransaction().replace(R.id.settings_frame,confidentialityFragment).commitAllowingStateLoss();

            toolbar.setSubtitle(R.string.config);
            break;

    }



}



}
