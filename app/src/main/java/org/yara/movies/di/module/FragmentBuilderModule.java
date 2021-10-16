package org.yara.movies.di.module;


import org.yara.movies.ui.fragment.DetailsFragment;
import org.yara.movies.ui.fragment.HomeFragment;
import org.yara.movies.ui.fragment.SplashFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract SplashFragment contributesSplashFragment();

    @ContributesAndroidInjector
    abstract HomeFragment contributesHomeFragment();

    @ContributesAndroidInjector
    abstract DetailsFragment contributesDetailsFragment();

}
