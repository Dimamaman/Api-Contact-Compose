package uz.gita.dima.apicontactcompose.presenter.home

import uz.gita.dima.apicontactcompose.domain.network.model.ContactData
import uz.gita.dima.apicontactcompose.navigation.AppNavigator
import uz.gita.dima.apicontactcompose.presenter.addedit.AddEditContactScreen
import javax.inject.Inject

class HomeDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : HomeViewModel.HomeDirection {
    override suspend fun navigateToAddOrEditScreen(data: ContactData?) {
        appNavigator.navigateTo(AddEditContactScreen(data))
    }
}