package uz.gita.dima.apicontactcompose.domain.usecase.auth.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.dima.apicontactcompose.data.source.remote.request.LoginRequest
import uz.gita.dima.apicontactcompose.data.source.remote.request.RegisterRequest
import uz.gita.dima.apicontactcompose.data.source.remote.request.VerifyCodeRequest
import uz.gita.dima.apicontactcompose.domain.repository.auth.AuthRepository
import uz.gita.dima.contactappwithclean.data.source.remote.response.LoginResponse
import uz.gita.dima.contactappwithclean.data.source.remote.response.RegisterResponse
import uz.gita.dima.contactappwithclean.data.source.remote.response.VerifyCodeResponse
import uz.gita.dima.apicontactcompose.domain.usecase.auth.AuthUseCase
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
): AuthUseCase {
    override fun login(loginRequest: LoginRequest): Flow<Result<LoginResponse>> {
        return authRepository.login(loginRequest)
    }

    override fun register(registerRequest: RegisterRequest): Flow<Result<RegisterResponse>> {
        return authRepository.register(registerRequest)
    }

    override fun verify(verifyCodeRequest: VerifyCodeRequest): Flow<Result<VerifyCodeResponse>> {
        return authRepository.verify(verifyCodeRequest)
    }
}