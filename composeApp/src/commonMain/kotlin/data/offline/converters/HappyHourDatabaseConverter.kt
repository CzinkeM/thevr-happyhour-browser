package data.offline.converters

import androidx.room.TypeConverter
import data.entity.HappyHourVideoChapterEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class HappyHourDatabaseConverter {
    @TypeConverter
    fun fromChapterList(chapters: List<HappyHourVideoChapterEntity>): String {
        return Json.encodeToString(chapters)
    }

    @TypeConverter
    fun toChapterList(chaptersString: String): List<HappyHourVideoChapterEntity> {
        return if (chaptersString.isBlank()) {
            emptyList()
        }else {
            Json.decodeFromString(chaptersString)
        }
    }
}