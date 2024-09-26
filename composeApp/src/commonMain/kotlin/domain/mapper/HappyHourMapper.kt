package domain.mapper

import data.entity.HappyHourVideoChapterEntity
import data.entity.HappyHourVideoEntity
import domain.HappyHourTitleFormatter
import domain.isNullOrMinus
import domain.model.HappyHourChapter
import domain.model.HappyHourVideo
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import networking.dto.HappyHourPageDto
import networking.dto.HappyHourVideoDto
import presentation.components.HappyHourCardState
import presentation.components.HappyHourVideoChapterCardState

fun HappyHourPageDto.toHappyHourVideoList(): List<HappyHourVideo> {
    return this.hhVideos.map { dto -> dto.toHappyHourVideo() }
}

fun HappyHourVideoDto.toHappyHourVideo(): HappyHourVideo {
    if (id.isNullOrMinus() || part.isNullOrMinus() || title.isNullOrBlank() || videoId.isNullOrBlank() || publishedDate.isNullOrBlank()) {
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
        if (timeStampText.isNullOrBlank() || videoId.isNullOrBlank()) {
            return emptyList()
        }
        val hhChapters = mutableListOf<HappyHourChapter>()
        if (this.timeStampText.contains("\n")) {
            val timestampStrings = this.timeStampText.split("\n")
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
                            )
                        )
                    }

                    chapterParts.size == 2 -> {
                        // TODO: sometime there is a chapter where the string with 0 index isn't a time code string pl időjárás-jelentés, currently in these cases we return 0
                        hhChapters.add(
                            HappyHourChapter(
                                title = chapterParts[1],
                                timeStamp = chapterParts[0],
                            )
                        )
                    }

                    chapterParts.size > 2 -> {
                        val assembledTitle = chapterParts.subList(1, chapterParts.size)
                            .joinToString("-") { ch -> ch.trim() }
                        HappyHourChapter(
                            title = assembledTitle,
                            timeStamp = chapterParts[0],
                        )
                    }

                    else -> {
                        throw IllegalStateException()
                    }
                }
            }
            return hhChapters
        } else {
            return emptyList()
        }
    } catch (e: Exception) {
        throw IllegalStateException()
    }
}

fun HappyHourVideo.toHappyHourCardState(): HappyHourCardState {
    return HappyHourCardState(
        id = id,
        title = HappyHourTitleFormatter.format(title),
        part = part,
        publishDate = publishedDate.toString()
    )
}

fun HappyHourVideo.toHappyHourVideoEntity(): HappyHourVideoEntity {
    return HappyHourVideoEntity(
        id = this.id,
        part = this.part,
        title = this.title,
        videoId = this.videoId,
        chapters = this.chapters.map { it.toHappyHourVideoChapterEntity() },
        publishedDateAsEpoch = this.publishedDate.toEpochDays()
    )
}

fun HappyHourVideoEntity.toHappyHourVideo(): HappyHourVideo {
    return HappyHourVideo(
        id = this.id,
        part = this.part,
        title = this.title,
        videoId = this.videoId,
        chapters = this.chapters.map { it.toHappyHourChapter() },
        publishedDate = LocalDate.fromEpochDays(this.publishedDateAsEpoch)
    )
}

fun HappyHourChapter.toHappyHourVideoChapterEntity(): HappyHourVideoChapterEntity {
    return HappyHourVideoChapterEntity(
        title = this.title,
        timeStamp = this.timeStamp,
    )
}

fun HappyHourVideoChapterEntity.toHappyHourChapter(): HappyHourChapter {
    return HappyHourChapter(
        title = this.title,
        timeStamp = this.timeStamp,
    )
}

fun List<HappyHourVideoEntity>.toHappyHourVideoList(): List<HappyHourVideo> {
    return this.map { it.toHappyHourVideo() }
}

fun HappyHourVideoDto.toHappyHourVideoEntity(): HappyHourVideoEntity {
    if (id.isNullOrMinus() || part.isNullOrMinus() || title.isNullOrBlank() || videoId.isNullOrBlank() || publishedDate.isNullOrBlank()) {
        throw IllegalArgumentException()
    }

    return HappyHourVideoEntity(
        id = this.id!!,
        part = this.part!!,
        title = this.title,
        videoId = this.videoId,
        chapters = this.getChapterList().map { it.toHappyHourVideoChapterEntity() },
        publishedDateAsEpoch = LocalDateTime.parse(
            this.publishedDate.replace(
                " ",
                "T"
            )
        ).date.toEpochDays(),
    )
}

fun HappyHourChapter.toHappyHourChapterCardState(videoId: String): HappyHourVideoChapterCardState {
    return HappyHourVideoChapterCardState(
        title = this.title,
        timestampString = this.timeStamp,
        videoId = videoId
    )
}