package uz.gita.dima.apicontactcompose.domain.network.model

import uz.gita.dima.apicontactcompose.data.source.local.entity.ContactEntity
import uz.gita.dima.apicontactcompose.data.source.remote.request.AddContactRequest
import uz.gita.dima.apicontactcompose.data.source.remote.response.ContactData

data class ContactData(
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val isSavedToServer:Int = 0
) {
    fun toEntity() = ContactEntity(
        id, firstName, lastName, phone
    )

    fun toContactData(): AddContactRequest {
        return AddContactRequest(firstName, lastName, phone)
    }

    fun toContactDataToResponse(): ContactData {
        return ContactData(id.toInt(),firstName,lastName, phone)
    }
}