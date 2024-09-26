package presentation.happyHourListScreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.HappyHourRepository
import domain.mapper.toHappyHourCardState
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import okio.IOException

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

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        when(exception) {
            is UnresolvedAddressException -> {
                println("There is no internet")
            }
            is ClientRequestException -> {
                println("Something went wring with client request")
            }
            is ServerResponseException -> {
                println("Something went wring with server response")
            }
            is IOException -> {
                println("IO Exception")
            }
            is SerializationException -> {
                println("Serialization Exception")
            }
            else -> {
                println(exception::class.simpleName)
            }
        }
    }

    init {
        screenModelScope.launch(exceptionHandler) {
            withContext(Dispatchers.IO) {
                repository.sync()
            }
        }
    }

    fun stopShowingDisclaimerDialogOnStart() {
        screenModelScope.launch(exceptionHandler) {
            withContext(Dispatchers.IO) {
                repository.doNotShowDisclaimerDialogOnStart()
            }
        }
    }
}