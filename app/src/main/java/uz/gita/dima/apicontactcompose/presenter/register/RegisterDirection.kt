package uz.gita.dima.apicontactcompose.presenter.register

import uz.gita.dima.apicontactcompose.navigation.AppNavigator
import uz.gita.dima.apicontactcompose.presenter.verify.VerifyScreen
import javax.inject.Inject

class RegisterDirection @Inject constructor(
    private val appNavigator: AppNavigator
): RegisterViewModel.Direction {
    override suspend fun navigateToVerify(phone: String) {
        appNavigator.navigateTo(VerifyScreen(phone))
    }

    override suspend fun backLogin() {
        appNavigator.back()
    }
}