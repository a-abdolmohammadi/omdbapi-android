package org.yara.movies.di.component;

import android.app.Application;

import org.yara.movies.base.MyApplication;
import org.yara.movies.di.module.ActivityBuilderModule;
import org.yara.movies.di.module.ApiModule;
import org.yara.movies.di.module.AppModule;
import org.yara.movies.di.module.FragmentBuilderModule;
import org.yara.movies.di.module.RoomModule;
import org.yara.movies.di.module.ViewModelFactoryModule;
import org.yara.movies.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {
        ApiModule.class,
        ViewModelModule.class,
        AndroidSupportInjectionModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class,
        FragmentBuilderModule.class,
        AppModule.class,
        ViewModelFactoryModule.class,
        RoomModule.class,
})
public interface ApplicationComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}
