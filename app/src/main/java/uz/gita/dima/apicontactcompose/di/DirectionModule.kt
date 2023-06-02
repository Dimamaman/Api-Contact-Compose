package uz.gita.dima.apicontactcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.dima.apicontactcompose.presenter.home.HomeDirection
import uz.gita.dima.apicontactcompose.presenter.home.HomeViewModel
import uz.gita.dima.apicontactcompose.presenter.login.LoginDirection
import uz.gita.dima.apicontactcompose.presenter.login.LoginViewModel
import uz.gita.dima.apicontactcompose.presenter.register.RegisterDirection
import uz.gita.dima.apicontactcompose.presenter.register.RegisterViewModel
import uz.gita.dima.apicontactcompose.presenter.splash.SplashDirection
import uz.gita.dima.apicontactcompose.presenter.splash.SplashScreenViewModel
import uz.gita.dima.apicontactcompose.presenter.verify.VerifyDirection
import uz.gita.dima.apicontactcompose.presenter.verify.VerifyViewModel

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @Binds
    fun bindSplashDirection(impl: SplashDirection): SplashScreenViewModel.Direction

    @Binds
    fun bindLoginDirection(impl: LoginDirection): LoginViewModel.Direction

    @Binds
    fun bindRegisterDirection(impl: RegisterDirection): RegisterViewModel.Direction

    @Binds
    fun bindVerifyDirection(impl: VerifyDirection): VerifyViewModel.Direction

    @Binds
    fun bindHomeDirection(impl: HomeDirection): HomeViewModel.HomeDirection
}