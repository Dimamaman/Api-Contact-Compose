package uz.gita.dima.apicontactcompose.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.dima.apicontactcompose.app.App
import uz.gita.dima.apicontactcompose.domain.local.usecase.HomeUseCase
import uz.gita.dima.apicontactcompose.domain.network.usecase.contact.ContactUseCase
import uz.gita.dima.apicontactcompose.data.source.local.sharedPref.SharedPref
import uz.gita.dima.apicontactcompose.util.isInternetAvailable
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val sharedPref: SharedPref,
    private val contactUseCase: ContactUseCase,
    private val homeUseCase: HomeUseCase,
    private val direction: HomeViewModel.HomeDirection
) : HomeViewModel.ViewModel, ViewModel() {

    private val isInternetAvailable = isInternetAvailable(App.instance)

    init {
        if (isInternetAvailable) {
            contactUseCase.getAllContacts(sharedPref.token).onEach {
                uiState.update { it.copy(progressLoading = true) }
                it.onSuccess { list ->

//                    homeUseCase.retrieveAllContacts().onEach {
//                        for (i in it.indices) {
//                            if (it[i].id != list[i].id.toLong()) {
//                                list.toMutableList().add(it[i])
//                            }
//                        }
//                    }.launchIn(viewModelScope)

                    uiState.update { it.copy(progressLoading = false) }
                    uiState.update { it.copy(contacts = list.map { it.toContactData() }) }
                }

                it.onFailure { error ->
                    uiState.update { it.copy(progressLoading = false) }
                }
            }.launchIn(viewModelScope)
        } else {
            homeUseCase.retrieveAllContacts().onEach { contacts ->
                uiState.update { it.copy(progressLoading = false) }
                uiState.update { it.copy( contacts = contacts) }
            }.launchIn(viewModelScope)
        }
    }



    override val uiState = MutableStateFlow(HomeViewModel.UiState())

    override fun onEventDispatcher(intent: HomeViewModel.Intent) {
        when(intent) {
            is HomeViewModel.Intent.OpenEditContact -> {
                viewModelScope.launch {
                    direction.navigateToAddOrEditScreen(intent.contact)
                }
            }

            is HomeViewModel.Intent.OpenAddContact -> {
                viewModelScope.launch {
                    direction.navigateToAddOrEditScreen(null)
                }
            }

            is HomeViewModel.Intent.Delete -> {
                if (isInternetAvailable) {
                    contactUseCase.deleteContact(intent.contact.id.toInt()).onEach {
                        it.onSuccess {
                            uiState.update { it.copy(message = "Item deleted") }
                        }

                        it.onFailure { error ->
                            uiState.update { it.copy(message = error.message.toString()) }
                        }
                    }.launchIn(viewModelScope)
                } else {
                    homeUseCase.delete(intent.contact.toEntity()).onEach {
                        uiState.update {
                            it.copy(message = "${intent.contact.firstName} has been deleted")
                        }
                    }.launchIn(viewModelScope)
                }
            }
        }
    }
}