package uz.gita.dima.apicontactcompose.presenter.addedit

import uz.gita.dima.apicontactcompose.navigation.AppNavigator
import javax.inject.Inject

class AddEditDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : AddEditViewModel.AddEditDirection {
    override suspend fun backToMain() {
        appNavigator.back()
    }
}