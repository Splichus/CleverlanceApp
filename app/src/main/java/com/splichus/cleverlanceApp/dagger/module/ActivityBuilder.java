package com.splichus.cleverlanceApp.dagger.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import com.splichus.cleverlanceApp.activity.MainActivity;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract MainActivity bindMainActivity();



}
