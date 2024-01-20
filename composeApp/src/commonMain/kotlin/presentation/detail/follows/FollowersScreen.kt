package presentation.detail.follows

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kmm.githubkmm.MR
import dev.icerock.moko.resources.compose.painterResource
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import presentation.detail.DetailViewModel
import utils.UiState

@Composable
fun FollowersScreen(detailViewModel: DetailViewModel, username: String, type: String) {
    val getFollowers by detailViewModel.getFollows.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        detailViewModel.setUsername(username)
        detailViewModel.setType(type)
    }

    LazyColumn {
        when (val uiState = getFollowers) {
            is UiState.Loading -> {
                items(10) {
                    FollowItemPlaceholder()
                }
            }

            is UiState.Success -> {
                items(uiState.data, key = { it.id ?: 0 }) { data ->
                    FollowItem(username = data.login.orEmpty(), imageUrl = data.avatarUrl.orEmpty())
                }
            }

            is UiState.Error -> {
                item {
                    Box(
                        modifier = Modifier.fillParentMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            modifier = Modifier.size(200.dp),
                            painter = painterResource(MR.images.img_error),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}