package presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import thevr_happyhour_browser.composeapp.generated.resources.Res
import thevr_happyhour_browser.composeapp.generated.resources.icon_coffee

@Composable
fun HappyHourPartNumber(
    modifier: Modifier = Modifier,
    partNumber: Int,
) {
    Card(
        modifier = modifier,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ){
        Column(
            modifier = Modifier.padding(4.dp),
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
}