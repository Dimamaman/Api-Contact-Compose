package uz.gita.dima.apicontactcompose.domain.repository.contact

import kotlinx.coroutines.flow.Flow
import uz.gita.dima.apicontactcompose.data.source.remote.request.AddContactRequest
import uz.gita.dima.apicontactcompose.data.source.remote.request.EditContactRequest
import uz.gita.dima.contactappwithclean.data.source.remote.response.AddContactResponse
import uz.gita.dima.contactappwithclean.data.source.remote.response.AllContacts
import uz.gita.dima.contactappwithclean.data.source.remote.response.EditContactResponse

interface ContactRepository {
    fun getAllContacts(token: String): Flow<Result<List<AllContacts>>>
    fun deleteContact(id: Int): Flow<Result<Unit>>

    fun addContact(newContact: AddContactRequest): Flow<Result<AddContactResponse>>
    fun updateContact(editContact: EditContactRequest): Flow<Result<EditContactResponse>>
}