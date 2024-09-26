package presentation.happyHourListScreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.HappyHourRepository
import domain.mapper.toHappyHourCardState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HappyHourListScreenModel(
    private val repository: HappyHourRepository,

) : ScreenModel {
    val syncProgress = repository.syncingInProgress.asStateFlow()
    val shouldShowDisclaimerDialog = repository.shouldShowDisclaimerDialog.stateIn(screenModelScope, SharingStarted.WhileSubscribed(3000), false)

    val happyHours = repository
        .happyHoursFlow()
        .map { happyHoursVideo ->
            happyHoursVideo.map { it.toHappyHourCardState() }
        }
        .stateIn(
            screenModelScope, SharingStarted.WhileSubscribed(3000), emptyList()
        )
    //TODO: Utilize onDispose function

    init {
        screenModelScope.launch(Dispatchers.IO) {
            repository.sync()
        }
    }

    fun stopShowingDisclaimerDialogOnStart() {
        screenModelScope.launch(Dispatchers.IO) {
            repository.doNotShowDisclaimerDialogOnStart()
        }
    }
}