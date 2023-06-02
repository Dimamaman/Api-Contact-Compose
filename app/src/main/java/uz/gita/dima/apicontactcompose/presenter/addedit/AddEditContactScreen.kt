package uz.gita.dima.apicontactcompose.presenter.addedit

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.dima.apicontactcompose.domain.network.model.ContactData
import uz.gita.dima.apicontactcompose.navigation.AppScreen
import uz.gita.dima.apicontactcompose.presenter.login.component.MyTextField

class AddEditContactScreen(private val contactData: ContactData? = null): AppScreen() {
    @Composable
    override fun Content() {
        val viewModel: AddEditViewModel.ViewModel = getViewModel<AddEditViewModelImpl>()
        AddEditContactScreenContent(viewModel.uiState.collectAsState(),viewModel::onEventDispatcher, contact = contactData ?: ContactData(0, "", "", ""))
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditContactScreenContent(
    uiState: State<AddEditViewModel.UIState>,
    onEventDispatcher: (AddEditViewModel.Intent) -> Unit,
    contact: ContactData
) {
    var firstName by remember { mutableStateOf(contact.firstName) }

    var lastName by remember { mutableStateOf(contact.lastName) }

    var phone by remember { mutableStateOf(contact.phone) }
    val context = LocalContext.current
    if (uiState.value.submitMessage != "") {

        val message =
            if (contact.id == 0L) "${uiState.value.submitMessage}  added" else "${uiState.value.submitMessage} edited"

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

        onEventDispatcher.invoke(AddEditViewModel.Intent.ClearMessage)
    }

    if (uiState.value.error != "") {

        Toast.makeText(LocalContext.current, uiState.value.error, Toast.LENGTH_SHORT).show()

        onEventDispatcher.invoke(AddEditViewModel.Intent.ClearMessage)
    }

    Scaffold(
        topBar = {
//            TopAppBar(title = {
//                Text(text = if (contact.id == 0L) "Add Contact" else "Edit Contact")
//            })
        },

        ) { contentPadding ->
        // Screen content
        Column(
            modifier = Modifier.padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(modifier = Modifier.padding(start = 8.dp, top = 4.dp), text = "First name")

            MyTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                placeholder = "First name", value = firstName,
                onValueChange = { firstName = it },
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Text(modifier = Modifier.padding(start = 8.dp, top = 4.dp), text = "Last name")

            MyTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                placeholder = "Last name", value = lastName,
                onValueChange = { lastName = it },
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Text(modifier = Modifier.padding(start = 8.dp, top = 4.dp), text = "Phone name")
            MyTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                placeholder = "Number", value = phone,
                onValueChange = { phone = it },
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            ElevatedButton(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp), onClick = {

                Log.d("TTT", "Contact ID -> ${contact.id}")

                if (contact.id == 0L) {
                    onEventDispatcher(
                        AddEditViewModel.Intent.AddContact(
                            ContactData(
                                firstName = firstName,
                                lastName = lastName,
                                phone = phone
                            )
                        )
                    )
                } else {
                    onEventDispatcher(
                        AddEditViewModel.Intent.UpdateContact(
                            ContactData(
                                id = contact.id,
                                firstName = firstName,
                                lastName = lastName,
                                phone = phone
                            )
                        )
                    )
                }

            }) {
                Text(text = if (contact.id == 0L) "Add Contact" else "Edit Contact")
            }
        }
    }
}