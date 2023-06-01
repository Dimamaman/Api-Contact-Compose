package uz.gita.dima.apicontactcompose.presenter.splash

import uz.gita.dima.apicontactcompose.data.source.remote.request.VerifyCodeRequest

interface SplashScreenViewModel {

    interface Direction {
        suspend fun navigateToLogin()
        suspend fun navigateToHome()
    }

    interface ViewModel
}