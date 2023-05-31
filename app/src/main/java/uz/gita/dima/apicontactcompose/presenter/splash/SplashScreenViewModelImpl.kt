package uz.gita.dima.apicontactcompose.presenter.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.dima.apicontactcompose.data.source.local.sharedPref.SharedPref
import uz.gita.dima.apicontactcompose.data.source.remote.request.LoginRequest
import uz.gita.dima.apicontactcompose.domain.usecase.auth.AuthUseCase
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModelImpl @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val sharedPref: SharedPref,
    private val direction: SplashScreenViewModel.Direction
): SplashScreenViewModel.ViewModel, ViewModel() {

    init {
        if (sharedPref.hasToken) {
            val phone = sharedPref.phone
            val password = sharedPref.parol
            authUseCase.login(LoginRequest(phone,password)).onEach {
                it.onSuccess {
                    sharedPref.token = it.token
                    Handler(Looper.getMainLooper()).postDelayed({
                        viewModelScope.launch {
                            direction.navigateToHome()
                        }
                    }, 2000)

                }

                it.onFailure {
                    sharedPref.hasToken = false
                    direction.navigateToLogin()
                }
            }.launchIn(viewModelScope)
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                viewModelScope.launch {
                    direction.navigateToLogin()
                }
            }, 2000)
        }
    }
}