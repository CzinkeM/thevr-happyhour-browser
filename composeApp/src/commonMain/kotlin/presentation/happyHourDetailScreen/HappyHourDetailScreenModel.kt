package presentation.happyHourDetailScreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.HappyHourRepository
import domain.HappyHourTitleFormatter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HappyHourDetailScreenModel(
    repository: HappyHourRepository,
) : ScreenModel {
    private var allHappyHours =
        repository.happyHoursFlow().stateIn(screenModelScope, SharingStarted.Eagerly, emptyList())
    private val selectedHappyHourId = MutableStateFlow<Int?>(null)

    val selectedHappyHour =
        combine(allHappyHours, selectedHappyHourId) { happyHours, selectedHappyHourId ->
            val selectedHappyHour = happyHours.firstOrNull { it.id == selectedHappyHourId }
            if (selectedHappyHour == null) {
                HappyHourDetailScreenState.Loading
            } else {
                println("${selectedHappyHour.part}: ${selectedHappyHour.chapters.size}")
                HappyHourDetailScreenState.Loaded(
                    model = HappyHourDetailScreenStateModel(
                        id = selectedHappyHour.id,
                        title = HappyHourTitleFormatter.format(selectedHappyHour.title),
                        part = selectedHappyHour.part,
                        dateString = selectedHappyHour.publishedDate.toString(),
                        videoId = selectedHappyHour.videoId,
                        chapters = selectedHappyHour.chapters
                    )
                )
            }
        }.stateIn(
            screenModelScope,
            SharingStarted.WhileSubscribed(3000),
            HappyHourDetailScreenState.Loading
        )

    fun setSelectedHappyHour(happyHourId: Int?) {
        screenModelScope.launch {
            selectedHappyHourId.update {
                happyHourId
            }
        }
    }

    override fun onDispose() {
        screenModelScope.launch {
            selectedHappyHourId.update { null }
        }
        super.onDispose()
    }
}