package uz.gita.dima.apicontactcompose.domain.local.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.dima.apicontactcompose.data.source.local.entity.ContactEntity
import uz.gita.dima.apicontactcompose.domain.network.model.ContactData

interface HomeUseCase {

    fun retrieveAllContacts(): Flow<List<ContactData>>

    fun delete(contact: ContactEntity):Flow<Unit>
}