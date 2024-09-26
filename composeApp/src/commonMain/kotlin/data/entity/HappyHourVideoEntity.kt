package data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
data class HappyHourVideoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val part: Int,
    val title: String,
    val videoId: String,
    val chapters: List<HappyHourVideoChapterEntity>,
    val publishedDateAsEpoch: Int
)

@Serializable
data class HappyHourVideoChapterEntity(
    val title: String,
    val timeStamp: String
)