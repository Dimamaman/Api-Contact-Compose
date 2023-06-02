package uz.gita.dima.apicontactcompose.domain.local.usecase.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.dima.apicontactcompose.data.source.local.entity.ContactEntity
import uz.gita.dima.apicontactcompose.domain.local.repository.AppRepository
import uz.gita.dima.apicontactcompose.domain.local.usecase.HomeUseCase
import uz.gita.dima.apicontactcompose.domain.network.model.ContactData
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : HomeUseCase {

    override fun retrieveAllContacts(): Flow<List<ContactData>>  = repository.retrieveAllContacts()

    override fun delete(contact: ContactEntity): Flow<Unit>  = flow{
        repository.delete(contact.toData())
        emit(Unit)
    }
}