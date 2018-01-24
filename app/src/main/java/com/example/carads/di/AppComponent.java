package com.example.carads.di;

import com.example.carads.storage.favorites.MyFavorites;
import com.example.carads.ui.detail.DetailFragment;
import com.example.carads.ui.detail.SaveFragment;
import com.example.carads.ui.primary.AutoFragment;
import com.example.carads.ui.search.FavoritesActivity;
import com.example.carads.ui.search.SearchableActivity;
import com.example.carads.ui.primary.CarActivity;
import com.example.carads.ui.detail.DetailActivity;


import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
        ServiceModule.class,
        GlideModule.class,
        DatabaseModule.class,
        MyFavoritesModule.class,
        PermissionModule.class,
        FileModule.class
})

 public interface AppComponent {

     void injectMainActivity(CarActivity activity);

     void injectSearchableActivity(SearchableActivity activity);

     void injectAvtoFragment(AutoFragment avtoFragment);
     void injectDetailFragment(DetailFragment detailFragment);
     void injectFavoritesActivity(FavoritesActivity favoritesActivity);
     void injectSaveFragment(SaveFragment saveFragment);


}
