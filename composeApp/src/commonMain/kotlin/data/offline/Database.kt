package data.offline

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import data.entity.HappyHourVideoEntity
import data.offline.converters.HappyHourDatabaseConverter

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