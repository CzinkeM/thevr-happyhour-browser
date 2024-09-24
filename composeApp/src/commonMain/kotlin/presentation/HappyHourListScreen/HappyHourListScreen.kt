package presentation.HappyHourListScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import presentation.components.HappyHourList
import presentation.components.HappyHourListEvent
import presentation.components.HappyHourListState
import presentation.happyHourDetailScreen.HappyHourDetailScreen

class HappyHourListScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = navigator.getNavigatorScreenModel<HappyHourListScreenModel>()

        val happyHours by screenModel.happyHours.collectAsState()

        Surface(modifier = Modifier) {
            HappyHourList(
                modifier = Modifier.fillMaxSize(),
                state = HappyHourListState(
                    happyHourList = happyHours
                ),
                onEvent = { event ->
                    when(event) {
                        is HappyHourListEvent.OnHappyHourCardClick -> navigator.push(HappyHourDetailScreen(event.id))
                        HappyHourListEvent.OnSearchFabClick -> TODO()
                    }
                }
            )
        }
    }
}