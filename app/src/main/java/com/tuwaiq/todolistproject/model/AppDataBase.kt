package com.tuwaiq.todolistproject.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserData::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract val userDao: UserDao

    companion object{
        private var INSTANCE: AppDataBase? = null
        fun getAppDataBase(context: Context):AppDataBase?{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "App_Data_Base"
                ).build()
            }
            return INSTANCE
        }
    }
}