package uz.gita.dima.apicontactcompose.presenter.register

import kotlinx.coroutines.flow.StateFlow
import uz.gita.dima.apicontactcompose.data.source.remote.request.RegisterRequest

interface RegisterViewModel {

    sealed interface Intent {
        class Ready(val registerRequest: RegisterRequest): Intent

        object BackLogin: Intent
        object ClearMessage: Intent
    }

    data class UIState(
        val message: String = "",
        val errorMessage: String = "",
        val progressState: Boolean = false
    )

    interface Direction {
        suspend fun navigateToVerify(phone: String)
        suspend fun backLogin()
    }

    interface ViewModel {
        val uiState: StateFlow<UIState>
        fun onEventDispatchers(intent: Intent)
    }
}