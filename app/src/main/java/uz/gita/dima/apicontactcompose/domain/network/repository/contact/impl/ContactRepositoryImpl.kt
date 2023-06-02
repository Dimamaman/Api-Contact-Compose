package uz.gita.dima.apicontactcompose.domain.network.repository.contact.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.dima.apicontactcompose.data.source.remote.api.ContactApi
import uz.gita.dima.apicontactcompose.data.source.remote.request.AddContactRequest
import uz.gita.dima.apicontactcompose.data.source.remote.request.EditContactRequest
import uz.gita.dima.contactappwithclean.data.source.remote.response.AddContactResponse
import uz.gita.dima.apicontactcompose.data.source.remote.response.ContactData
import uz.gita.dima.contactappwithclean.data.source.remote.response.EditContactResponse
import uz.gita.dima.apicontactcompose.domain.network.repository.contact.ContactRepository
import uz.gita.dima.apicontactcompose.data.source.local.sharedPref.SharedPref
import javax.inject.Inject


class ContactRepositoryImpl @Inject constructor(
    private val contactApi: ContactApi,
    private val sharedPref: SharedPref
) : ContactRepository {
    override fun getAllContacts(token: String): Flow<Result<List<ContactData>>> = flow {
        val response = contactApi.getAllContacts(token)
        when (response.code()) {
            in 200..299 -> {
                emit(Result.success(response.body() ?: emptyList()))
            }
            else -> {
                emit(Result.failure(Exception()))
            }
        }
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)

    override fun deleteContact(id: Int): Flow<Result<Unit>> = flow {
        val response = contactApi.deleteNote(sharedPref.token, id)
        when (response.code()) {
            in 200..299 -> {
                emit(Result.success(response.body() ?: 1) as Result<Unit>)
            }
            else -> {
                emit(Result.failure(Exception()))
            }
        }
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)

    override fun addContact(newContact: AddContactRequest): Flow<Result<AddContactResponse>> = flow {
        val response = contactApi.addContact(sharedPref.token, newContact)
        when (response.code()) {
            in 200..299 -> {
                emit(Result.success(response.body() ?: AddContactResponse("", "", "")))
            }
            else -> {
                emit(Result.failure(Exception()))
            }
        }
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)

    override fun updateContact(editContact: EditContactRequest): Flow<Result<EditContactResponse>> = flow {
        val response = contactApi.editContact(sharedPref.token,editContact)

        when(response.code()) {
            in 200 .. 299 -> {
                emit(Result.success(response.body() ?: EditContactResponse(0,"","","")))
            }
            else -> {
                emit(Result.failure(Exception()))
            }
        }
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)
}