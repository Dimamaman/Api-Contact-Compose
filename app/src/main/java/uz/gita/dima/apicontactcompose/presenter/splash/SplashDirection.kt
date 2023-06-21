package uz.gita.dima.apicontactcompose.presenter.splash

import uz.gita.dima.apicontactcompose.navigation.AppNavigator
import uz.gita.dima.apicontactcompose.presenter.home.HomeScreen
import uz.gita.dima.apicontactcompose.presenter.login.LoginScreen
import javax.inject.Inject

class SplashDirection @Inject constructor(
    private val appNavigator: AppNavigator
): SplashScreenContract.Direction {
    override suspend fun navigateToLogin() {
        appNavigator.replace(LoginScreen())
    }

    override suspend fun navigateToHome() {
        appNavigator.replace(HomeScreen())
    }
}