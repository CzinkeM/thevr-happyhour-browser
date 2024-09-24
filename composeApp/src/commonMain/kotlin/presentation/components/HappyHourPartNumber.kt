package presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.painterResource
import thevr_happyhour_browser.composeapp.generated.resources.Res
import thevr_happyhour_browser.composeapp.generated.resources.icon_coffee

@Composable
fun HappyHourPartNumber(
    modifier: Modifier = Modifier,
    partNumber: Int,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(resource = Res.drawable.icon_coffee),
            contentDescription = null
        )
        Text(
            text = "$partNumber",
            textAlign = TextAlign.Center,
        )
    }
}