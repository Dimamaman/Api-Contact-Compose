package uz.gita.dima.apicontactcompose.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDispatcher @Inject constructor() : AppNavigator, NavigationHandler {
    override val navigationBuffer = MutableSharedFlow<NavigationArg>()

    private suspend fun navigate(arg: NavigationArg) {
        navigationBuffer.emit(arg)
    }

    override suspend fun navigateTo(screen: AppScreen) = navigate {
        push(screen)
    }

    override suspend fun back() = navigate {
        pop()
    }

    override suspend fun navigateTo(screens: List<AppScreen>) = navigate {
        push(screens)
    }

    override suspend fun replaceAll(screen: AppScreen) = navigate {
        replaceAll(screen)
    }

    override suspend fun replace(screen: AppScreen) = navigate {
        replace(screen)
    }

    override suspend fun <T : AppScreen> backUntil(clazz: Class<T>) = navigate {
        popUntil { it::class == clazz }
    }
}