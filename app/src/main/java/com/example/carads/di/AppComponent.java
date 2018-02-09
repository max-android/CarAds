package com.example.carads.di;

import com.example.carads.ui.detail.DetailFragment;
import com.example.carads.ui.detail.SaveFragment;
import com.example.carads.ui.myads.AddEditAdActivity;
import com.example.carads.ui.myads.MyAdsActivity;
import com.example.carads.ui.primary.AutoFragment;
import com.example.carads.ui.primary.PrimaryFragment;
import com.example.carads.ui.registration.LoginRegisterActivity;
import com.example.carads.ui.favorites.FavoritesActivity;
import com.example.carads.ui.search.SearchableActivity;
import com.example.carads.ui.primary.CarActivity;


import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
        ServiceModule.class,
        GlideModule.class,
        DatabaseModule.class,
        MyFavoritesModule.class,
        PermissionModule.class,
        FileModule.class,
        FirebaseModule.class
})

 public interface AppComponent {

     void injectMainActivity(CarActivity activity);

     void injectSearchableActivity(SearchableActivity activity);

     void injectAvtoFragment(AutoFragment avtoFragment);
     void injectDetailFragment(DetailFragment detailFragment);
     void injectFavoritesActivity(FavoritesActivity favoritesActivity);
     void injectSaveFragment(SaveFragment saveFragment);

     void injectPrimaryFragment(PrimaryFragment primaryFragment);
     void injectLoginRegisterActivity(LoginRegisterActivity loginRegisterActivity);
     void injectMyAdsActivity (MyAdsActivity myAdsActivity);
     void injectAddEditAdActivity (AddEditAdActivity addEditAdActivity);


}
