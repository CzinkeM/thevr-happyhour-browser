package data

import data.offline.HappyHourDatabase
import domain.mapper.toHappyHourVideo
import domain.mapper.toHappyHourVideoEntity
import domain.mapper.toHappyHourVideoList
import domain.model.HappyHourVideo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import networking.HappyHourHttpClient
import networking.dto.HappyHourPageDto

class HappyHourRepository(
    private val happyHourHttpClient: HappyHourHttpClient,
    private val happyHourDatabase: HappyHourDatabase
) {
    suspend fun sync(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        withContext(dispatcher) {
            syncProgress.update { true }
            val cachedLatestHappyHourPartNumber = happyHourDatabase.getDao().latest() ?: 0
            val syncedLatestHappyHourPartNumber =
                happyHourHttpClient.loadHappyHourPage("").hhVideos.maxBy { it.part ?: 0 }.part
            if (cachedLatestHappyHourPartNumber < (syncedLatestHappyHourPartNumber ?: 0)) {
                var targetPage = ""
                var isMoreHappyHourAvailable = true
                while (isMoreHappyHourAvailable) {
                    val dto = getHappyHoursByPage(targetPage)
                    if (dto.hhVideos.isEmpty()) {
                        isMoreHappyHourAvailable = false
                    } else {
                        targetPage = dto.page.toString()
                        dto.hhVideos.forEach { videoDto ->
                            happyHourDatabase.getDao().insert(videoDto.toHappyHourVideoEntity())
                        }
                    }
                }
            }
            syncProgress.update { false }
        }
    }

    fun happyHoursFlow(): Flow<List<HappyHourVideo>> {
        return happyHourDatabase.getDao().getAllAsFlow().map { it.toHappyHourVideoList() }
    }

    suspend fun happyHours(): List<HappyHourVideo> =
        happyHourDatabase.getDao().getAll().map { it.toHappyHourVideo() }

    private suspend fun getHappyHoursByPage(
        page: String,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): HappyHourPageDto {
        return withContext(dispatcher) {
            happyHourHttpClient.loadHappyHourPage(page)
        }
    }

    val syncProgress = MutableStateFlow(false)
}