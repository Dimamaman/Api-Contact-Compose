package uz.gita.dima.apicontactcompose.presenter.home

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.dima.apicontactcompose.domain.network.model.ContactData
import uz.gita.dima.apicontactcompose.presenter.home.component.ContactItem
import uz.gita.dima.apicontactcompose.theme.ApiContactComposeTheme

class HomeScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel.ViewModel = getViewModel<HomeViewModelImpl>()
        val uiState = viewModel.uiState.collectAsState().value

        ApiContactComposeTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                HomeScreenContent(uiState, viewModel::onEventDispatcher)
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContent(
    uiSate: HomeViewModel.UiState,
    onEventDispatcher: (HomeViewModel.Intent) -> Unit
) {

    val showDialog = remember { mutableStateOf(false) }
    val data = remember { mutableStateOf(ContactData(-1, "", "", "")) }

    if (showDialog.value) {
        AlertDialogComponent(onEventDispatcher = onEventDispatcher, data = data.value, true, showDialog)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current

        LazyColumn(
            modifier = Modifier.padding(horizontal = 8.dp),
            content = {
                items(uiSate.contacts) {
                    Spacer(modifier = Modifier.size(8.dp))
                    ContactItem(
                        fname = it.firstName,
                        lname = it.lastName,
                        phone = it.phone,
                        modifier = Modifier.combinedClickable(
                            onClick = {
                                onEventDispatcher(HomeViewModel.Intent.OpenEditContact(it))
                            },
                            onLongClick = {
                                //viewModel.delete(it)
                                data.value = it
                                showDialog.value = true
                            }
                        )
                    )
                }
            })

        FloatingActionButton(
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomEnd),
            shape = RoundedCornerShape(16.dp),
            onClick = {
                onEventDispatcher(HomeViewModel.Intent.OpenAddContact)
            }) {
            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
        }
    }
}

@Composable
fun AlertDialogComponent(
    onEventDispatcher: (HomeViewModel.Intent) -> Unit,
    data: ContactData,
    show: Boolean,
    showDialog: MutableState<Boolean>
) {
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(show) }

    if (openDialog.value) {
        AlertDialog(
            properties = DialogProperties(dismissOnClickOutside = false),
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Warning", color = Color.White) },
            text = { Text("Do you want to delete ?", color = Color.White) },
            confirmButton = {
                TextButton(
                    onClick = {
                        onEventDispatcher(HomeViewModel.Intent.Delete(data))
                        openDialog.value = false
                        showDialog.value = false
                        Toast.makeText(context, "Item deleted", Toast.LENGTH_LONG).show()
                    }
                ) { Text("Confirm", color = Color.White) }
            },
            dismissButton = {
                TextButton(onClick = {
                    openDialog.value = false
                    showDialog.value = false
                })
                { Text("Dismiss", color = Color.White) }
            },
            containerColor = Color.Gray
        )
    }
}