package presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.HappyHourRepository
import domain.mapper.toHappyHourCardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenModel(
    private val repository: HappyHourRepository,
): ScreenModel {

    private val _happyHours = MutableStateFlow(emptyList<HappyHourCardState>())
    val happyHours = _happyHours.asStateFlow()

    init {
        screenModelScope.launch {
            _happyHours.update {
                with(repository.getHappyHoursByPage(0)) {
                    println("Current list size: ${this.size}")
                    this.map { it.toHappyHourCardState() }
                }
            }
        }
    }
    //TODO: Utilize onDispose function
}