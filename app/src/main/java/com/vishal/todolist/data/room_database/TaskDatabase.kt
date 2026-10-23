package com.vishal.todolist.data.room_database

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase

@Database(entities = [TaskItem::class], version = 1)
abstract class TaskDatabase : RoomDatabase(){

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile

        private var INSTANCE: TaskDatabase? =null

        fun getDatabase(context: Context): TaskDatabase{
            return INSTANCE?:synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }


    }
}