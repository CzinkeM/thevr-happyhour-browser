package presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.HappyHourRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import networking.HappyHourHttpClient

class MainScreenModel(
    private val repository: HappyHourRepository,
    private val httpClient: HappyHourHttpClient,
): ScreenModel {

    private val _happyHours = MutableStateFlow(emptyList<HappyHourCardState>())
    val happyHours = _happyHours.asStateFlow()

    init {
        screenModelScope.launch {
            _happyHours.update {
                repository.getHappyHourList()
            }

            val hhPage = httpClient.loadHappyHourPage(8)
            println("HappyHour:$hhPage")
        }
    }

    override fun onDispose() {
        super.onDispose()
    }
}