package uz.gita.dima.contactappwithclean.data.source.remote.response

import uz.gita.dima.apicontactcompose.data.source.remote.response.ContactData

data class GetAllContactsResponse(
    val list: List<ContactData>
)
