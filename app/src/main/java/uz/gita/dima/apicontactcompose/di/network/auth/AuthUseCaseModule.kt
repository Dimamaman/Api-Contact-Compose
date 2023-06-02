package uz.gita.dima.apicontactcompose.di.network.auth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.dima.apicontactcompose.domain.network.usecase.auth.AuthUseCase
import uz.gita.dima.apicontactcompose.domain.network.usecase.auth.impl.AuthUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface AuthUseCaseModule {

    @Binds
    fun bindAuthUseCase(impl: AuthUseCaseImpl): AuthUseCase
}