package presentation.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmm.githubkmm.MR
import dev.icerock.moko.resources.compose.stringResource
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import presentation.detail.followers.FollowersScreen
import presentation.detail.following.FollowingScreen
import presentation.detail.repositories.RepositoriesScreen
import utils.UiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(username: String, detailViewModel: DetailViewModel, onNavigateBack: () -> Unit) {
    val tabsData = listOf(
        stringResource(MR.strings.repositories),
        stringResource(MR.strings.followers),
        stringResource(MR.strings.following),
    )
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabsData.size })
    val coroutineScope = rememberCoroutineScope()

    val getDetail by detailViewModel.getDetail.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        detailViewModel.setUsername(username)
    }

    when (val uiState = getDetail) {
        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> DetailContent(
            data = uiState.data,
            tabsData = tabsData,
            pagerState = pagerState,
            coroutineScope = coroutineScope,
            onNavigateBack = onNavigateBack,
            onPager = { index ->
                when (index) {
                    0 -> RepositoriesScreen(
                        detailViewModel = detailViewModel,
                        username = username,
                    )

                    1 -> FollowersScreen(
                        detailViewModel = detailViewModel,
                        username = username,
                        type = FollowType.FOLLOWERS.type,
                    )

                    2 -> FollowingScreen(
                        detailViewModel = detailViewModel,
                        username = username,
                        type = FollowType.FOLLOWING.type,
                    )
                }
            }
        )

        is UiState.Error -> {}
    }
}