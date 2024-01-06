package presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.cash.paging.compose.collectAsLazyPagingItems
import com.kmm.githubkmm.MR
import dev.icerock.moko.resources.compose.stringResource
import org.koin.compose.koinInject

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinInject()) {
    val getUsersPagingData = homeViewModel.getUsers.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(MR.strings.app_name_ui),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = stringResource(MR.strings.app_description),
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(start = 20.dp, top = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 20.dp),
        ) {
            items(getUsersPagingData.itemCount) { index ->
                val item = getUsersPagingData[index]
                UserItem(
                    imageUrl = item?.avatarUrl.orEmpty(),
                    name = item?.login.orEmpty(),
                    url = item?.htmlUrl.orEmpty()
                )
            }
        }
    }
}