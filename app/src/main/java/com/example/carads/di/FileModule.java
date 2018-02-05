package com.example.carads.di;

import android.content.Context;

import com.example.carads.storage.file.FileManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Максим on 24.01.2018.
 */
@Module
@Singleton
public class FileModule {

    private Context context;


    public FileModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public FileManager provideFileManager(){

        return new FileManager(context);

    }


}
