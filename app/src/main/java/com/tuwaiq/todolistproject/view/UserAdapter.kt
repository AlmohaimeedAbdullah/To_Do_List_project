package com.tuwaiq.todolistproject.view

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.todolistproject.R
import com.tuwaiq.todolistproject.model.UserData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class UserAdapter(val c:Context,val userList:MutableList<UserData>):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {



    inner class UserViewHolder(v: View):RecyclerView.ViewHolder(v){

        var title: TextView
        var date: TextView
        var mMenu:ImageView
        var mDescribe: TextView
        var creationDate:TextView

        init {
            title = v.findViewById(R.id.mTitle)
            date = v.findViewById(R.id.mSubTitle)
            mDescribe = v.findViewById(R.id.mDescription)
            creationDate = v.findViewById(R.id.txtCreate)
            mMenu = v.findViewById(R.id.mMenus)
            mMenu.setOnClickListener{popupMenus(it)}
        }

        //menu -edit and delete-
        private fun popupMenus(v:View) {
            val position = userList[adapterPosition]
            val popupMenus = PopupMenu(c,v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {

                val v = LayoutInflater.from(c).inflate(R.layout.add_item,null)
                val title = v.findViewById<EditText>(R.id.txtTitle)
                val enterDate = v.findViewById<TextView>(R.id.txtDate)
                val enterDiscription = v.findViewById<TextView>(R.id.txtDescribe)
                val creationday = v.findViewById<TextView>(R.id.txtCreationDate)
                //current day
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val formatted = current.format(formatter)
                when (it.itemId) {


                    //if(>=){
                    //set edit
                    R.id.editText ->{

                        val c =  Calendar.getInstance()
                        val day = c.get(Calendar.DAY_OF_MONTH)
                        val month = c.get(Calendar.MONTH)
                        val year = c.get(Calendar.YEAR)
                        var date = ""
                        enterDate.setOnClickListener{
                            val datePickerDialog = DatePickerDialog(v.context,DatePickerDialog.OnDateSetListener{
                                    view, y, m, d ->
                                date = "$y/$m/$d"
                                enterDate.setText(date) },
                                year,month,day)
                            datePickerDialog.datePicker.minDate = c.timeInMillis
                            datePickerDialog.show()
                        }
                        //creation Date
                        creationday.setText(formatted)

                            AlertDialog.Builder(v.context)
                                .setView(v)
                                .setPositiveButton("ok") { dialog, _ ->
                                    if (title.text.isNotBlank()) {
                                        position.txtTitle = title.text.toString()
                                        position.txtDate = enterDate.text.toString()
                                        position.txtDescribe = enterDiscription.text.toString()
                                        position.creatDate = creationday.text.toString()
                                        notifyDataSetChanged()
                                        Toast.makeText(v.context, "you editing the data", Toast.LENGTH_LONG).show()
                                        dialog.dismiss()
                                    } else {
                                        Toast.makeText(v.context, "Must add title", Toast.LENGTH_LONG).show()
                                    }
                                }
                                .setNegativeButton("Cancel") { dialog, _ ->
                                    dialog.dismiss()
                                }
                                .create()
                                .show()
                        true
                    }
                    //set delete
                    R.id.deleteText ->{
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Are you sur delete this List?")
                            .setPositiveButton("Yes"){
                                    dialog,_ ->
                                userList.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c,"you deleted a List", Toast.LENGTH_LONG).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){
                                    dialog,_ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                        true
                    }
                    else -> true
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item,parent,false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]
        holder.title.text = newList.txtTitle
        holder.date.text = newList.txtDate
        holder.creationDate.text = newList.creatDate
        holder.mDescribe.text = newList.txtDescribe
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}