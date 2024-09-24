package domain.model

import kotlinx.datetime.LocalDate

data class HappyHourVideo(
    val id: Int,
    val part: Int,
    val title: String,
    val videoId: String,
    val chapters: List<HappyHourChapter>,
    val publishedDate: LocalDate
)
