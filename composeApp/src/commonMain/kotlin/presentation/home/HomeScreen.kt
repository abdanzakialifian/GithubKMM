package presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import app.cash.paging.compose.collectAsLazyPagingItems
import org.koin.compose.koinInject

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinInject()) {
    val getUsersPagingData = homeViewModel.getUsers.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(getUsersPagingData.itemCount) {
                val item = getUsersPagingData[it]
                Text(item?.login ?: "", style = TextStyle(fontSize = 50.sp))
            }
        }
    }
}