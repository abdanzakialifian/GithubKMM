package presentation.detail.follows

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import app.cash.paging.compose.itemKey
import com.kmm.githubkmm.MR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import domain.model.FollowItemModel
import presentation.detail.DetailViewModel

@Composable
fun FollowingScreen(detailViewModel: DetailViewModel, username: String, type: String) {
    val getFollowers = detailViewModel.getFollows.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        detailViewModel.setUsername(username)
        detailViewModel.setType(type)
    }

    FollowersPagingState(
        pagingData = getFollowers,
        onRetry = {

        },
    )
}

@Composable
private fun FollowersPagingState(
    pagingData: LazyPagingItems<FollowItemModel>,
    onRetry: () -> Unit
) {
    LazyColumn {
        when (pagingData.loadState.refresh) {
            is LoadState.Loading -> {
                items(10) {
                    FollowItemPlaceholder()
                }
            }

            is LoadState.NotLoading -> {
                items(
                    count = pagingData.itemCount,
                    key = pagingData.itemKey { it.id ?: 0 }
                ) { index ->
                    val data = pagingData[index]
                    FollowItem(
                        username = data?.login.orEmpty(),
                        imageUrl = data?.avatarUrl.orEmpty()
                    )
                }
            }

            is LoadState.Error -> {
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

        if (pagingData.loadState.append is LoadState.Loading) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(40.dp),
                        color = colorResource(MR.colors.darkGrey),
                    )
                }
            }
        }

        if (pagingData.loadState.append is LoadState.Error) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    IconButton(
                        onClick = onRetry,
                        content = {
                            Icon(
                                modifier = Modifier.size(40.dp),
                                imageVector = Icons.Default.Refresh,
                                contentDescription = null,
                            )
                        },
                    )
                }
            }
        }
    }
}