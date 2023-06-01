package uz.gita.dima.apicontactcompose.presenter.login

import uz.gita.dima.apicontactcompose.navigation.AppNavigator
import uz.gita.dima.apicontactcompose.presenter.home.HomeScreen
import uz.gita.dima.apicontactcompose.presenter.register.RegisterScreen
import javax.inject.Inject

class LoginDirection @Inject constructor(
    private val appNavigator: AppNavigator
): LoginViewModel.Direction {
    override suspend fun navigateToHome() {
        appNavigator.replace(HomeScreen())
    }

    override suspend fun navigateToRegister() {
        appNavigator.navigateTo(RegisterScreen())
    }
}