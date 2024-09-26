package data

import androidx.room.ConstructedBy
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

//Further information: https://developer.android.com/kotlin/multiplatform/room

@Database(entities = [HappyHourVideoEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
@TypeConverters(HappyHourDatabaseConverter::class)
abstract class HappyHourDatabase : RoomDatabase() {
    abstract fun getDao(): HappyHourVideoDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<HappyHourDatabase> {
    override fun initialize(): HappyHourDatabase
}

@Dao
interface HappyHourVideoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(videoEntity: HappyHourVideoEntity)

    @Query("SELECT MAX(part) FROM HappyHourVideoEntity")
    suspend fun latest(): Int?

    @Query("SELECT * FROM HappyHourVideoEntity")
    fun getAllAsFlow(): Flow<List<HappyHourVideoEntity>>

    @Query("DELETE FROM HappyHourVideoEntity")
    fun clearTable()

}

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