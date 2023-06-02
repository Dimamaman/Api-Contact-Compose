package uz.gita.dima.apicontactcompose.data.source.remote.api

import uz.gita.dima.apicontactcompose.data.source.remote.request.AddContactRequest
import uz.gita.dima.apicontactcompose.data.source.remote.request.EditContactRequest
import uz.gita.dima.contactappwithclean.data.source.remote.response.AddContactResponse
import uz.gita.dima.apicontactcompose.data.source.remote.response.ContactData
import uz.gita.dima.contactappwithclean.data.source.remote.response.EditContactResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ContactApi {

    @POST("/api/v1/contact")
    suspend fun addContact(@Header("token") token: String, @Body request: AddContactRequest): Response<AddContactResponse>

    @GET("/api/v1/contact")
    suspend fun getAllContacts(@Header("token") token: String): Response<List<ContactData>>

    @DELETE("/api/v1/contact")
    suspend fun deleteNote(@Header("token") token: String, @Query("id") id: Int): Response<Unit>

    @PUT("api/v1/contact")
    suspend fun editContact(@Header("token") token: String,@Body request: EditContactRequest): Response<EditContactResponse>

}