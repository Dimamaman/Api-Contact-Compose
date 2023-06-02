package uz.gita.dima.apicontactcompose.di.network.auth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.dima.apicontactcompose.domain.network.repository.auth.AuthRepository
import uz.gita.dima.apicontactcompose.domain.network.repository.auth.impl.AuthRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface AuthRepositoryModule {

    @Binds
    fun bindAuthRepository(imp: AuthRepositoryImpl): AuthRepository
}