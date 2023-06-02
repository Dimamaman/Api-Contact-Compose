package uz.gita.dima.apicontactcompose.di.local

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.dima.apicontactcompose.domain.local.usecase.AddUseCase
import uz.gita.dima.apicontactcompose.domain.local.usecase.HomeUseCase
import uz.gita.dima.apicontactcompose.domain.local.usecase.impl.AddUseCaseImpl
import uz.gita.dima.apicontactcompose.domain.local.usecase.impl.HomeUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindHomeUseCase(impl: HomeUseCaseImpl): HomeUseCase

    @Binds
    fun bindAddUseCase(impl: AddUseCaseImpl): AddUseCase
}