package presentation.happyHourDetailScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
import presentation.components.HappyHourDateCard
import presentation.components.HappyHourVideoChapterCard
import presentation.components.TopologicalBackground

data class HappyHourDetailScreen(val happyHourId: Int): Screen {
    @OptIn(ExperimentalMaterialApi::class)
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
            TopologicalBackground(
                modifier = Modifier.fillMaxSize()
            )
            AnimatedContent(state) { s ->
                when(s) {
                    is HappyHourDetailScreenState.Loaded -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                        ) {
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 16.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                                    ) {
                                        //FIXME: the image does not behave as expected when rotated
                                        CoilImage(
                                            modifier = Modifier,
                                            imageModel = {
                                                HappyHourUrlProvider.youtubeThumbnailUrl(s.model.videoId)
                                            },
                                        )
                                    }
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                                        backgroundColor = MaterialTheme.colors.surface.copy(alpha = .65f)
                                    ) {
                                        Column {
                                            Text(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(8.dp),
                                                text = s.model.title,
                                                fontSize = 24.sp,
                                                overflow = TextOverflow.Ellipsis,
                                                textAlign = TextAlign.Start,
                                                fontWeight = FontWeight.SemiBold,
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 8.dp),
                                            ) {
                                                Text(
                                                    modifier = Modifier
                                                        .align(Alignment.CenterStart),
                                                    text = "#${s.model.part}",
                                                    maxLines = 1,
                                                    fontSize = 18.sp,
                                                    overflow = TextOverflow.Ellipsis,
                                                    textAlign = TextAlign.Center,
                                                )
                                                HappyHourDateCard(
                                                    modifier = Modifier
                                                        .align(Alignment.CenterEnd),
                                                    dateString = s.model.dateString,
                                                )
                                            }
                                            Spacer(modifier = Modifier.size(8.dp))
                                        }
                                    }
                                }
                            }
                            item {
                                var isChaptersExpanded by remember {
                                    mutableStateOf(false)
                                }

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .animateContentSize(),
                                    shape = RoundedCornerShape(8.dp),
                                    border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                                    backgroundColor = MaterialTheme.colors.surface.copy(alpha = .65f),
                                    onClick = {
                                        isChaptersExpanded = !isChaptersExpanded
                                    }
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .padding(4.dp)
                                                .height(56.dp)
                                                .fillMaxWidth()
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .align(Alignment.CenterStart),
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Bookmark,
                                                    contentDescription = null,
                                                )
                                                Spacer(modifier = Modifier.size(4.dp))
                                                Text(
                                                    modifier = Modifier,
                                                    text = "Chapters",
                                                    maxLines = 1,
                                                    fontSize = 18.sp,
                                                    overflow = TextOverflow.Ellipsis,
                                                    textAlign = TextAlign.Center,
                                                )
                                            }

                                            Text(
                                                modifier = Modifier
                                                    .align(Alignment.CenterEnd)
                                                    .padding(horizontal = 4.dp),
                                                text = "${s.model.chapters.size}",
                                                maxLines = 1,
                                                fontSize = 18.sp,
                                                overflow = TextOverflow.Ellipsis,
                                                textAlign = TextAlign.Center,
                                            )
                                            AnimatedContent(
                                                modifier = Modifier.align(Alignment.BottomCenter),
                                                targetState = isChaptersExpanded
                                            ) { isExpanded ->
                                                when(isExpanded) {
                                                    true -> Icon(
                                                        imageVector = Icons.Default.ExpandLess,
                                                        contentDescription = null
                                                    )
                                                    false -> Icon(
                                                        imageVector = Icons.Default.ExpandMore,
                                                        contentDescription = null
                                                    )
                                                }
                                            }
                                        }
                                        AnimatedVisibility(isChaptersExpanded) {
                                            Column(
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                s.model.chapters.forEach { chapter ->
                                                    HappyHourVideoChapterCard(
                                                        modifier = Modifier.padding(horizontal = 8.dp),
                                                        state = chapter,
                                                    )
                                                }
                                            }
                                        }
                                    }

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