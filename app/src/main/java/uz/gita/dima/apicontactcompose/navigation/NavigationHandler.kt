package uz.gita.dima.apicontactcompose.navigation

import kotlinx.coroutines.flow.Flow
import uz.gita.dima.apicontactcompose.navigation.NavigationArg

interface NavigationHandler {
    val navigationBuffer: Flow<NavigationArg>
}