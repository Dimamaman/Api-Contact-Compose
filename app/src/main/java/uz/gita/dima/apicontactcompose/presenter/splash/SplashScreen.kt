package uz.gita.dima.apicontactcompose.presenter.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.dima.apicontactcompose.R


class SplashScreen: AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: SplashScreenViewModel.ViewModel = getViewModel<SplashScreenViewModelImpl>()
        SplashScreenContent()
    }
}

@Composable
fun SplashScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(250.dp),
            painter = painterResource(id = R.drawable.contact),
            contentDescription = "splashImage"
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Contact App",
            fontSize = 30.sp
        )
    }
}

@Preview
@Composable
fun SplashScreenContentPreview() {
    Surface {
        SplashScreenContent()
    }
}