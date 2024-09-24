package networking

import domain.TimestampProvider

object HappyHourUrlProvider {
    private const val BASE_URL = "https://thevr.hu/thevrapps/"
    private const val YOUTUBE_BASE_URL = "https://www.youtube.com/watch"
    private const val thumbnailEndpointTemplate = "https://img.youtube.com/vi/%s/0.jpg"

    const val HAPPY_HOURS = "$BASE_URL/HappyHour/ajax.hhvideos.php"

    fun youtubeThumbnailUrl(videoId: String): String {
        return thumbnailEndpointTemplate.replace("%s",videoId)
    }

    fun youtubeVideoUrl(videoId: String): String {
        return "$YOUTUBE_BASE_URL?v=$videoId"
    }

    fun youtubeChapterUrl(timestampString: String, videoId: String): String {
        val timeInDecimals = TimestampProvider.timestampStringToSecond(timestampString)
        return "$YOUTUBE_BASE_URL?v=$videoId&t=${timeInDecimals}s"
    }
}