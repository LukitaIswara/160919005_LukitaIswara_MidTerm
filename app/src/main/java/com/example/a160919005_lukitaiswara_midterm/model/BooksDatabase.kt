package com.example.a160919005_lukitaiswara_midterm.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a160919005_lukitaiswara_midterm.util.MIGRATION_1_2
import com.example.a160919005_lukitaiswara_midterm.util.MIGRATION_2_3

@Database(entities = arrayOf(MyBooks::class), version =  3)
abstract class BooksDatabase: RoomDatabase() {
    abstract fun booksDao(): BooksDao

    companion object {
        @Volatile private var instance: BooksDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            BooksDatabase::class.java,
            "booksdb")
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }



    }

}