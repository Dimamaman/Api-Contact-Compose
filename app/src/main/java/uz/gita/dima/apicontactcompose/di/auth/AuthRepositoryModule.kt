package uz.gita.dima.apicontactcompose.di.auth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.dima.apicontactcompose.domain.repository.auth.AuthRepository
import uz.gita.dima.apicontactcompose.domain.repository.auth.impl.AuthRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface AuthRepositoryModule {

    @Binds
    fun bindAuthRepository(imp: AuthRepositoryImpl): AuthRepository
}