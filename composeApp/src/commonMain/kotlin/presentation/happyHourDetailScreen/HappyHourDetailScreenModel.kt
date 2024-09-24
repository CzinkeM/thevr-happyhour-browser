package presentation.happyHourDetailScreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.HappyHourRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HappyHourDetailScreenModel(
    repository: HappyHourRepository,
): ScreenModel {

    //FIXME: happyhours list should be read from some kind of cache.
    private var allHappyHours = repository.syncHappyHours().stateIn(screenModelScope, SharingStarted.Eagerly, emptyList())
    private val selectedHappyHourId = MutableStateFlow<Int?>(null)

    val selectedHappyHour = combine(allHappyHours, selectedHappyHourId) { happyHours, selectedHappyHourId ->
        val selectedHappyHour = happyHours.firstOrNull { it.id == selectedHappyHourId }
        if (selectedHappyHour == null) {
            HappyHourDetailScreenState.Loading
        }else{
            HappyHourDetailScreenState.Loaded(
                model = HappyHourDetailScreenStateModel(
                    id = selectedHappyHour.id,
                    title = selectedHappyHour.title,
                    part = selectedHappyHour.part,
                    dateString = selectedHappyHour.publishedDate.toString(),
                    videoId = selectedHappyHour.videoId,
                )
            )
        }
    }.stateIn(screenModelScope, SharingStarted.WhileSubscribed(3000),HappyHourDetailScreenState.Loading)

    fun setSelectedHappyHour(happyHourId: Int?) {
        screenModelScope.launch {
            selectedHappyHourId.update {
                happyHourId
            }
        }
    }

    override fun onDispose() {
        println("Dispose runs")
        screenModelScope.launch {
            selectedHappyHourId.update { null }
        }
        super.onDispose()
    }
}