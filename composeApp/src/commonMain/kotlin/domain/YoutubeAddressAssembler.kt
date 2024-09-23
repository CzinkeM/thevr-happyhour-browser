package domain

object YoutubeAddressAssembler {

    private const val YOUTUBE_BASE_URL = "https://www.youtube.com/watch"

    fun assemble(timestampString: String, videoId: String): String {
        //FIXME: timeInDecimal should be decoded from the timestamp string (timestamp string look like something like: 1:12:32)
        val timeInDecimals = 0
        return "$YOUTUBE_BASE_URL/v=$videoId&t=${timeInDecimals}s"
    }
}