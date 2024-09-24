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
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import domain.AppLauncher
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SpotifyCard(
    modifier: Modifier = Modifier,
) {
    val appLauncher = koinInject<AppLauncher>()
    Card(
        modifier = modifier,
        onClick = {
            appLauncher.launchApp(uri = "https://open.spotify.com/show/2TViVtEtC5NjM1xEwkXK0c")
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
                imageVector = Icons.Default.Headphones,
                contentDescription = null
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Find on Spotify",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}