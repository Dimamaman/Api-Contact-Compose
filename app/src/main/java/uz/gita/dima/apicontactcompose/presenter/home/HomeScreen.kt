package uz.gita.dima.apicontactcompose.presenter.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uz.gita.dima.apicontactcompose.navigation.AppScreen

class HomeScreen: AppScreen() {
    @Composable
    override fun Content() {
        HomeScreenContent()
    }


}

@Composable
fun HomeScreenContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Home Screen")
    }
}