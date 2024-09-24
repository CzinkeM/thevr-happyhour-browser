package presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

data class HappyHourCardState(
    val id: Int,
    val title: String,
    val part: Int,
    val publishDate: String
)

sealed interface HappyHourCardEvent {
    data class OnCardClick(val id: Int): HappyHourCardEvent
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HappyHourCard(
    modifier: Modifier = Modifier,
    state: HappyHourCardState,
    onEvent: (HappyHourCardEvent) -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = { onEvent(HappyHourCardEvent.OnCardClick(state.id)) },
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.surface.copy(.65f),
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
        ) {
            HappyHourPartNumber(
                modifier = Modifier.widthIn(max = 54.dp),
                partNumber = state.part
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = state.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                HappyHourDateCard(
                    modifier = Modifier.padding(top = 4.dp),
                    dateString = state.publishDate
                )
            }
        }
    }
}

