package presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import domain.AppLauncher
import networking.HappyHourUrlProvider
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun YoutubeCard(
    modifier: Modifier = Modifier,
    videoId: String,
) {
    val appLauncher = koinInject<AppLauncher>()
    Card(
        modifier = modifier,
        onClick = {
            appLauncher.launchApp(uri = HappyHourUrlProvider.youtubeVideoUrl(videoId))
        },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = MaterialTheme.colors.primary,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(4.dp),
        ) {
            Icon(
                modifier = Modifier.align(Alignment.CenterStart),
                imageVector = Icons.Default.PlayCircle,
                contentDescription = null
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Start On Youtube",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}