package presentation.detail.repositories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmm.githubkmm.MR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import domain.model.RepositoryItemModel

@Composable
fun RepositoryItem(
    repositoryItemModel: RepositoryItemModel?,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(16.dp)) {
        Row {
            Text(
                text = repositoryItemModel?.name.orEmpty(),
                fontFamily = fontFamilyResource(MR.fonts.Poppins.medium),
                fontSize = 16.sp,
            )

            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Black)
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                text = repositoryItemModel?.visibility.orEmpty(),
                fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
                fontSize = 9.sp,
                color = colorResource(MR.colors.grey),
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(repositoryItemModel?.color ?: Color.Blue)
            )

            Text(
                modifier = Modifier.padding(start = 6.dp),
                text = repositoryItemModel?.language.orEmpty(),
                fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
                fontSize = 12.sp,
                color = colorResource(MR.colors.darkGrey),
            )
        }
    }
}