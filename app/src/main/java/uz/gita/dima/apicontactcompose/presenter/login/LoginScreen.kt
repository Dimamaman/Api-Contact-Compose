package uz.gita.dima.apicontactcompose.presenter.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.dima.apicontactcompose.data.source.remote.request.LoginRequest
import uz.gita.dima.apicontactcompose.presenter.login.component.MyTextField

class LoginScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: LoginViewModel.ViewModel = getViewModel<LoginViewModelImp>()

        LoginScreenContent(
            viewModel.uiState.collectAsState(),
            viewModel::onEventDispatchers
        )
    }
}

@Composable
fun LoginScreenContent(
    uiState: State<LoginViewModel.UIState>,
    onEventDispatchers: (LoginViewModel.Intent) -> Unit
) {
    val context = LocalContext.current

    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    if (uiState.value.errorMessage != "") {
        Log.d("TTT","Error Message -> ${uiState.value.errorMessage}")
        Toast.makeText(context, uiState.value.errorMessage, Toast.LENGTH_SHORT).show()
        onEventDispatchers.invoke(LoginViewModel.Intent.ClearMessage)
    }

    if (uiState.value.message != "") {
        Toast.makeText(context, uiState.value.message, Toast.LENGTH_SHORT).show()
        onEventDispatchers.invoke(LoginViewModel.Intent.ClearMessage)
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            modifier = Modifier.padding(bottom = 50.dp),
            text = "Login", fontSize = 30.sp
        )

        MyTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            placeholder = "Enter Phone Number",
            value = phone,
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Phone),
            onValueChange = { phone = it }
        )

        MyTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            placeholder = "Enter Password",
            value = password,
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { password = it }
        )

        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp),
            onClick = {
                onEventDispatchers(LoginViewModel.Intent.LoginSubmit(LoginRequest(phone, password)))

            }
        ) {
            Text(text = "Submit Login")
        }

        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            onClick = { onEventDispatchers(LoginViewModel.Intent.OpenRegister) }
        ) {
            Text(text = "Register")
        }
    }
}