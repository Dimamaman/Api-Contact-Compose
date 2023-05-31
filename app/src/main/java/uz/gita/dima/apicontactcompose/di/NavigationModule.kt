package uz.gita.dima.apicontactcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.dima.apicontactcompose.navigation.AppNavigator
import uz.gita.dima.apicontactcompose.navigation.NavigationDispatcher
import uz.gita.dima.apicontactcompose.navigation.NavigationHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    fun bindAppNavigator(impl: NavigationDispatcher): AppNavigator

    @Binds
    fun bindNavigationHandler(impl: NavigationDispatcher): NavigationHandler
}