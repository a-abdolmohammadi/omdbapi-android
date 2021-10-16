package org.yara.movies.di.module;

import androidx.lifecycle.ViewModel;


import org.yara.movies.viewModel.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindMainActivityViewModel(MainViewModel viewModel);

}
