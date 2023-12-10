package presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import domain.model.UsersModel
import io.github.aakira.napier.Napier
import org.koin.compose.koinInject
import utils.UiState

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinInject()) {
    val uiState by homeViewModel.getUsers.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(
            onClick = {}
        ) {
            Text("HIT API")
        }

        when (uiState) {
            is UiState.Loading -> CircularProgressIndicator()
            is UiState.Success -> {
                Napier.d(message = "CEK", tag = (uiState as UiState.Success<List<UsersModel>>).data.toString())
            }

            is UiState.Error -> {
                Napier.d(message = "CEK", tag = (uiState as UiState.Error).message)
            }
        }
    }
}