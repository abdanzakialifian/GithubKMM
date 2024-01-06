package presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmm.githubkmm.MR
import dev.icerock.moko.resources.compose.painterResource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun UserItem(imageUrl: String, name: String, url: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        KamelImage(
            modifier = Modifier.size(90.dp, 110.dp).clip(RoundedCornerShape(8.dp)),
            resource = asyncPainterResource(imageUrl),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = name, fontSize = 16.sp)
            TextButton(
                onClick = {

                },
                content = {
                    Row {
                        Text(text = "Github Link", fontSize = 16.sp, color = Color.Blue)
//                        Image(
//                            painter = painterResource(MR.images.ic_link),
//                            contentDescription = null
//                        )
                    }
                }
            )
        }
    }
}