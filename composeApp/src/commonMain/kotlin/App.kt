import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import di.appModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import presentation.MainScreen
import theme.colorScheme
import theme.shapes

@Composable
@Preview
fun App() {
    initKoin()
    MaterialTheme(
        shapes = shapes,
        colors = colorScheme
    ) {
        Navigator(MainScreen())
    }
}

private fun initKoin() {
    startKoin {
        modules(appModule)
    }
}