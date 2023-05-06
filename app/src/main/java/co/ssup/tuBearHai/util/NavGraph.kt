package co.ssup.tuBearHai.util

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.ssup.tuBearHai.presentation.ui.MainScreen

@Composable
fun NavGraph(
  startDestination: String,
  navController: NavHostController
) {
  NavHost(
    navController = navController,
    startDestination = startDestination
  ) {
    composable(route = Screens.MainScreen.route) {
      MainScreen(hiltViewModel())
    }
  }
}

