package com.tuwaiq.todolistproject.model

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepo {
    class AppRepo(context: Context) {
        //connect to the db
        private val appDB = AppDataBase.getAppDataBase(context)!!


        suspend fun getAllUsers(): List<UserData> = withContext(Dispatchers.IO) {
            appDB.userDao.getAllUsers()
        }

        suspend fun insertTask(userData: UserData) = appDB.userDao.insert(userData)



        }
    }
