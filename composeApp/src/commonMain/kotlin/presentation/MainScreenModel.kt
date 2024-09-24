package presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.HappyHourRepository
import domain.mapper.toHappyHourCardState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainScreenModel(
    private val repository: HappyHourRepository,
): ScreenModel {

    val happyHours = repository
        .syncHappyHours()
        .map { happyHoursVideo ->
            happyHoursVideo.map { it.toHappyHourCardState() }
        }
        .stateIn(
        screenModelScope, SharingStarted.WhileSubscribed(3000), emptyList())
    //TODO: Utilize onDispose function
}