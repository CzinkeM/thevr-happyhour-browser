package presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HappyHourList(
    modifier: Modifier = Modifier,
    state: HappyHourListState,
    onHappyHourCardClick: (HappyHourCardState) -> Unit,
    onSearchFabClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
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
                    onCardClick = { onHappyHourCardClick(it) }
                )
            }
        }
//        AnimatedVisibility(
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(16.dp),
//            visible = isListFiltered
//        ) {
//            FloatingActionButton(
//                onClick = onClearFilterClick,
//                containerColor = MaterialTheme.colorScheme.secondary,
//                contentColor = MaterialTheme.colorScheme.primary,
//            ) {
//                Row(
//                    modifier = Modifier
//                        .padding(16.dp),
//                ) {
//                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
//                    Spacer(modifier = Modifier.size(8.dp))
//                    Text(text = "Clear Search")
//                }
//            }
//        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = onSearchFabClick,
            // TODO: Add coloring
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
    }
}

data class HappyHourListState(
    val happyHourList: List<HappyHourCardState>
) {
    companion object {
        val test = listOf(
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
            HappyHourCardState(
                id = "1",
                title = "Első",
                part = 1,
                publishDate = "2024-02-31"
            ),
        )
    }
}
