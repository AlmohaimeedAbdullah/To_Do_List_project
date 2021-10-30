package com.tuwaiq.todolistproject

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tuwaiq.todolistproject.MainVM.ViewModle
import com.tuwaiq.todolistproject.model.UserData
import com.tuwaiq.todolistproject.view.UserAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(){
    private lateinit var addBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var userList: MutableList<UserData>
    private lateinit var userAdapter: UserAdapter
    lateinit var mainVM: ViewModle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainVM = ViewModelProvider(this).get(ViewModle::class.java)
        loadScreen()


        //set find id
        addBtn = findViewById(R.id.addingBtn)
        recv = findViewById(R.id.mRecycleView)

        //set user List
        userList = ArrayList()

        //set adapter
       // userAdapter = UserAdapter(this,userList)

        //set recycle view adapter
        recv.layoutManager = LinearLayoutManager(this)


        //set Dialog
        addBtn.setOnClickListener{ addInfo()}
    }

    private fun loadScreen() {
        mainVM.getAllUsers().observe(this, {
            recv.adapter = UserAdapter(this, it as MutableList<UserData>,mainVM)
        })
    }


    private fun addInfo() {
        val mainVM = ViewModelProvider(this).get(ViewModle::class.java)
        // to fill the data base
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_item, null)

        //set view
        val enterTitle: EditText = v.findViewById(R.id.txtTitle)
        val enterDate: TextView = v.findViewById(R.id.txtDate)
        val enterDescription: TextView = v.findViewById(R.id.txtDescribe)
        val creationDate: TextView = v.findViewById(R.id.txtCreationDate)

        //creation Date
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)
        creationDate.setText(formatted)


        //date button
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)
        var date = ""
        enterDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this, DatePickerDialog.OnDateSetListener { view, y, m, d ->
                    date = "$y-${m+1}-$d"
                    enterDate.setText(date)
                },
                year, month, day
            )
            datePickerDialog.datePicker.minDate = c.timeInMillis
            datePickerDialog.show()
        }

        //dialog
        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok") { dialog, _ ->
            val title = enterTitle.text.toString()
            val date = enterDate.text.toString()
            val discr = enterDescription.text.toString()
            val create = creationDate.text.toString()

            if (title.isNotBlank()) {

                val addTask =UserData(taskTitle = title,taskDate= date,taskDescribe = discr,taskCreatDate = create)
                mainVM.insertTask(addTask)
                loadScreen()
                    Toast.makeText(this, "Adding success", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Must add title", Toast.LENGTH_SHORT).show()
            }
        }
        addDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }

}
