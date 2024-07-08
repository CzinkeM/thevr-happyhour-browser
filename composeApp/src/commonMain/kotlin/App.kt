import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.MainScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        MainScreen(modifier = Modifier.fillMaxSize())
    }
}