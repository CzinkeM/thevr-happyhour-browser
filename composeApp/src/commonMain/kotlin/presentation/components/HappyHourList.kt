package presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

sealed interface HappyHourListEvent {
    data object OnSearchFabClick : HappyHourListEvent
    data class OnHappyHourCardClick(val id: Int) : HappyHourListEvent
}

@Composable
fun HappyHourList(
    modifier: Modifier = Modifier,
    state: HappyHourListState,
    onEvent: (HappyHourListEvent) -> Unit,
//    onHappyHourCardClick: (HappyHourCardState) -> Unit,
//    onSearchFabClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        TopologicalBackground(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            items(state.happyHourList) {
                HappyHourCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 8.dp),
                    state = it,
                    onEvent = { event ->
                        when (event) {
                            is HappyHourCardEvent.OnCardClick -> onEvent(
                                HappyHourListEvent.OnHappyHourCardClick(
                                    event.id
                                )
                            )
                        }
                    },
                )
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            backgroundColor = MaterialTheme.colors.primary,
            onClick = {
                onEvent(HappyHourListEvent.OnSearchFabClick)
            },
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
    }
}

data class HappyHourListState(
    val happyHourList: List<HappyHourCardState>
)
