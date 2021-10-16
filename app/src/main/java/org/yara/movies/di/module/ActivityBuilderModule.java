package org.yara.movies.di.module;
import org.yara.movies.base.BaseActivity;
import org.yara.movies.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    public abstract MainActivity  contributeMainActivity();

    @ContributesAndroidInjector
    public abstract BaseActivity contributeBaseActivity();
}
