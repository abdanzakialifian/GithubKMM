package presentation.navigation

sealed class Screen(val route: String) {
    data object SplashScreen : Screen("splash")
    data object HomeScreen : Screen("home")
    data object DetailScreen : Screen("detail/{username}") {
        fun createRoute(username: String): String = "detail/$username"
    }
}