package uz.gita.dima.apicontactcompose.data.source.remote.response

import uz.gita.dima.apicontactcompose.domain.network.model.ContactData

data class ContactData(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phone: String
) {
    fun toContactData(): ContactData {
        return ContactData(id.toLong(), firstName, lastName, phone)
    }
}
