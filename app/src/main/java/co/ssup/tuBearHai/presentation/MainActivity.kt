package co.ssup.tuBearHai.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import co.ssup.tuBearHai.presentation.ui.MainScreen
import co.ssup.tuBearHai.presentation.ui.theme.TuBearHaiTheme
import co.ssup.tuBearHai.util.NavGraph
import co.ssup.tuBearHai.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      TuBearHaiTheme {
        // A surface container using the 'background' color from the theme
        NavGraph(startDestination = Screens.MainScreen.route, navController = rememberNavController())
      }
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  TuBearHaiTheme {
    Greeting("Android")
  }
}