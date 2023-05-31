package uz.gita.dima.apicontactcompose.domain.repository.auth.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.dima.apicontactcompose.data.source.remote.api.AuthApi
import uz.gita.dima.apicontactcompose.data.source.remote.request.LoginRequest
import uz.gita.dima.apicontactcompose.data.source.remote.request.RegisterRequest
import uz.gita.dima.apicontactcompose.data.source.remote.request.VerifyCodeRequest
import uz.gita.dima.contactappwithclean.data.source.remote.response.LoginResponse
import uz.gita.dima.contactappwithclean.data.source.remote.response.RegisterResponse
import uz.gita.dima.contactappwithclean.data.source.remote.response.VerifyCodeResponse
import uz.gita.dima.apicontactcompose.domain.repository.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
): AuthRepository {
    override fun login(loginRequest: LoginRequest): Flow<Result<LoginResponse>> = flow {
        val response = authApi.login(loginRequest)
        when(response.code()) {
            in 200 .. 299 -> {
                emit(Result.success(response.body() ?: LoginResponse("","")))
            }
            else -> {
                emit(Result.failure(Exception("Failed")))
            }
        }
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)

    override fun register(registerRequest: RegisterRequest): Flow<Result<RegisterResponse>> = flow {
        val response = authApi.register(registerRequest)

        when(response.code()) {
            in 200 .. 299 -> {
                emit(Result.success(response.body() ?: RegisterResponse("Good")))
            }
            else -> {
                emit(Result.failure(Exception("Serverda xatolik")))
            }
        }
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)

    override fun verify(verifyCodeRequest: VerifyCodeRequest): Flow<Result<VerifyCodeResponse>> = flow {
        val response = authApi.verifyCode(verifyCodeRequest)

        when(response.code()) {
            in 200 .. 299 -> {
                emit(Result.success(response.body() ?: VerifyCodeResponse("Men","Sen")))
            }
            else -> {
                emit(Result.failure(Exception("Failed")))
            }
        }
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)
}