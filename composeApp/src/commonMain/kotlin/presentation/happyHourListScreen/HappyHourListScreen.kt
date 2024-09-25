package presentation.happyHourListScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import presentation.components.HappyHourList
import presentation.components.HappyHourListEvent
import presentation.components.HappyHourListState
import presentation.components.HappyHourSearchDialog
import presentation.happyHourDetailScreen.HappyHourDetailScreen
import presentation.happyHourSearchResultScreen.HappyHourSearchResultScreen

class HappyHourListScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = navigator.getNavigatorScreenModel<HappyHourListScreenModel>()

        val happyHours by screenModel.happyHours.collectAsState()

        var isSearchDialog by remember {
            mutableStateOf(false)
        }

        AnimatedVisibility(isSearchDialog) {
            HappyHourSearchDialog(
                modifier = Modifier
                    .fillMaxWidth(.9f),
                onDismissRequest = {
                    isSearchDialog = false
                },
                onClickSearch = { parameter ->
                    navigator.push(HappyHourSearchResultScreen(parameter))
                }
            )
        }

        Surface(modifier = Modifier) {
            HappyHourList(
                modifier = Modifier.fillMaxSize(),
                state = HappyHourListState(
                    happyHourList = happyHours
                ),
                onEvent = { event ->
                    when(event) {
                        is HappyHourListEvent.OnHappyHourCardClick -> navigator.push(HappyHourDetailScreen(event.id))
                        HappyHourListEvent.OnSearchFabClick -> {
                            isSearchDialog = true
                        }
                    }
                }
            )
        }
    }
}