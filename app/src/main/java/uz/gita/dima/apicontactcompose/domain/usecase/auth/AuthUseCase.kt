package uz.gita.dima.apicontactcompose.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import uz.gita.dima.apicontactcompose.data.source.remote.request.LoginRequest
import uz.gita.dima.apicontactcompose.data.source.remote.request.RegisterRequest
import uz.gita.dima.apicontactcompose.data.source.remote.request.VerifyCodeRequest
import uz.gita.dima.contactappwithclean.data.source.remote.response.LoginResponse
import uz.gita.dima.contactappwithclean.data.source.remote.response.RegisterResponse
import uz.gita.dima.contactappwithclean.data.source.remote.response.VerifyCodeResponse

interface AuthUseCase {
    fun login(loginRequest: LoginRequest): Flow<Result<LoginResponse>>
    fun register(registerRequest: RegisterRequest): Flow<Result<RegisterResponse>>
    fun verify(verifyCodeRequest: VerifyCodeRequest): Flow<Result<VerifyCodeResponse>>

}