package com.splichus.cleverlanceApp.dagger;

import android.app.Application;

import com.splichus.cleverlanceApp.CleverlanceApp;
import com.splichus.cleverlanceApp.dagger.module.ActivityBuilder;
import com.splichus.cleverlanceApp.dagger.module.AppModule;
import com.splichus.cleverlanceApp.dagger.module.FragmentBuilder;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
                      AppModule.class,
                      ActivityBuilder.class,
                      FragmentBuilder.class})

public interface AppComponent extends AndroidInjector<CleverlanceApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(CleverlanceApp cleverlanceApp);
}
