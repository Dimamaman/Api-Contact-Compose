package uz.gita.dima.apicontactcompose.presenter.login

import kotlinx.coroutines.flow.StateFlow
import uz.gita.dima.apicontactcompose.data.source.remote.request.LoginRequest

interface LoginViewModel {
    sealed interface Intent {
        class LoginSubmit(val login: LoginRequest): Intent
        object OpenRegister: Intent

        object ClearMessage: Intent
    }

    data class UIState(
        val message: String = "",
        val errorMessage: String = "",
        val progressState: Boolean = false
    )

    interface ViewModel {
        val uiState: StateFlow<UIState>

        fun onEventDispatchers(intent: Intent)
    }

    interface Direction {
        suspend fun navigateToHome()
        suspend fun navigateToRegister()
    }
}