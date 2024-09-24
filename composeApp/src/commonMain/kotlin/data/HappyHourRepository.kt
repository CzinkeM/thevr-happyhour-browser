package data

import domain.mapper.toHappyHourVideoList
import domain.model.HappyHourVideo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext
import networking.HappyHourHttpClient
import networking.dto.HappyHourPageDto

class HappyHourRepository(
    private val happyHourHttpClient: HappyHourHttpClient
) {
    private suspend fun getHappyHoursByPage(page: String, dispatcher: CoroutineDispatcher = Dispatchers.IO): HappyHourPageDto {
        return withContext(dispatcher) {
            happyHourHttpClient.loadHappyHourPage(page)
        }
    }


    fun syncHappyHours(): Flow<List<HappyHourVideo>> {
        return channelFlow {
            var targetPage = ""
            var isMoreHappyHourAvailable = true
            val hhList = mutableListOf<HappyHourVideo>()
            while (isMoreHappyHourAvailable) {
                val dto = getHappyHoursByPage(
                    page = targetPage,
                )
                if(dto.hhVideos.isEmpty()) {
                    isMoreHappyHourAvailable = false
                } else {
                    targetPage = dto.page.toString()
                    hhList.addAll(dto.toHappyHourVideoList())
                    send(hhList)
                }
            }
        }
    }
}