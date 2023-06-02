package uz.gita.dima.apicontactcompose.di.network.contact

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.dima.apicontactcompose.domain.network.usecase.contact.ContactUseCase
import uz.gita.dima.apicontactcompose.domain.network.usecase.contact.impl.ContactUseCaseImpl


@Module
@InstallIn(SingletonComponent::class)
interface ContactUseCaseModule {

    @Binds
    fun provideContactUseCase(imp: ContactUseCaseImpl): ContactUseCase
}