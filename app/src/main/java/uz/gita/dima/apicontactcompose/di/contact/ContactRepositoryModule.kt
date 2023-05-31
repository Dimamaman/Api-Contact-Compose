package uz.gita.dima.apicontactcompose.di.contact

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.dima.apicontactcompose.domain.repository.contact.ContactRepository
import uz.gita.dima.apicontactcompose.domain.repository.contact.impl.ContactRepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
interface ContactRepositoryModule {

    @Binds
    fun providesContactRepository(impl: ContactRepositoryImpl): ContactRepository
}