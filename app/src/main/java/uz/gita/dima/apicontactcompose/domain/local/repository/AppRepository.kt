package uz.gita.dima.apicontactcompose.domain.local.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.dima.apicontactcompose.domain.network.model.ContactData

interface AppRepository {

    fun add(contactData: ContactData)
    fun delete(contactData: ContactData)
    fun update(contactData: ContactData)
    fun retrieveAllContacts(): Flow<List<ContactData>>

    fun getContact(contactId: Long): ContactData
}