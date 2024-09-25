package presentation.happyHourSearchResultScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.model.SearchParameter

class HappyHourSearchResultScreen(
    private val searchParameter: SearchParameter
): Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = navigator.getNavigatorScreenModel<HappyHourSearchResultScreenModel>()

        Surface {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                when(searchParameter) {
                    is SearchParameter.DateSearchParameter -> Text("Search parameters: ${searchParameter.date}")
                    is SearchParameter.PartNumberSearchParameter -> Text("Search parameters: ${searchParameter.partNumber}")
                    is SearchParameter.TextSearchParameter -> Text("Search parameters: ${searchParameter.searchedText}")
                }

            }
        }
    }
}