package pt.ipp.isep.weatherapp.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ipp.isep.weatherapp.data.persistence.model.Location
import pt.ipp.isep.weatherapp.data.persistence.dao.LocationDao

private const val DB_NAME = "weather_db"

@Database(entities = [Location::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }

}