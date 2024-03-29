package presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmm.githubkmm.MR
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun UserItem(
    username: String,
    imageUrl: String,
    onUserClicked: () -> Unit,
    onLinkClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .clickable(onClick = onUserClicked)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        KamelImage(
            modifier = Modifier.size(100.dp, 150.dp).clip(RoundedCornerShape(8.dp)),
            resource = asyncPainterResource(imageUrl),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            onLoading = {
                Box(modifier = Modifier.fillMaxSize().background(color = Color.Gray))
            }
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 4.dp,
                alignment = Alignment.CenterVertically,
            ),
        ) {
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = username,
                fontSize = 14.sp,
                fontFamily = fontFamilyResource(MR.fonts.Poppins.semiBold),
            )

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable(onClick = onLinkClicked)
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = stringResource(MR.strings.title_link),
                    fontSize = 14.sp,
                    color = Color.Blue,
                    fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
                )
                Image(
                    modifier = Modifier.size(14.dp),
                    painter = painterResource(MR.images.ic_open_link),
                    contentDescription = null,
                )
            }
        }
    }
}