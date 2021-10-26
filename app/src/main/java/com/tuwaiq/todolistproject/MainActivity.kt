package com.tuwaiq.todolistproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
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

        //set recycle view adapter
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = userAdapter


        //set Dialog
        addBtn.setOnClickListener{ addInfo()}
    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_item,null)

        //set view
        val enterTitle: EditText = v.findViewById(R.id.txtTitle)
        val enterDate: EditText = v.findViewById(R.id.txtDate)

        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
            dialog,_ ->
            val names = enterTitle.text.toString()
            val number = enterDate.text.toString()
            userList.add(UserData("Title: $names","Date: $number"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Adding success",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
            dialog,_ ->
            dialog.dismiss()
            Toast.makeText(this,"Canceled",Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
}