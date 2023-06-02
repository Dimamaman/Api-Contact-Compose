package uz.gita.dima.apicontactcompose.domain.local.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.gita.dima.apicontactcompose.data.source.local.dao.ContactDao
import uz.gita.dima.apicontactcompose.domain.local.repository.AppRepository
import uz.gita.dima.apicontactcompose.domain.network.model.ContactData
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val dao: ContactDao
) : AppRepository {

    override fun add(contactData: ContactData) {
        dao.add(contactData.toEntity())
    }

    override fun delete(contactData: ContactData) {
        dao.delete(contactData.toEntity())
    }

    override fun update(contactData: ContactData) {
        dao.update(contactData.toEntity())
    }

    override fun retrieveAllContacts(): Flow<List<ContactData>> =
        dao.retrieveAllContacts().map { list ->
            list.map { it.toData() }
        }

    override  fun getContact(contactId: Long): ContactData {
        return dao.getContactById(contactId.toInt()).toData()
    }
}