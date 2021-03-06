package com.example.carads.ui.detail;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.RequestManager;
import com.example.carads.R;
import com.example.carads.di.App;
import com.example.carads.model.storage.file.FileManager;
import com.example.carads.model.storage.database.entity.Car;
import com.example.carads.ui.utilities.Constants;
import com.example.carads.ui.utilities.Notification;
import com.example.carads.ui.utilities.WritePermission;
import javax.inject.Inject;

/**
 * Created by Максим on 15.01.2018.
 */

public class SaveFragment extends Fragment {


    @Inject
    RequestManager requestManager;

    @Inject
    FileManager fileManager;

    @Inject
    WritePermission writePermission;

    private Car car;
    private  ImageView imageView;
    private Notification notification;
    private Bitmap bitmap;

    public SaveFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_save,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialData();
        initComponents(view);


    }

    private void initialData(){

        car = (Car)getArguments().getSerializable(Constants.KEY_FRAG);
    }


    private void initComponents(View view){

        App.getAppComponent().injectSaveFragment(SaveFragment.this);
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbarSave);

       // ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
       // toolbar.setSubtitleTextColor(ContextCompat.getColor(getContext(),R.color.colorWhite));
        toolbar.setTitle(car.getName());

        notification = new Notification(view.findViewById(R.id.linSave),getContext());

         imageView=(ImageView)view.findViewById(R.id.car_save);
        requestManager.load(car.getImage()).into(imageView);

       Button saveOnPictures = (Button)view.findViewById(R.id.saveOnPictures);
       Button saveOnSDCARD = (Button)view.findViewById(R.id.saveOnSDCARD);
       Button saveOnInternal = (Button)view.findViewById(R.id.saveOnInternal);

        saveOnPictures.setOnClickListener(view1 -> saveIntoPictures() );
        saveOnSDCARD.setOnClickListener(view2 -> saveIntoSDCARD() );
        saveOnInternal.setOnClickListener(view3 -> saveIntoApplication());
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case Constants.WRITE_FILE_PERMISSION_REQUEST_CODE:
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //срабатывает только один раз при получении разрешения,потом вызывается внутри saveImage()
                    notification.showMessage(getString(R.string.permission_write_granted));

                } else {

                    notification.showMessage(getString(R.string.permission_write_denied));

                }
                break;
        }
    }


    private Bitmap convertImageIntoBitmap(){
        imageView.setDrawingCacheEnabled(true);
        imageView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        imageView.layout(0, 0,
                imageView.getMeasuredWidth(),imageView.getMeasuredHeight());
        imageView.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
        imageView.setDrawingCacheEnabled(false);

        return bitmap;
    }


    private void  correctionImageLocation(){

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.setMargins(0,24,0,0);
        imageView.setLayoutParams(params);
    }


private void saveIntoPictures(){
        //проверка на доступ
    writePermission.requestPermission(getContext(),this::savePictures);

}

    private void savePictures() {

        bitmap=convertImageIntoBitmap();
        fileManager.saveImageIntoPicturesDirectory(bitmap,car);

        correctionImageLocation();
    }



private void saveIntoSDCARD(){
    //проверка на доступ
    writePermission.requestPermission(getContext(),this::saveAllData);
    correctionImageLocation();
}


private void saveAllData(){

    bitmap=convertImageIntoBitmap();

    fileManager.saveInfoIntoExternalStorage(car);

    fileManager.saveImageIntoExternalStorage(bitmap,car);

    correctionImageLocation();
}

private void saveIntoApplication(){

    bitmap=convertImageIntoBitmap();
    fileManager.saveImageIntoApplicationStorage(bitmap,car);

    correctionImageLocation();
}

}
