package com.tuwaiq.todolistproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserData (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var taskTitle :String,
    var taskDate :String,
    var taskDescribe: String,
    var taskCreatDate:String
        )