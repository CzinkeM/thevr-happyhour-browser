package domain.model

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import kotlinx.datetime.LocalDate

data class HappyHourVideo(
    val id: Int,
    val part: Int,
    val title: String,
    val videoId: String,
    val chapters: List<HappyHourChapter>,
    val publishedDate: LocalDate
) {
    val searchString = "$part ${title.toLowerCase(Locale.current)} ${chapters.joinToString(" ") { it.title.toLowerCase(
        Locale.current) }} $publishedDate"
}
