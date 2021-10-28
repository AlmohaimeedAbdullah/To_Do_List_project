package com.tuwaiq.todolistproject.model

import androidx.room.*


@Dao
interface UserDao {

    @Insert//it will automatically make the sql query itself
    suspend fun insert(user:UserData)

    @Query("select * from UserData")
    suspend fun getAllUsers():List<UserData>

    @Update
    suspend fun update(user:UserData)

    @Delete
    suspend fun delete(user:UserData)
}

