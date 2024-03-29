package presentation.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmm.githubkmm.MR
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.stringResource
import domain.model.DetailModel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import utils.orHyphen

@Composable
fun DetailContent(
    data: DetailModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = onNavigateBack,
                content = {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            )

            Text(
                text = data.login.orEmpty(),
                fontFamily = fontFamilyResource(MR.fonts.Poppins.medium),
                fontSize = 14.sp,
            )
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)) {
                KamelImage(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(border = BorderStroke(1.dp, Color.Black), CircleShape),
                    resource = asyncPainterResource(data.avatarUrl.orEmpty()),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    onLoading = {
                        Box(modifier = Modifier.fillMaxSize().background(color = Color.Gray))
                    },
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(MR.strings.repositories),
                            fontFamily = fontFamilyResource(MR.fonts.Poppins.medium),
                            fontSize = 14.sp,
                        )

                        Text(
                            text = data.publicRepos.toString(),
                            fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
                            fontSize = 14.sp,
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(MR.strings.followers),
                            fontFamily = fontFamilyResource(MR.fonts.Poppins.medium),
                            fontSize = 14.sp,
                        )

                        Text(
                            text = data.followers.toString(),
                            fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
                            fontSize = 14.sp,
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(MR.strings.following),
                            fontFamily = fontFamilyResource(MR.fonts.Poppins.medium),
                            fontSize = 14.sp,
                        )

                        Text(
                            text = data.following.toString(),
                            fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
                            fontSize = 14.sp,
                        )
                    }
                }
            }

            Text(
                text = data.name.orEmpty(),
                fontFamily = fontFamilyResource(MR.fonts.Poppins.semiBold),
                fontSize = 16.sp,
            )

            Row {
                Text(
                    text = stringResource(MR.strings.bio),
                    fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
                    fontSize = 12.sp,
                )

                Text(
                    text = data.bio.orHyphen(),
                    fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Row {
                Text(
                    text = stringResource(MR.strings.company),
                    fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
                    fontSize = 12.sp,
                )

                Text(
                    text = data.company.orHyphen(),
                    fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}