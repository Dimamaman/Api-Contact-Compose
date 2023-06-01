package uz.gita.dima.apicontactcompose.presenter.register

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
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.dima.apicontactcompose.data.source.remote.request.LoginRequest
import uz.gita.dima.apicontactcompose.data.source.remote.request.RegisterRequest
import uz.gita.dima.apicontactcompose.navigation.AppScreen
import uz.gita.dima.apicontactcompose.presenter.login.LoginViewModel
import uz.gita.dima.apicontactcompose.presenter.login.component.MyTextField

class RegisterScreen : AppScreen() {
    @Composable
    override fun Content() {
        val viewModel: RegisterViewModel.ViewModel = getViewModel<RegisterViewModelImpl>()

        RegisterScreenContent(viewModel.uiState.collectAsState(), viewModel::onEventDispatchers)
    }
}

@Composable
fun RegisterScreenContent(
    uiState: State<RegisterViewModel.UIState>,
    onEventDispatchers: (RegisterViewModel.Intent) -> Unit
) {
    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    if (uiState.value.errorMessage != "") {
        Log.d("TTT", "Error Message -> ${uiState.value.errorMessage}")
        Toast.makeText(context, uiState.value.errorMessage, Toast.LENGTH_SHORT).show()
        onEventDispatchers.invoke(RegisterViewModel.Intent.ClearMessage)
    }

    if (uiState.value.message != "") {
        Toast.makeText(context, uiState.value.message, Toast.LENGTH_SHORT).show()
        onEventDispatchers.invoke(RegisterViewModel.Intent.ClearMessage)
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
            text = "Register", fontSize = 30.sp
        )

        MyTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            placeholder = "Enter First Name",
            value = firstName,
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { firstName = it }
        )

        MyTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            placeholder = "Enter Last Name",
            value = lastName,
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { lastName = it }
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

        MyTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            placeholder = "Enter Confirm Password",
            value = confirmPassword,
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { confirmPassword = it }
        )

        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp),
            onClick = {
                onEventDispatchers(
                    RegisterViewModel.Intent.Ready(
                        RegisterRequest(
                            firstName = firstName,
                            lastName = lastName,
                            password = password,
                            confirmPassword = confirmPassword,
                            phone = phone
                        )
                    )
                )
            }
        ) {
            Text(text = "Redy")
        }
    }
}