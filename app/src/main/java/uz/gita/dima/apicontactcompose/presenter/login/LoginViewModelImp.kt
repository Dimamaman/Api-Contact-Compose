package uz.gita.dima.apicontactcompose.presenter.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.dima.apicontactcompose.domain.network.usecase.auth.AuthUseCase
import uz.gita.dima.apicontactcompose.data.source.local.sharedPref.SharedPref
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImp @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val sharedPref: SharedPref,
    private val direction: LoginContract.Direction
) : LoginContract.ViewModel, ViewModel() {

    override val uiState = MutableStateFlow(LoginContract.UIState())

    override fun onEventDispatchers(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.LoginSubmit -> {

                uiState.update { it.copy(progressState = true) }

                if (intent.login.phone.trim().isEmpty()) {
                    uiState.update { it.copy(errorMessage = "Please Enter Phone Number") }
                    uiState.update { it.copy(progressState = false) }
                    return
                }

                if (!intent.login.phone.trim().startsWith("+998")) {
                    uiState.update { it.copy(errorMessage = "Phone number must start +998...") }
                    uiState.update { it.copy(progressState = false) }
                    return
                }

                if (intent.login.phone.trim().length != 13) {
                    uiState.update { it.copy(errorMessage = "Phone number must 13 symbol...") }
                    uiState.update { it.copy(progressState = false) }
                    return
                }

                if (intent.login.password.trim().isEmpty()) {
                    uiState.update { it.copy(errorMessage = "Please Enter Password") }
                    uiState.update { it.copy(progressState = false) }
                    return
                }

                if (intent.login.password.trim().length < 6) {
                    uiState.update { it.copy(errorMessage = "Password must 6 symbol or more...") }
                    uiState.update { it.copy(progressState = false) }
                    return
                }

                authUseCase.login(intent.login).onEach {
                    uiState.update { it.copy(progressState = false) }
                    it.onSuccess {
                        sharedPref.token = it.token
                        sharedPref.hasToken = true
                        sharedPref.phone = it.phone
                        sharedPref.parol = intent.login.password


                        uiState.update { it.copy(message = "Successfully") }

                        direction.navigateToHome()
                    }

                    it.onFailure { error ->
//                        sharedPref.hasToken = false
                        Log.d("TTT", "Fail Message -> ${error.message}")
                        uiState.update { it.copy(errorMessage = error.message.toString()) }
                    }
                }.launchIn(viewModelScope)
            }

            is LoginContract.Intent.OpenRegister -> {
                viewModelScope.launch { direction.navigateToRegister() }
            }

            is LoginContract.Intent.ClearMessage -> {
                uiState.update { it.copy(message = "", errorMessage = "") }
            }
        }
    }
}