package presentation.happyHourDetailScreen

import domain.model.HappyHourChapter

sealed interface HappyHourDetailScreenState {
    data object Loading : HappyHourDetailScreenState
    data class Loaded(val model: HappyHourDetailScreenStateModel) : HappyHourDetailScreenState
}

data class HappyHourDetailScreenStateModel(
    val id: Int,
    val title: String,
    val part: Int,
    val dateString: String,
    val videoId: String,
    val chapters: List<HappyHourChapter>
)
