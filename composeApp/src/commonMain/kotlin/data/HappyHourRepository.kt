package data

import domain.mapper.toHappyHourVideoList
import domain.model.HappyHourVideo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import networking.HappyHourHttpClient
import presentation.HappyHourCardState

class HappyHourRepository(
    private val happyHourHttpClient: HappyHourHttpClient
) {
    suspend fun getHappyHoursByPage(page: Int, dispatcher: CoroutineDispatcher = Dispatchers.IO): List<HappyHourVideo> {
        return withContext(dispatcher) {
            happyHourHttpClient.loadHappyHourPage(page).toHappyHourVideoList()
        }
    }
    fun testHappyHours() = listOf(
        HappyHourCardState(
            id = 1,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
        HappyHourCardState(
            id = 2,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
        HappyHourCardState(
            id = 3,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
        HappyHourCardState(
            id = 4,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
        HappyHourCardState(
            id = 5,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
        HappyHourCardState(
            id = 6,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
        HappyHourCardState(
            id = 7,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
        HappyHourCardState(
            id = 8,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
        HappyHourCardState(
            id = 9,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
        HappyHourCardState(
            id = 10,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
        HappyHourCardState(
            id = 11,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
        HappyHourCardState(
            id = 12,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
        HappyHourCardState(
            id = 13,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
        HappyHourCardState(
            id = 14,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
        HappyHourCardState(
            id = 15,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
        HappyHourCardState(
            id = 16,
            title = "Első",
            part = 1,
            publishDate = "2024-02-31"
        ),
    )
}