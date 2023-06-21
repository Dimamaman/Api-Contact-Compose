package uz.gita.dima.apicontactcompose.presenter.splash

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.dima.apicontactcompose.app.App
import uz.gita.dima.apicontactcompose.data.source.local.sharedPref.SharedPref
import uz.gita.dima.apicontactcompose.data.source.remote.request.LoginRequest
import uz.gita.dima.apicontactcompose.domain.network.usecase.auth.AuthUseCase
import uz.gita.dima.apicontactcompose.util.Network
import uz.gita.dima.apicontactcompose.util.isInternetAvailable
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModelImpl @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val sharedPref: SharedPref,
    private val direction: SplashScreenContract.Direction
): SplashScreenContract.ViewModel, ViewModel() {
    private val isInternetAvailable = isInternetAvailable(App.instance)
    init {
        Log.d("TTT","Has token Splash 1->  ${sharedPref.hasToken}")
        if (sharedPref.hasToken) {
            if (Network.checkConnectivity(App.instance)) {
                val phone = sharedPref.phone
                val password = sharedPref.parol
                Log.d("TTT","Phone -> $phone ,  Password -> $password")
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
//                    sharedPref.hasToken = false
                        Handler(Looper.getMainLooper()).postDelayed({
                            viewModelScope.launch {
                                direction.navigateToLogin()
                            }
                        }, 2000)
                    }
                }.launchIn(viewModelScope)
            } else {
                Handler(Looper.getMainLooper()).postDelayed({
                    viewModelScope.launch {
                        direction.navigateToHome()
                    }
                }, 2000)
            }
        } else {
            Log.d("TTT","Has token Splash 2 ->  ${sharedPref.hasToken}")
            Handler(Looper.getMainLooper()).postDelayed({
                viewModelScope.launch {
                    direction.navigateToLogin()
                }
            }, 2000)
        }
    }
}

fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
            return true
        }
    }
    return false
}