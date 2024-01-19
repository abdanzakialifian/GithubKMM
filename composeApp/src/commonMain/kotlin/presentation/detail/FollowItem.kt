package presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.fontFamilyResource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import com.kmm.githubkmm.MR

@Composable
fun FollowItem(username: String, imageUrl: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        KamelImage(
            modifier = Modifier.size(60.dp).clip(CircleShape),
            resource = asyncPainterResource(imageUrl),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            onLoading = {
                Box(modifier = Modifier.fillMaxSize().background(color = Color.Gray))
            },
        )

        Text(
            text = username,
            fontFamily = fontFamilyResource(MR.fonts.Poppins.medium),
            fontSize = 14.sp,
        )
    }
}