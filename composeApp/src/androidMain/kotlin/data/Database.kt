package data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<HappyHourDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("happy_hours.db")
    return Room.databaseBuilder<HappyHourDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}