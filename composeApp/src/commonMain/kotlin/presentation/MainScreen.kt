package presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        HappyHourList(
            modifier = Modifier.fillMaxSize(),
            state = HappyHourListState(
                happyHourList = HappyHourListState.test
            ),
            onHappyHourCardClick = {

            },
            onSearchFabClick = {

            }
        )
    }
}