package com.example.carads.ui.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Looper;

import com.example.carads.ui.callbacks.SetFunc;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Handler;

/**
 * Created by Максим on 24.01.2018.
 */

public class Picture {


    private android.os.Handler handler;

    public Picture() {

        handler=new android.os.Handler(Looper.getMainLooper());
    }


    public  Drawable drawableFromUrl(String url)throws Exception{

        HttpURLConnection connection=(HttpURLConnection) createURL(url).openConnection();
        connection.connect();

        int response = connection.getResponseCode();

        Drawable drawable=null;

        if(response==HttpURLConnection.HTTP_OK){
            InputStream input = connection.getInputStream();
            drawable = Drawable.createFromStream(input,Constants.DIALOG_IMAGE);
            input.close();
        }

        connection.disconnect();
        return drawable;
    }



//    public Bitmap bitmapFromUrl(String url)throws Exception{
//
//        HttpURLConnection connection=(HttpURLConnection) createURL(url).openConnection();
//        connection.connect();
//
//        int response = connection.getResponseCode();
////        final BitmapFactory.Options options=new BitmapFactory.Options();
////        options.inJustDecodeBounds=true;
//        Bitmap bitmap=null;
//
//        if(response==HttpURLConnection.HTTP_OK){
//            InputStream input = connection.getInputStream();
//            bitmap = BitmapFactory.decodeStream(input);
//            input.close();
//        }
//
//        connection.disconnect();
//        return bitmap;
//    }


    public void  bitmapFromUrl2(String url, SetFunc<Bitmap> setFunc)  {


        new Thread(()-> {

            HttpURLConnection connection=null;
            try {
                connection = (HttpURLConnection) new URL(url).openConnection();

            connection.connect();

            InputStream input = null;

                int response = connection.getResponseCode();

                if(response==HttpURLConnection.HTTP_OK){
                     input = connection.getInputStream();
                  Bitmap  bitmap = BitmapFactory.decodeStream(input);
                    input.close();
                    handler.post(()->{setFunc.transferResult(bitmap);} );
                }

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } ).start();
    }



    private static URL createURL(String uri){

        URL url= null;
        try {
            url = new URL(uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

}
