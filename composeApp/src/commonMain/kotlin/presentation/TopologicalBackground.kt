package presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import thevr_happyhour_browser.composeapp.generated.resources.Res
import thevr_happyhour_browser.composeapp.generated.resources.topological_background

@Composable
fun TopologicalBackground(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .blur(4.dp)
            .background(Black),
        painter = painterResource(resource = Res.drawable.topological_background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        alignment = Alignment.Center,
        colorFilter = ColorFilter.tint(Color.White)
    )
}