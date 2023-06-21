package uz.gita.dima.apicontactcompose.presenter.splash

interface SplashScreenContract {

    interface Direction {
        suspend fun navigateToLogin()
        suspend fun navigateToHome()
    }

    interface ViewModel
}