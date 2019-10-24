package com.example.mvvweather.di.modules.viewModel

import androidx.lifecycle.ViewModel
import com.example.mvvweather.presentation.mainScreen.MainScreenViewModel
import com.example.mvvweather.presentation.newLocation.AddLocationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddLocationViewModel::class)
    abstract fun bindAddLocationViewModel(addLocationViewModel: AddLocationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    abstract fun bindMainScreenViewModel(mainScreenViewModel: MainScreenViewModel): ViewModel

}