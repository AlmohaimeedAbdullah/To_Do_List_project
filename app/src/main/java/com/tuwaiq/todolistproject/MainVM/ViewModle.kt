package com.tuwaiq.todolistproject.MainVM

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tuwaiq.todolistproject.model.AppDataBase
import com.tuwaiq.todolistproject.model.AppRepo
import com.tuwaiq.todolistproject.model.UserData
import kotlinx.coroutines.launch

class ViewModle(context: Application) : AndroidViewModel(context) {

    private val repo = AppRepo.AppRepo(context)



    fun getAllUsers(): MutableLiveData<List<UserData>> {
        val users = MutableLiveData<List<UserData>>()
        viewModelScope.launch {
            users.postValue(repo.getAllUsers())
        }
        return users
    }

    fun insertTask(userData: UserData) = viewModelScope.launch {
        repo.insertTask(userData)
    }


    fun deleteTask(userData: UserData) = viewModelScope.launch {
        repo.deleteTask(userData)
    }
    fun updateTask(userData: UserData)= viewModelScope.launch {
        repo.updateTask(userData)
    }

}
