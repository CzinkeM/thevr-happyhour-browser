package networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HappyHourVideoDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("part")
    val part: Int? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("videoId")
    val videoId: String? = null,
    @SerialName("videoCoverImg")
    val videoCoverImg: String? = null,
    @SerialName("timeStampText")
    val timeStampText: String? = null,
    @SerialName("publishedDate")
    val publishedDate: String? = null
)
