package co.ssup.tuBearHai.util

sealed class Screens(val route: String) {
  object MainScreen: Screens("main_screen")

}
