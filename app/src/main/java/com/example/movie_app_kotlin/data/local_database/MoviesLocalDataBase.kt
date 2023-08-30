package com.example.movie_app_kotlin.data.local_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movie_app_kotlin.domain.model.details.MovieDetailsCache
import com.example.movie_app_kotlin.domain.model.details.MovieDetailsDao
import com.example.movie_app_kotlin.domain.model.movie.MovieCache
import com.example.movie_app_kotlin.domain.model.movie.MovieDao

@Database(
    entities = [MovieCache::class, MovieDetailsCache::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesLocalDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieDetailsDao(): MovieDetailsDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesLocalDataBase? = null

        fun getInstance(context: Context): MoviesLocalDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesLocalDataBase::class.java,
                    "movies_local_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}