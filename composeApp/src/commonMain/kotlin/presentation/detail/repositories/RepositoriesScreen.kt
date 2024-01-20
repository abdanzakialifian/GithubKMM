package presentation.detail.repositories

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import presentation.detail.DetailViewModel
import presentation.detail.RepositoryItem
import utils.UiState

@Composable
fun RepositoriesScreen(detailViewModel: DetailViewModel, username: String) {
    val getRepositories by detailViewModel.getRepositories.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        detailViewModel.setUsername(username)
    }

    when (val uiState = getRepositories) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            LazyColumn {
                items(uiState.data, key = { it.id ?: 0 }) { data ->
                    RepositoryItem(repositoryItemModel = data)
                }
            }
        }

        is UiState.Error -> {}
    }
}