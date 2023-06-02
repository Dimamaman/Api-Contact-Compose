package uz.gita.dima.apicontactcompose.presenter.addedit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.dima.apicontactcompose.app.App
import uz.gita.dima.apicontactcompose.domain.local.usecase.AddUseCase
import uz.gita.dima.apicontactcompose.domain.network.usecase.contact.ContactUseCase
import uz.gita.dima.apicontactcompose.util.isInternetAvailable
import javax.inject.Inject

@HiltViewModel
class AddEditViewModelImpl @Inject constructor(
    private val contactUseCase: ContactUseCase,
    private val useCase: AddUseCase,
    private val direction: AddEditViewModel.AddEditDirection

): AddEditViewModel.ViewModel, ViewModel() {
    override val uiState = MutableStateFlow(AddEditViewModel.UIState())

    private val isInternetAvailable = isInternetAvailable(App.instance)

    override fun onEventDispatcher(intent: AddEditViewModel.Intent) {
        when(intent) {
            is AddEditViewModel.Intent.AddContact -> {
                if (isInternetAvailable) {
                    Log.d("TTT","Connect")
                    if (intent.newContact.firstName.trim().isEmpty()) {
                        uiState.update {
                            it.copy(error = "Please enter firstname")
                        }
                        return
                    }
                    if (intent.newContact.lastName.trim().isEmpty()) {
                        uiState.update {
                            it.copy(error = "Please enter lastname")
                        }
                        return
                    }
                    if (intent.newContact.phone.trim().isEmpty()) {
                        uiState.update {
                            it.copy(error = "Please enter phone number")
                        }
                        return
                    }
                    if (!intent.newContact.phone.trim()
                            .startsWith("+998") || intent.newContact.phone.trim().length != 13
                    ) {
                        uiState.update {
                            it.copy(error = "Phone number is not correct")
                        }
                        return
                    }

                    contactUseCase.addContact(intent.newContact.toContactData()).onEach {
                        it.onSuccess {
                            useCase.addContact(intent.newContact.toEntity())
                            direction.backToMain()
                            uiState.update { it.copy(submitMessage = "Added Successfully") }
                        }
                        it.onFailure {
                            uiState.update { it.copy(error = "Failed") }
                        }
                    }.launchIn(viewModelScope)

                    uiState.update {
                        it.copy(submitMessage = "Has been successfully")
                    }

                    viewModelScope.launch {
                        delay(200)
                        direction.backToMain()
                    }
                } else {
                    Log.d("TTT","Not Connect")
                    if (intent.newContact.firstName.trim().isEmpty()) {
                        uiState.update {
                            it.copy(error = "Please enter firstname")
                        }
                        return
                    }
                    if (intent.newContact.lastName.trim().isEmpty()) {
                        uiState.update {
                            it.copy(error = "Please enter lastname")
                        }
                        return
                    }
                    if (intent.newContact.phone.trim().isEmpty()) {
                        uiState.update {
                            it.copy(error = "Please enter phone number")
                        }
                        return
                    }
                    if (!intent.newContact.phone.trim()
                            .startsWith("+998") || intent.newContact.phone.trim().length != 13
                    ) {
                        uiState.update {
                            it.copy(error = "Phone number is not correct")
                        }
                        return
                    }

                    useCase.addContact(intent.newContact.toEntity())

                    uiState.update {
                        it.copy(submitMessage = "Has been successfully")
                    }

                    viewModelScope.launch {
                        delay(200)
                        direction.backToMain()
                    }
                }
            }

            is AddEditViewModel.Intent.UpdateContact -> {

            }

            is AddEditViewModel.Intent.ClearMessage -> {

            }
        }
    }
}