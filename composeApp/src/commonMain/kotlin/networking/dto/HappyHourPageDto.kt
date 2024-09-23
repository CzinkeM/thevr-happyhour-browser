package networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HappyHourPageDto(
    @SerialName("hh_videos")
    var hhVideos: ArrayList<HappyHourVideoDto> = arrayListOf(),
    @SerialName("page")
    var page: Int? = null
)