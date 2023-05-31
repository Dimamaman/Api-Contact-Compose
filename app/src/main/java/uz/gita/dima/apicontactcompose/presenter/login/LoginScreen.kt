package uz.gita.dima.apicontactcompose.presenter.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen

class LoginScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        LoginScreenContent()
    }
}

@Composable
fun LoginScreenContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Login Screen")
    }
}