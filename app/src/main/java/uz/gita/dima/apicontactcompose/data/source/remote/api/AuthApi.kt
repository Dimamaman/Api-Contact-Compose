package uz.gita.dima.apicontactcompose.data.source.remote.api

import uz.gita.dima.apicontactcompose.data.source.remote.request.LoginRequest
import uz.gita.dima.apicontactcompose.data.source.remote.request.RegisterRequest
import uz.gita.dima.apicontactcompose.data.source.remote.request.VerifyCodeRequest
import uz.gita.dima.contactappwithclean.data.source.remote.response.LoginResponse
import uz.gita.dima.contactappwithclean.data.source.remote.response.RegisterResponse
import uz.gita.dima.contactappwithclean.data.source.remote.response.VerifyCodeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/v1/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/v1/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("api/v1/register/verify")
    suspend fun verifyCode(@Body request: VerifyCodeRequest): Response<VerifyCodeResponse>

}