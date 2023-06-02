package uz.gita.dima.apicontactcompose.presenter.home

import kotlinx.coroutines.flow.StateFlow
import uz.gita.dima.apicontactcompose.domain.network.model.ContactData

interface HomeViewModel {

    sealed interface Intent {
        class OpenEditContact(val contact: ContactData): Intent
        class Delete(val contact: ContactData): Intent
        object OpenAddContact: Intent
    }

    data class UiState(
        val message:String = "",
        val contact: ContactData? = null,
        val progressLoading: Boolean = false,
        val contacts: List<ContactData> = emptyList()
    )

    interface HomeDirection {
        suspend fun navigateToAddOrEditScreen(data: ContactData?)
    }

    interface ViewModel {
        val uiState: StateFlow<UiState>

        fun onEventDispatcher(intent: Intent)
    }
}