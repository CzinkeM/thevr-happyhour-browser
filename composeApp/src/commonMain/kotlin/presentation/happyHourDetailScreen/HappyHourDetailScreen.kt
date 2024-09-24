package presentation.happyHourDetailScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.skydoves.landscapist.coil3.CoilImage
import networking.HappyHourUrlProvider

data class HappyHourDetailScreen(val happyHourId: Int): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = navigator.getNavigatorScreenModel<HappyHourDetailScreenModel>()

        val state by screenModel.selectedHappyHour.collectAsState()

        DisposableEffect(true) {
            screenModel.setSelectedHappyHour(happyHourId)
            onDispose {
                screenModel.setSelectedHappyHour(null)
            }
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            AnimatedContent(state) { s ->
                when(s) {
                    is HappyHourDetailScreenState.Loaded -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                        ) {
                            item {
                                Column {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                                    ) {
                                        CoilImage(
                                            modifier = Modifier,
                                            imageModel = {
                                                HappyHourUrlProvider.youtubeThumbnailUrl(s.model.videoId)
                                            },
                                        )
                                    }
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                horizontal = 16.dp,
                                            ),
                                        text = s.model.title,
                                        fontSize = 24.sp,
                                        overflow = TextOverflow.Ellipsis,
                                        textAlign = TextAlign.Center,
                                    )
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                horizontal = 16.dp,
                                            ),
                                        text = s.model.dateString,
                                        maxLines = 1,
                                        fontSize = 16.sp,
                                        overflow = TextOverflow.Ellipsis,
                                        textAlign = TextAlign.Center,
                                        fontStyle = FontStyle.Italic,
                                    )
                                    Spacer(modifier = Modifier.size(8.dp))
                                }
                            }
                            item {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    shape = RoundedCornerShape(8.dp),
                                    border = BorderStroke(1.dp,MaterialTheme.colors.primary),
                                    contentColor = MaterialTheme.colors.primary,
                                    backgroundColor = Color.Transparent,
                                ) {
                                    // FIXME:  
            //                        for (chapter in happyHour.chapters) {
            //                            ChapterCard(
            //                                modifier = Modifier
            //                                    .padding(
            //                                        horizontal = 16.dp,
            //                                        vertical = 8.dp
            //                                    ),
            //                                chapterState = chapter,
            //                            )
            //                        }
                                }
                            }
                        }
                    }
                    HappyHourDetailScreenState.Loading -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    }
                }
            }
        }
    }
}