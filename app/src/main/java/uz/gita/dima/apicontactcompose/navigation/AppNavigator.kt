package uz.gita.dima.apicontactcompose.navigation

interface AppNavigator {
    suspend fun navigateTo(screen: AppScreen)
    suspend fun back()

    suspend fun navigateTo(screens: List<AppScreen>)
    suspend fun replaceAll(screen: AppScreen)
    suspend fun replace(screen: AppScreen)

    suspend fun <T : AppScreen> backUntil(clazz: Class<T>)
}