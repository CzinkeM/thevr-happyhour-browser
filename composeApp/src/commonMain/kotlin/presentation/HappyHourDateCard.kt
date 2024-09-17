package presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import thevr_happyhour_browser.composeapp.generated.resources.Res
import thevr_happyhour_browser.composeapp.generated.resources.icon_calendar

@Composable
fun HappyHourDateCard(
    modifier: Modifier = Modifier,
    dateString: String,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            Modifier.padding(horizontal = 4.dp)
        ) {
            Icon(
                modifier = Modifier.scale(.9f).align(CenterVertically),
                painter = painterResource(resource = Res.drawable.icon_calendar),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                text = dateString,
                textAlign = TextAlign.Center,
            )
        }
    }
}