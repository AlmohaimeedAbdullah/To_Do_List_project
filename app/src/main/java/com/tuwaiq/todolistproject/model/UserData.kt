package com.tuwaiq.todolistproject.model

import androidx.room.Entity

@Entity
data class UserData (
    var txtTitle :String,
    var txtDate :String,
    var txtDescribe: String,
    var creatDate:String
        )