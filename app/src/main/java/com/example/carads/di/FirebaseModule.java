package com.example.carads.di;

import com.google.firebase.auth.FirebaseAuth;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Максим on 01.02.2018.
 */


@Module
@Singleton
public class FirebaseModule {

    @Provides
    @Singleton
    public FirebaseAuth provideFirebaseUser(){

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        return mAuth;
    }

}
