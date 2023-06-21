package uz.gita.dima.apicontactcompose.presenter.verify

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.dima.apicontactcompose.R
import uz.gita.dima.apicontactcompose.data.source.remote.request.VerifyCodeRequest
import uz.gita.dima.apicontactcompose.navigation.AppScreen
import uz.gita.dima.apicontactcompose.presenter.login.component.MyTextField

class VerifyScreen(val phone: String) : AppScreen() {

    @Composable
    override fun Content() {
        val viewModel: VerifyContract.ViewModel = getViewModel<VerifyViewModelImpl>()
        VerifyScreenContent(viewModel.uiState.collectAsState(),viewModel::onEventDispatchers,phone)
    }
}

@Composable
fun VerifyScreenContent(
    uiState: State<VerifyContract.UIState>,
    onEventDispatchers: (VerifyContract.Intent) -> Unit,
    phone: String
) {
    val context = LocalContext.current

    if (uiState.value.errorMessage != "") {
        Log.d("TTT", "Error Message -> ${uiState.value.errorMessage}")
        Toast.makeText(context, uiState.value.errorMessage, Toast.LENGTH_SHORT).show()
        onEventDispatchers.invoke(VerifyContract.Intent.ClearMessage)
    }

    if (uiState.value.message != "") {
        Toast.makeText(context, uiState.value.message, Toast.LENGTH_SHORT).show()
        onEventDispatchers.invoke(VerifyContract.Intent.ClearMessage)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Image(
            modifier = Modifier
                .clickable {
                    onEventDispatchers(VerifyContract.Intent.BackRegister)
                }
                .size(24.dp),
            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
            contentDescription = "back"
        )

        var code by remember { mutableStateOf("") }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            MyTextField(
                placeholder = "Enter sms code",
                value = code,
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { code = it },
                visualTransformation = {
                    mobileNumberFilter(it)
                }
            )
        }

        if (code.length == 6) {
            val verifyCodeRequest = VerifyCodeRequest(phone, code)
            onEventDispatchers(VerifyContract.Intent.Ready(verifyCodeRequest))
        }
    }
}

@Composable
fun Test() {

    var mobileNumber by rememberSaveable { mutableStateOf("") }
    Column {
        Row(modifier = Modifier.padding(all = 10.dp)) {
            Text(
                text = "Mobile number",
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )
            BasicTextField(
                value = mobileNumber,
                onValueChange = { mobileNumber = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = {
                    mobileNumberFilter(it)
                }
            )
        }
        Box(
            modifier = Modifier
                .height(1.dp)
                .padding(start = 10.dp)
                .fillMaxWidth()
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.height(20.dp))
        if (mobileNumber.length == 4) {

        }
        Text(text = "Actual value:\n$mobileNumber")
    }

}

const val mask = "xx xxx xx xx"

private fun mobileNumberFilter(text: AnnotatedString): TransformedText {
    // change the length
    val trimmed =
        if (text.text.length >= 4) text.text.substring(0..3) else text.text

    val annotatedString = AnnotatedString.Builder().run {
        for (i in trimmed.indices) {
            append(trimmed[i])
            if (i == 1 || i == 4 || i == 6) {
                append(" ")
            }
        }
        pushStyle(SpanStyle(color = Color.LightGray))
        append(mask.takeLast(mask.length - length))
        toAnnotatedString()
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 4) return offset + 1
            if (offset <= 6) return offset + 2
            if (offset <= 9) return offset + 3
            return 12
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 4) return offset - 1
            if (offset <= 6) return offset - 2
            if (offset <= 9) return offset - 3
            return 9
        }
    }

    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}

@Composable
fun KeyboardKey(text: String, onPress: () -> Unit) {
    Button(onClick = onPress) {
        Text(text = text)
    }
}

@Composable
fun CustomKeyboard(onKeyPressed: (Char) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            KeyboardKey(text = "1", onPress = { onKeyPressed('1') })
            KeyboardKey(text = "2", onPress = { onKeyPressed('2') })
            KeyboardKey(text = "3", onPress = { onKeyPressed('3') })
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            KeyboardKey(text = "4", onPress = { onKeyPressed('4') })
            KeyboardKey(text = "5", onPress = { onKeyPressed('5') })
            KeyboardKey(text = "6", onPress = { onKeyPressed('6') })
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            KeyboardKey(text = "7", onPress = { onKeyPressed('7') })
            KeyboardKey(text = "8", onPress = { onKeyPressed('8') })
            KeyboardKey(text = "9", onPress = { onKeyPressed('9') })
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            KeyboardKey(text = "*", onPress = { onKeyPressed('*') })
            KeyboardKey(text = "0", onPress = { onKeyPressed('0') })
            KeyboardKey(text = "#", onPress = { onKeyPressed('#') })
        }
    }
}

@Preview
@Composable
fun CustomKeyboardPreview() {
    Test()
}