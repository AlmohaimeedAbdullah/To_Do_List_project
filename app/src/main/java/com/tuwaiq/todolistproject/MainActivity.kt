package com.tuwaiq.todolistproject

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tuwaiq.todolistproject.model.UserData
import com.tuwaiq.todolistproject.view.UserAdapter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(){
    private lateinit var addBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter
/*    private lateinit var enterTitle: EditText
    private lateinit var enterDate: TextView*/





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*        //set view
        enterTitle= findViewById(R.id.txtTitle)
        //date
        enterDate = findViewById(R.id.txtDate)*/

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
        val v = inflater.inflate(R.layout.add_item, null)

        //set view
        val enterTitle: EditText = v.findViewById(R.id.txtTitle)
        val enterDate: TextView = v.findViewById(R.id.txtDate)

        //date button
      //  val dateBtn :Button = v.findViewById(R.id.btnWhen)
        val c =  Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)
        var date = ""
        enterDate.setOnClickListener{
            DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
                    view, y, m, d ->
                date = "$y/$m/$d"
                enterDate.setText(date) },
                year,month,day).show()
        }
        //dialog
        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok") { dialog, _ ->
            val title = enterTitle.text.toString()
            val date = enterDate.text.toString()
            userList.add(UserData(title, date))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Adding success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }


}