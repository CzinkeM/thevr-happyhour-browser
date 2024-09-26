package presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import domain.AppLauncher
import networking.HappyHourUrlProvider
import org.koin.compose.koinInject

data class HappyHourVideoChapterCardState(
    val title: String,
    val timestampString: String,
    val videoId: String
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HappyHourVideoChapterCard(
    modifier: Modifier = Modifier,
    state: HappyHourVideoChapterCardState,
) {
    val appLauncher = koinInject<AppLauncher>()
    Card(
        modifier = modifier,
        onClick = {
            appLauncher.launchApp(
                uri = HappyHourUrlProvider.youtubeChapterUrl(
                    timestampString = state.timestampString,
                    videoId = state.videoId
                )
            )
        },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = MaterialTheme.colors.primary,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.weight(0.7f),
                text = state.timestampString,
                color = MaterialTheme.colors.primary
            )
            Spacer(
                modifier = Modifier.weight(0.1f)
            )
            Text(
                modifier = Modifier.weight(0.1f),
                text = "-",
                color = MaterialTheme.colors.primary
            )
            Spacer(
                modifier = Modifier.weight(0.1f)
            )
            Text(
                modifier = Modifier.weight(2f),
                text = state.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.primary
            )
        }
    }
}