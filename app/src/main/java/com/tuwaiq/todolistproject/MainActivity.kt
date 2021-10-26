package com.tuwaiq.todolistproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tuwaiq.todolistproject.model.UserData
import com.tuwaiq.todolistproject.view.UserAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var addBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set find id
        addBtn = findViewById(R.id.addingBtn)
        recv = findViewById(R.id.mRecycleView)

        //set user List
        userList = ArrayList()

        //set adapter
        userAdapter = UserAdapter(this,userList)
    }
}