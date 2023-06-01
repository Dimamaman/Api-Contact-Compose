package uz.gita.dima.apicontactcompose.presenter.verify

import uz.gita.dima.apicontactcompose.navigation.AppNavigator
import uz.gita.dima.apicontactcompose.presenter.home.HomeScreen
import javax.inject.Inject

class VerifyDirection @Inject constructor(
    private val appNavigator: AppNavigator
): VerifyViewModel.Direction {
    override suspend fun navigateToHome() {
        appNavigator.replaceAll(HomeScreen())
    }

    override suspend fun backRegister() {
        appNavigator.back()
    }
}