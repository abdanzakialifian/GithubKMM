import androidx.compose.ui.window.ComposeUIViewController
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import presentation.base.App

fun MainViewController() = ComposeUIViewController {
    Napier.base(DebugAntilog())
    App()
}
