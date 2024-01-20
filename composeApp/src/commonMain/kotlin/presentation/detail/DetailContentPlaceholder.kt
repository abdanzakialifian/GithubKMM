package presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmm.githubkmm.MR
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.stringResource
import presentation.component.shimmer

@Composable
fun DetailContentPlaceholder(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {},
                content = {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            )

            Box(modifier = Modifier.size(70.dp, 14.dp).clip(RoundedCornerShape(4.dp)).shimmer())
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)) {
                Box(modifier = Modifier.size(80.dp).clip(CircleShape).shimmer())

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

                        Box(
                            modifier = Modifier
                                .size(40.dp, 14.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .shimmer()
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(MR.strings.followers),
                            fontFamily = fontFamilyResource(MR.fonts.Poppins.medium),
                            fontSize = 14.sp,
                        )

                        Box(
                            modifier = Modifier
                                .size(40.dp, 14.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .shimmer()
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(MR.strings.following),
                            fontFamily = fontFamilyResource(MR.fonts.Poppins.medium),
                            fontSize = 14.sp,
                        )

                        Box(
                            modifier = Modifier
                                .size(40.dp, 14.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .shimmer()
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .size(100.dp, 16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmer()
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(MR.strings.bio),
                    fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
                    fontSize = 12.sp,
                )

                Box(
                    modifier = Modifier
                        .size(40.dp, 12.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmer()
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(MR.strings.company),
                    fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
                    fontSize = 12.sp,
                )

                Box(
                    modifier = Modifier
                        .size(40.dp, 12.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmer()
                )
            }
        }
    }
}