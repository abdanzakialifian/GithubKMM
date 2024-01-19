package presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import com.kmm.githubkmm.MR
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import domain.model.UserItemModel
import presentation.component.SearchBar

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, onNavigateToDetail: (String) -> Unit) {
    val localUriHandler = LocalUriHandler.current
    val getUsersPagingData = homeViewModel.getUsers.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(MR.strings.app_name_ui),
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = fontFamilyResource(MR.fonts.Poppins.bold),
        )

        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = stringResource(MR.strings.app_description),
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontFamily = fontFamilyResource(MR.fonts.Poppins.medium),
        )

        SearchBar(
            value = homeViewModel.search,
            placeholder = MR.strings.search_users,
            onValueChange = { search ->
                homeViewModel.onSearchQuery(search)
            }
        )

        UsersPagingState(
            getUsersPagingData,
            onUserClicked = onNavigateToDetail,
            onLinkClicked = { url ->
                localUriHandler.openUri(url)
            },
            onRetry = {
                getUsersPagingData.retry()
            }
        )
    }
}

@Composable
private fun UsersPagingState(
    pagingData: LazyPagingItems<UserItemModel>,
    onUserClicked: (String) -> Unit,
    onLinkClicked: (String) -> Unit,
    onRetry: () -> Unit,
) {
    LazyColumnLayout {
        when (pagingData.loadState.refresh) {
            is LoadState.Loading -> {
                items(10) {
                    UserItemPlaceholder()
                }
            }

            is LoadState.NotLoading -> {
                items(count = pagingData.itemCount, key = { it }) { index ->
                    val item = pagingData[index]
                    UserItem(
                        username = item?.login.orEmpty(),
                        imageUrl = item?.avatarUrl.orEmpty(),
                        onUserClicked = {
                            onUserClicked(item?.login.orEmpty())
                        },
                        onLinkClicked = {
                            onLinkClicked(item?.htmlUrl.orEmpty())
                        },
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
                            modifier = Modifier.size(300.dp),
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
                        color = Color.Blue,
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

@Composable
private fun LazyColumnLayout(content: LazyListScope.() -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 10.dp),
    ) {
        content()
    }
}