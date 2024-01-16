package presentation.base

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import di.dataModule
import di.useCaseModule
import di.viewModelModule
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.koin.compose.KoinApplication
import presentation.home.HomeScreen
import presentation.home.HomeViewModel

@Composable
fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()

        KoinApplication(
            application = {
                modules(dataModule, useCaseModule, viewModelModule)
            }
        ) {
            MaterialTheme {
                Surface {
                    NavHost(
                        navigator = navigator,
                        navTransition = NavTransition(),
                        initialRoute = "/home",
                    ) {
                        scene(route = "/home", navTransition = NavTransition()) {
                            val viewModel = koinViewModel(vmClass = HomeViewModel::class)
                            HomeScreen(homeViewModel = viewModel, onNavigateToDetail = {})
                        }
                    }
                }
            }
        }
    }
}