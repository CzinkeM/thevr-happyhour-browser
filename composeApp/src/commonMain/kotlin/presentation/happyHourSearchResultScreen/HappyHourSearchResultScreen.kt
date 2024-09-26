package presentation.happyHourSearchResultScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.model.SearchParameter
import presentation.components.HappyHourCard
import presentation.components.HappyHourCardEvent
import presentation.components.TopologicalBackground
import presentation.happyHourDetailScreen.HappyHourDetailScreen
import presentation.components.LoadingCard

class HappyHourSearchResultScreen(
    private val searchParameter: SearchParameter
) : Screen {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = navigator.getNavigatorScreenModel<HappyHourSearchResultScreenModel>()
        val screenState by screenModel.screenState.collectAsState()

        LaunchedEffect(Unit) {
            screenModel.search(searchParameter)
        }

        Surface {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                TopologicalBackground(modifier = Modifier.fillMaxSize())
                AnimatedContent(
                    modifier = Modifier.fillMaxSize().align(Alignment.Center),
                    targetState = screenState
                ) { state ->
                    when (state) {
                        HappyHourSearchResultScreenState.EmptySearchResult -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {

                                Card {
                                    Column(
                                        modifier = Modifier.padding(16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.SearchOff,
                                            contentDescription = null,
                                            tint = MaterialTheme.colors.primary
                                        )
                                        Text("No result!")
                                    }
                                }
                            }
                        }

                        HappyHourSearchResultScreenState.IsSearching -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                LoadingCard(
                                    message = "Loading!"
                                )
                            }
                        }

                        is HappyHourSearchResultScreenState.SearchResult -> {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                stickyHeader {
                                    TopAppBar(
                                        modifier = Modifier.fillMaxWidth().height(56.dp),
                                        backgroundColor = MaterialTheme.colors.background,
                                        navigationIcon = {
                                            IconButton(
                                                onClick = {
                                                    navigator.pop()
                                                }
                                            ) {
                                                Icon(
                                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                    contentDescription = null
                                                )
                                            }
                                        },
                                        title = {
                                            Text("Result for: ${searchParameter.searchInfo()}")
                                        }
                                    )
                                }
                                items(state.result) { videoCardState ->
                                    HappyHourCard(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp)
                                            .padding(bottom = 8.dp),
                                        state = videoCardState,
                                        onEvent = { event ->
                                            when (event) {
                                                is HappyHourCardEvent.OnCardClick -> navigator.push(
                                                    HappyHourDetailScreen(event.id)
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

            }

        }
    }
}