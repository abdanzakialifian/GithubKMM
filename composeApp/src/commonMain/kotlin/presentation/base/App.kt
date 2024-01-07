package presentation.base

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import di.dataModule
import di.useCaseModule
import di.viewModelModule
import org.koin.compose.KoinApplication
import presentation.home.HomeScreen

@Composable
fun App() {
    KoinApplication(
        application = {
            modules(dataModule, useCaseModule, viewModelModule)
        }
    ) {
        MaterialTheme {
            Surface {
                HomeScreen()
            }
        }
    }
}