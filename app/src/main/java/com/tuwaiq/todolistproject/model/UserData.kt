package com.tuwaiq.todolistproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserData (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var txtTitle :String,
    var txtDate :String,
    var txtDescribe: String,
    var creatDate:String
        )