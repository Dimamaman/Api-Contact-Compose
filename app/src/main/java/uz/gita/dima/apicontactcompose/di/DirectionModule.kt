package uz.gita.dima.apicontactcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.dima.apicontactcompose.presenter.splash.SplashDirection
import uz.gita.dima.apicontactcompose.presenter.splash.SplashScreenViewModel

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @Binds
    fun bindSplashDirection(impl: SplashDirection): SplashScreenViewModel.Direction
}