package com.example.carads.model.storage.file;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;


import com.example.carads.R;
import com.example.carads.model.storage.database.entity.Car;
import com.example.carads.ui.utilities.Constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class FileManager {


    private Context context;
    private Handler handler;

    public FileManager(Context context) {
        this.context = context;
        handler=new Handler(Looper.getMainLooper());
    }



           //сохранение картинки в отдельную папку внешнего хранилища

    public void saveImageIntoExternalStorage(Bitmap bitmap,Car car){

        new Thread(()->{

            if(isExternalStorageWritable()) {

                if(!createDirectory().exists()) {

                    createDirectory().mkdir();

                }

                String rootPath = createDirectory().getPath();

                String file_name = createNameFile(car,Constants.FORMAT_JPG);

                File file = new File(rootPath, file_name);

                try (OutputStream outputStream = new FileOutputStream(file)) {

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

                } catch (IOException e) {
                    e.printStackTrace();
                     handler.post(()->showMessage(context.getString(R.string.save_error_data)));
                  //  handler.post(() -> showMessage(context.getString(R.string.save_error_data) + "IOException"));
                }

                handler.post(() -> showMessage(context.getString(R.string.image_save_successful) + " " + file.getAbsolutePath()));

            }else {     handler.post(() -> showMessage(context.getString(R.string.directory_not_available)));         }
        }).start();
        }


    public void saveImageIntoPicturesDirectory(Bitmap bitmap,Car car){

        new Thread(()->{

            if(isExternalStorageWritable()) {

                String location = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, createNameFile(car,Constants.FORMAT_JPG), createNameFile(car,Constants.FORMAT_JPG));
                if (location != null) {
                    handler.post(() -> {
                        showMessage(context.getString(R.string.save_in_pictures));

                    });
                } else {
                    handler.post(() -> {
                        showMessage(context.getString(R.string.save_error_data));
                    });
                }
            }else { handler.post(() -> showMessage(context.getString(R.string.directory_not_available)));}
        }).start();
    }


    public void saveImageIntoApplicationStorage(Bitmap bitmap,Car car){

        new Thread(()->{


            try(FileOutputStream outputStream = context.openFileOutput(createNameFile(car,Constants.FORMAT_JPG), Context.MODE_PRIVATE)) {

                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

                handler.post(()->{ showMessage(context.getString(R.string.image_saved_internal)); });
            } catch (IOException e) {
                e.printStackTrace();
                handler.post(()->{ showMessage(context.getString(R.string.save_error_data)); });
            }

           // PrintWriter printWriter = new PrintWriter(new FileWriter());

                       String file_name = createNameFile(car,Constants.FORMAT_TXT);

             //  try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(car.getName() + System.currentTimeMillis() + Constants.FORMAT_TXT, Context.MODE_PRIVATE)))) {
               try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(file_name, Context.MODE_PRIVATE)))) {
                   for (String info : createInfoForFile(car)) {
                       bufferedWriter.write(info);
                   }
                 //  bufferedWriter.close();

                   File directory = context.getDir(file_name, Context.MODE_PRIVATE);
                   Log.d("----INTERNAL----",directory.getAbsolutePath());
                   handler.post(()->{ showMessage(context.getString(R.string.data_saved_internal)+directory.getAbsolutePath()); });
               }

             catch (IOException e) {
                e.printStackTrace();
                handler.post(()->{ showMessage(context.getString(R.string.save_error_data)); });
            }

        }).start();

    }


    //сохранение данных в текстовом формате на SD
    public void saveInfoIntoExternalStorage(Car car){

        new Thread(()->{

            if(isExternalStorageWritable()) {

                if(!createDirectory().exists()) {

                    createDirectory().mkdir();
                }

                String rootPath = createDirectory().getPath();
                String name_file = createNameFile(car,Constants.FORMAT_TXT);
                File file = new File(rootPath, name_file);


                    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {

                        for (String info : createInfoForFile(car)) {
                            bufferedWriter.write(info);
                            bufferedWriter.newLine();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();

                        handler.post(() -> {
                            showMessage(context.getString(R.string.save_error_data));
                        });
                    }

                    handler.post(() -> showMessage(context.getString(R.string.data_save_successful) + " " + file.getAbsolutePath()));

             //   } else {
                    //handler.post(()-> showMessage(context.getString(R.string.save_error_data)));
                 //   handler.post(() -> showMessage(context.getString(R.string.save_error_data) + "NOT DIRECTORY-TXT"));
             //   }

            }else{

                handler.post(() -> showMessage(context.getString(R.string.directory_not_available)));


            }

        }).start();
    }


    private File createDirectory(){

        String absolutePath =  Environment.getExternalStorageDirectory().getAbsolutePath();

        File dir = new File(absolutePath + Constants.NAME_DIRECTORY);
        dir.mkdir();

        return dir;

    }



    private List<String> createInfoForFile(Car car){
        List<String> list=new ArrayList<>();

        list.add(car.getImage());
        list.add(car.getName());
        list.add(car.getDate_issue());
        list.add(String.valueOf(car.getPrice()));
        list.add(car.getColor());
        list.add(String.valueOf(car.getValume()));
        list.add(String.valueOf(car.getPower()));
        list.add(car.getOwner());
        list.add(car.getMileage());
        list.add(car.getPhone());
        list.add(car.getMail());

        return list;
    }

    private String createNameFile(Car car,String format){

       return car.getName()+System.currentTimeMillis()+format;

    }


private void showMessage(String message){
    Toast.makeText(context,message,Toast.LENGTH_LONG).show();

}



    // проверяем есть ли доступ к внешнему хранилищу
    public boolean isExternalStorageWritable()
    {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            return true;
        }
        return false;
    }

}
