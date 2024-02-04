package presentation.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.kmm.githubkmm.MR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import presentation.detail.follows.FollowType
import presentation.detail.follows.FollowersScreen
import presentation.detail.follows.FollowingScreen
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
    val pagerState = rememberPagerState(pageCount = { tabsData.size })
    val coroutineScope = rememberCoroutineScope()

    val getDetail by detailViewModel.getDetail.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        detailViewModel.setUsernameDetail(username)
    }

    Column {
        when (val uiState = getDetail) {
            is UiState.Loading -> DetailContentPlaceholder()

            is UiState.Success -> DetailContent(
                data = uiState.data,
                onNavigateBack = onNavigateBack,
            )

            is UiState.Error -> DetailContentError(onNavigateBack = onNavigateBack)
        }

        TabHeader(tabsData = tabsData, pagerState = pagerState, coroutineScope = coroutineScope)

        TabContent(
            pagerState = pagerState,
            onPager = { position ->
                when (position) {
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
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TabHeader(
    tabsData: List<String>,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier,
) {
    TabRow(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = colorResource(MR.colors.darkGrey),
        contentColor = Color.White,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
            )
        },
    ) {
        tabsData.forEachIndexed { index, element ->
            Tab(
                modifier = Modifier.fillMaxWidth(),
                selected = pagerState.currentPage == index,
                text = {
                    Text(
                        element,
                        fontFamily = fontFamilyResource(MR.fonts.Poppins.medium),
                        fontSize = 12.sp,
                    )
                },
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                selectedContentColor = Color.White,
                unselectedContentColor = colorResource(MR.colors.grey),
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TabContent(
    pagerState: PagerState,
    onPager: @Composable (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    HorizontalPager(modifier = modifier, state = pagerState) { _ ->
        onPager(pagerState.currentPage)
    }
}