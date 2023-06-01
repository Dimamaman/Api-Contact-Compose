package uz.gita.dima.apicontactcompose.presenter.verify

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
import uz.gita.dima.apicontactcompose.domain.usecase.auth.AuthUseCase
import javax.inject.Inject

@HiltViewModel
class VerifyViewModelImpl @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val sharedPref: SharedPref,
    private val direction: VerifyViewModel.Direction
) : VerifyViewModel.ViewModel, ViewModel() {
    override val uiState = MutableStateFlow(VerifyViewModel.UIState())

    override fun onEventDispatchers(intent: VerifyViewModel.Intent) {
        when(intent) {
            is VerifyViewModel.Intent.Ready -> {
                uiState.update { it.copy(progressState = true) }
                authUseCase.verify(intent.verifyCodeRequest).onEach {
                    it.onSuccess {
                        Log.d("TTT","Has token Verify ->  ${sharedPref.hasToken}")
                        sharedPref.hasToken = true
                        sharedPref.token = it.token
                        sharedPref.phone = it.phone
                        direction.navigateToHome()
                        uiState.update { it.copy(progressState = true) }
                    }

                    it.onFailure { error ->
                        Log.d("TTT","Has token Verify ->  ${sharedPref.hasToken}")
                        uiState.update { it.copy(errorMessage = error.message.toString()) }
                        uiState.update { it.copy(progressState = true) }
                    }
                }.launchIn(viewModelScope)
            }

            is VerifyViewModel.Intent.BackRegister -> {
                viewModelScope.launch {
                    direction.backRegister()
                }
            }

            is VerifyViewModel.Intent.ClearMessage -> {
                uiState.update { it.copy(message = "", errorMessage = "") }
            }
        }
    }
}