import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.MainScreen
import theme.colorScheme
import theme.shapes

@Composable
@Preview
fun App() {
    MaterialTheme(
        shapes = shapes,
        colors = colorScheme
    ) {
        MainScreen(modifier = Modifier.fillMaxSize())
    }
}