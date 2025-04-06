package com.example.libraryexam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room database for the Library Management Application
 */
@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class LibraryDatabase : RoomDatabase() {
    
    abstract fun bookDao(): BookDao
    
    companion object {
        @Volatile
        private var INSTANCE: LibraryDatabase? = null
        
        fun getDatabase(context: Context): LibraryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibraryDatabase::class.java,
                    "library_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
} 