package domain.mapper

import domain.YoutubeAddressAssembler
import domain.isNullOrMinus
import domain.model.HappyHourChapter
import domain.model.HappyHourVideo
import kotlinx.datetime.LocalDateTime
import networking.dto.HappyHourPageDto
import networking.dto.HappyHourVideoDto
import presentation.components.HappyHourCardState

fun HappyHourPageDto.toHappyHourVideoList(): List<HappyHourVideo> {
    return this.hhVideos.map { dto -> dto.toHappyHourVideo() }
}

fun HappyHourVideoDto.toHappyHourVideo(): HappyHourVideo {
    if(id.isNullOrMinus() || part.isNullOrMinus() || title.isNullOrBlank() || videoId.isNullOrBlank() || publishedDate.isNullOrBlank()) {
        throw IllegalArgumentException()
    }

    return HappyHourVideo(
        id = id!!,
        part = part!!,
        title = title,
        videoId = videoId,
        chapters = this.getChapterList(),
        publishedDate = LocalDateTime.parse(this.publishedDate.replace(" ", "T")).date,
    )
}


fun HappyHourVideoDto.getChapterList(): List<HappyHourChapter> {
    try {
        if(timeStampText.isNullOrBlank() || videoId.isNullOrBlank()){
            return emptyList()
        }
        val hhChapters = mutableListOf<HappyHourChapter>()
        val timestampStrings = this.timeStampText.split("\n")
        if(timestampStrings.contains("\n")) {
            timestampStrings.forEach {
                val chapterString = it.removeSuffix("\r")
                val chapterParts = chapterString.split("-")
                when {
                    chapterParts.size == 1 -> {
                        if (chapterParts[0].isBlank()) {
                            return@forEach
                        }
                        val title = chapterParts[0]
                        val timestamp = "00:00:00"
                        hhChapters.add(
                            HappyHourChapter(
                                title = title.trim(),
                                timeStamp = timestamp,
                                uri = YoutubeAddressAssembler.assemble(timestampString = timestamp, videoId = this.videoId)
                            )
                        )
                    }
                    chapterParts.size == 2 -> {
                        // TODO: sometime there is a chapter where the string with 0 index isn't a time code string pl időjárás-jelentés, currently in these cases we return 0
                        hhChapters.add(
                            HappyHourChapter(
                                title = chapterParts[1],
                                timeStamp = chapterParts[0],
                                uri = YoutubeAddressAssembler.assemble(timestampString = chapterParts[0], videoId = videoId)
                            )
                        )
                    }
                    chapterParts.size > 2 -> {
                        val assembledTitle = chapterParts.subList(1, chapterParts.size)
                            .joinToString("-") { ch -> ch.trim() }
                        HappyHourChapter(
                            title = assembledTitle,
                            timeStamp = chapterParts[0],
                            uri = YoutubeAddressAssembler.assemble(timestampString = chapterParts[0], videoId = videoId)
                        )
                    }
                    else -> {
                        throw IllegalStateException()
                    }
                }
            }
            return hhChapters
        }else {
            return emptyList()
        }
    }catch (e: Exception) {
        throw IllegalStateException()
    }
}

fun HappyHourVideo.toHappyHourCardState(): HappyHourCardState {
    return HappyHourCardState(
        id = id,
        title = title,
        part = part,
        publishDate = publishedDate.toString()
    )
}