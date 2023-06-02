package uz.gita.dima.apicontactcompose.domain.network.usecase.contact.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.dima.apicontactcompose.data.source.remote.request.AddContactRequest
import uz.gita.dima.apicontactcompose.data.source.remote.request.EditContactRequest
import uz.gita.dima.apicontactcompose.domain.network.repository.contact.ContactRepository
import uz.gita.dima.contactappwithclean.data.source.remote.response.AddContactResponse
import uz.gita.dima.apicontactcompose.data.source.remote.response.ContactData
import uz.gita.dima.contactappwithclean.data.source.remote.response.EditContactResponse
import uz.gita.dima.apicontactcompose.domain.network.usecase.contact.ContactUseCase
import javax.inject.Inject


class ContactUseCaseImpl @Inject constructor(
    private val contactRepository: ContactRepository,
): ContactUseCase {
    override fun getAllContacts(token: String): Flow<Result<List<ContactData>>> {
        return contactRepository.getAllContacts(token)
    }

    override fun deleteContact(id: Int): Flow<Result<Unit>> {
        return contactRepository.deleteContact(id)
    }

    override fun addContact(newContact: AddContactRequest): Flow<Result<AddContactResponse>> {
        return contactRepository.addContact(newContact)
    }

    override fun updateContact(editContact: EditContactRequest): Flow<Result<EditContactResponse>> {
        return contactRepository.updateContact(editContact)
    }
}