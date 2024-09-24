import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.happyHourListScreen.HappyHourListScreen
import theme.colorScheme
import theme.shapes

@Composable
@Preview
fun App() {
    MaterialTheme(
        shapes = shapes,
        colors = colorScheme
    ) {
        Navigator(HappyHourListScreen())
    }
}