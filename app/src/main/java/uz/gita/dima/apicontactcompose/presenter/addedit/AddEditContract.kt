package uz.gita.dima.apicontactcompose.presenter.addedit

import kotlinx.coroutines.flow.StateFlow
import uz.gita.dima.apicontactcompose.domain.network.model.ContactData

interface AddEditContract {
    sealed interface Intent {
        class UpdateContact(val updateContact: ContactData): Intent
        class AddContact(val newContact: ContactData): Intent

        object ClearMessage: Intent
    }


    data class UIState(
        val error: String = "",
        val submitMessage: String = "",
    )


    interface AddEditDirection {
        suspend fun backToMain()
    }

    interface ViewModel {
        val uiState: StateFlow<UIState>
        fun onEventDispatcher(intent: Intent)
    }
}