package com.example.carads.ui.intro;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.carads.R;
import com.example.carads.ui.primary.CarActivity;
import com.example.carads.ui.utilities.NetInspector;
import java.util.concurrent.Executors;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        showSplash();

    }


    private void showSplash(){

        changeFragment(new SplashFragment());

        Executors.newFixedThreadPool(1).execute(()->{

            try {
                Thread.sleep(3000);
                onTaskCompleted();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }


    private void onTaskCompleted() {

        runOnUiThread(()->{

            if(NetInspector.isOnline(this)){

                startActivity(new Intent(this, CarActivity.class));

                finish();

            }else{

                changeFragment(new NoNetworkConnectionFragment());
            }

        });


    }


    private  void changeFragment(Fragment fragment){
   getSupportFragmentManager().beginTransaction().replace(R.id.splash,fragment).commitAllowingStateLoss();

}



}
