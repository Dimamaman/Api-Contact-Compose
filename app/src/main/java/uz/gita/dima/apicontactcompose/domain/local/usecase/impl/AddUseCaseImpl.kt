package uz.gita.dima.apicontactcompose.domain.local.usecase.impl

import uz.gita.dima.apicontactcompose.data.source.local.entity.ContactEntity
import uz.gita.dima.apicontactcompose.domain.local.repository.AppRepository
import uz.gita.dima.apicontactcompose.domain.local.usecase.AddUseCase
import javax.inject.Inject

class AddUseCaseImpl @Inject constructor(
    private val repository: AppRepository
): AddUseCase {
    override fun addContact(contact: ContactEntity) {
        repository.add(contact.toData())
    }

    override fun update(contact: ContactEntity) {
        repository.update(contact.toData())
    }
}