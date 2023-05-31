package uz.gita.dima.apicontactcompose.presenter.splash

interface SplashScreenViewModel {

    interface Direction {
        suspend fun navigateToLogin()
        suspend fun navigateToHome()
    }

    interface ViewModel
}