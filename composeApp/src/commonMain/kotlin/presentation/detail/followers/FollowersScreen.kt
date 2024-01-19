package presentation.detail.followers

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import presentation.detail.DetailViewModel
import presentation.detail.FollowItem
import utils.UiState

@Composable
fun FollowersScreen(detailViewModel: DetailViewModel, username: String, type: String) {
    val getFollowers by detailViewModel.getFollows.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        detailViewModel.setUsername(username)
        detailViewModel.setType(type)
    }

    when (val uiState = getFollowers) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            LazyColumn {
                items(uiState.data, key = { it.id ?: 0 }) { data ->
                    FollowItem(username = data.login.orEmpty(), imageUrl = data.avatarUrl.orEmpty())
                }
            }
        }

        is UiState.Error -> {}
    }
}