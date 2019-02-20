package com.splichus.cleverlanceApp.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.splichus.cleverlanceApp.Constants;
import com.splichus.cleverlanceApp.api.service.BackendAPI;
import com.splichus.cleverlanceApp.presenter.ImagePresenter;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences sharedPreferences(Application application) {
        return application.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    BackendAPI api() {
        return new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(BackendAPI.class);
    }

    @Provides
    @Singleton
    @Inject
    ImagePresenter imageService(BackendAPI api) {
        return new ImagePresenter(api);
    }
}
