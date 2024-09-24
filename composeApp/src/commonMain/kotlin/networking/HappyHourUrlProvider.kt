package networking

object HappyHourUrlProvider {
    private const val BASE_URL = "https://thevr.hu/thevrapps/"
    const val HAPPY_HOURS = "$BASE_URL/HappyHour/ajax.hhvideos.php"

    private const val thumbnailEndpointTemplate = "https://img.youtube.com/vi/%s/0.jpg"

    fun youtubeThumbnailUrl(videoId: String): String {
        return thumbnailEndpointTemplate.replace("%s",videoId)
    }
}