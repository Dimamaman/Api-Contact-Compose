package uz.gita.dima.apicontactcompose.presenter.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.dima.apicontactcompose.data.source.local.sharedPref.SharedPref
import uz.gita.dima.apicontactcompose.domain.network.usecase.auth.AuthUseCase
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val direction: RegisterContract.Direction,
    private val sharedPref: SharedPref
): RegisterContract.ViewModel, ViewModel() {

    override val uiState =  MutableStateFlow(RegisterContract.UIState())

    override fun onEventDispatchers(intent: RegisterContract.Intent) {
        when(intent) {
            is RegisterContract.Intent.Ready -> {
                uiState.update { it.copy(progressState = true) }

                if (intent.registerRequest.firstName.trim().isEmpty()) {
                    uiState.update { it.copy(errorMessage = "Please Enter firstName") }
                    uiState.update { it.copy(progressState = false) }
                    return
                }
                if (intent.registerRequest.lastName.trim().isEmpty()) {
                    uiState.update { it.copy(errorMessage = "Please Enter lastName") }
                    uiState.update { it.copy(progressState = false) }
                    return
                }
                if (intent.registerRequest.phone.trim().isEmpty()) {
                    uiState.update { it.copy(errorMessage = "Please Enter Phone Number") }
                    uiState.update { it.copy(progressState = false) }
                    return
                }
                if (!intent.registerRequest.phone.trim().startsWith("+998")) {
                    uiState.update { it.copy(errorMessage = "Phone number must start +998...") }
                    uiState.update { it.copy(progressState = false) }
                    return
                }
                if (intent.registerRequest.phone.trim().length != 13) {
                    uiState.update { it.copy(errorMessage = "Phone number must 13 symbol...") }
                    uiState.update { it.copy(progressState = false) }
                    return
                }
                if (intent.registerRequest.password.trim().isEmpty()) {
                    uiState.update { it.copy(errorMessage = "Please Enter password") }
                    uiState.update { it.copy(progressState = false) }
                    return
                }
                if (intent.registerRequest.password.trim().length < 6) {
                    uiState.update { it.copy(errorMessage = "Password must 6 symbol or more...") }
                    uiState.update { it.copy(progressState = false) }
                    return
                }
                if (intent.registerRequest.confirmPassword != intent.registerRequest.password) {
                    Log.d("TTT","${intent.registerRequest.confirmPassword}  !=  ${intent.registerRequest.password}")
                    uiState.update { it.copy(errorMessage = "password did not match...") }
                    uiState.update { it.copy(progressState = false) }
                    return
                }


                authUseCase.register(intent.registerRequest).onEach {
                    it.onSuccess {
                        uiState.update { it.copy(progressState = false) }
                        Log.d("DDD","Registerdan nomer -> ${intent.registerRequest.phone}")
                        sharedPref.parol = intent.registerRequest.password
                        direction.navigateToVerify(intent.registerRequest.phone)
                    }

                    it.onFailure { error ->
                        uiState.update { it.copy(progressState = false) }
                        uiState.update { it.copy(errorMessage = error.message.toString()) }
                    }
                }.launchIn(viewModelScope)
            }

            is RegisterContract.Intent.BackLogin -> {
                viewModelScope.launch {
                    direction.backLogin()
                }
            }

            is RegisterContract.Intent.ClearMessage -> {
                uiState.update { it.copy(message = "", errorMessage = "") }
            }
        }
    }
}