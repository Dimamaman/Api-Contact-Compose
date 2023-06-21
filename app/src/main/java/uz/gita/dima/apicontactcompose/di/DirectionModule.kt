package uz.gita.dima.apicontactcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.dima.apicontactcompose.presenter.addedit.AddEditDirection
import uz.gita.dima.apicontactcompose.presenter.addedit.AddEditContract
import uz.gita.dima.apicontactcompose.presenter.home.HomeDirection
import uz.gita.dima.apicontactcompose.presenter.home.HomeContract
import uz.gita.dima.apicontactcompose.presenter.login.LoginDirection
import uz.gita.dima.apicontactcompose.presenter.login.LoginContract
import uz.gita.dima.apicontactcompose.presenter.register.RegisterDirection
import uz.gita.dima.apicontactcompose.presenter.register.RegisterContract
import uz.gita.dima.apicontactcompose.presenter.splash.SplashDirection
import uz.gita.dima.apicontactcompose.presenter.splash.SplashScreenContract
import uz.gita.dima.apicontactcompose.presenter.verify.VerifyDirection
import uz.gita.dima.apicontactcompose.presenter.verify.VerifyContract

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @Binds
    fun bindSplashDirection(impl: SplashDirection): SplashScreenContract.Direction

    @Binds
    fun bindLoginDirection(impl: LoginDirection): LoginContract.Direction

    @Binds
    fun bindRegisterDirection(impl: RegisterDirection): RegisterContract.Direction

    @Binds
    fun bindVerifyDirection(impl: VerifyDirection): VerifyContract.Direction

    @Binds
    fun bindHomeDirection(impl: HomeDirection): HomeContract.HomeDirection

    @Binds
    fun bindAddEditDirection(impl: AddEditDirection): AddEditContract.AddEditDirection
}