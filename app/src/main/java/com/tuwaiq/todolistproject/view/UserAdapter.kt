package com.tuwaiq.todolistproject.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.todolistproject.R
import com.tuwaiq.todolistproject.model.UserData
import kotlin.concurrent.fixedRateTimer

class UserAdapter(val c:Context,val userList:ArrayList<UserData>):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val v: View):RecyclerView.ViewHolder(v){
        val name: TextView = v.findViewById(R.id.mTitle)
        val mbNumber: TextView = v.findViewById(R.id.mSubTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item,parent,false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]
        holder.name.text = newList.userName
        holder.mbNumber.text = newList.userMb
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}