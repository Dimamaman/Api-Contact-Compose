package uz.gita.dima.apicontactcompose.presenter.verify

import kotlinx.coroutines.flow.StateFlow
import uz.gita.dima.apicontactcompose.data.source.remote.request.VerifyCodeRequest

interface VerifyViewModel {
    sealed interface Intent {
        class Ready(val verifyCodeRequest: VerifyCodeRequest): Intent

        object BackRegister: Intent
        object ClearMessage: Intent
    }

    data class UIState(
        val message: String = "",
        val errorMessage: String = "",
        val progressState: Boolean = false
    )

    interface Direction {
        suspend fun navigateToHome()
        suspend fun backRegister()
    }

    interface ViewModel {
        val uiState: StateFlow<UIState>
        fun onEventDispatchers(intent: Intent)
    }
}