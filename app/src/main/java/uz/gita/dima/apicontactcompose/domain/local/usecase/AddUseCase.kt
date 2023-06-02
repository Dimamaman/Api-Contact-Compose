package uz.gita.dima.apicontactcompose.domain.local.usecase

import uz.gita.dima.apicontactcompose.data.source.local.entity.ContactEntity

interface AddUseCase {

    fun addContact(contact: ContactEntity)
    fun update(contact: ContactEntity)
}