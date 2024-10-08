package data.offline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.entity.HappyHourVideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HappyHourVideoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(videoEntity: HappyHourVideoEntity)

    @Query("SELECT MAX(part) FROM HappyHourVideoEntity")
    suspend fun latest(): Int?

    @Query("SELECT * FROM HappyHourVideoEntity ORDER BY part DESC")
    fun getAllAsFlow(): Flow<List<HappyHourVideoEntity>>

    @Query("SELECT * FROM HappyHourVideoEntity")
    suspend fun getAll(): List<HappyHourVideoEntity>
}


