package uz.gita.dima.apicontactcompose.di.network.contact

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.dima.apicontactcompose.domain.network.repository.contact.ContactRepository
import uz.gita.dima.apicontactcompose.domain.network.repository.contact.impl.ContactRepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
interface ContactRepositoryModule {

    @Binds
    fun providesContactRepository(impl: ContactRepositoryImpl): ContactRepository
}