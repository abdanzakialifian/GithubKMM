package presentation.detail.repositories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import presentation.component.shimmer

@Composable
fun RepositoryItemPlaceholder(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Row {
            Box(
                modifier = Modifier.size(150.dp, 16.dp).clip(RoundedCornerShape(4.dp)).shimmer()
            )

            Box(
                modifier = Modifier
                    .size(40.dp, 14.dp)
                    .padding(start = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .shimmer()
            )
        }

        Row(
            modifier = Modifier.padding(top = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .shimmer()
            )

            Box(
                modifier = Modifier
                    .size(80.dp, 12.dp)
                    .padding(start = 6.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmer()
            )
        }
    }
}