package com.example.carads.di;

import com.example.carads.ui.utilities.WritePermission;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Максим on 22.01.2018.
 */



@Module
@Singleton
public class PermissionModule {


    @Provides
    @Singleton
    public WritePermission provideWritePermission(){

        WritePermission writePermission = new WritePermission();

        return writePermission;
    }


}
