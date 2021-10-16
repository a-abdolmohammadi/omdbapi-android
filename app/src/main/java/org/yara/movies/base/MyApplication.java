package org.yara.movies.base;


import org.yara.movies.di.component.DaggerApplicationComponent;
import org.yara.movies.util.InternetUtil;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MyApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().application(this).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        InternetUtil.init(this);
    }
}
