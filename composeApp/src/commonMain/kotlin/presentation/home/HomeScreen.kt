package presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import app.cash.paging.compose.itemKey
import com.kmm.githubkmm.MR
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.stringResource
import domain.model.UserItemModel
import org.koin.compose.koinInject
import presentation.component.SearchBar

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinInject()) {
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

        UsersPagingState(getUsersPagingData, localUriHandler)
    }
}

@Composable
private fun UsersPagingState(
    pagingData: LazyPagingItems<UserItemModel>,
    localUriHandler: UriHandler,
) {
    when (pagingData.loadState.refresh) {
        is LoadState.Loading -> {
            LazyColumnLayout {
                items(10) {
                    UserItemPlaceholder()
                }
            }
        }

        is LoadState.NotLoading -> {
            LazyColumnLayout {
                items(
                    pagingData.itemCount,
                    key = pagingData.itemKey { it.id ?: 0 },
                ) { index ->
                    val item = pagingData[index]
                    UserItem(
                        imageUrl = item?.avatarUrl.orEmpty(),
                        name = item?.login.orEmpty(),
                        onUserClicked = {},
                        onLinkClicked = {
                            localUriHandler.openUri(item?.htmlUrl.orEmpty())
                        },
                    )
                }
            }
        }

        is LoadState.Error -> {
            // TODO Need handle
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