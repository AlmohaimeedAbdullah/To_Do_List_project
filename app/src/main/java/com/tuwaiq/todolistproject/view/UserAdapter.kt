package com.tuwaiq.todolistproject.view

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.todolistproject.R
import com.tuwaiq.todolistproject.model.UserData

class UserAdapter(val c:Context,val userList:ArrayList<UserData>):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {



    inner class UserViewHolder(val v: View):RecyclerView.ViewHolder(v){

        var title: TextView
        var date: TextView
        var mMenu:ImageView

        init {
            title = v.findViewById(R.id.mTitle)
            date = v.findViewById(R.id.mSubTitle)
            mMenu = v.findViewById(R.id.mMenus)
            mMenu.setOnClickListener{popupMenus(it)}
        }

        //menu -edit and delete-
        private fun popupMenus(v:View) {
            val position = userList[adapterPosition]
            val popupMenus = PopupMenu(c,v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){

                    //set edit
                    R.id.editText ->{
                        val v = LayoutInflater.from(c).inflate(R.layout.add_item,null)
                        val title = v.findViewById<EditText>(R.id.txtTitle)
                        val date = v.findViewById<EditText>(R.id.txtDate)
                            AlertDialog.Builder(c)
                            .setView(v)
                                .setPositiveButton("ok"){
                                    dialog,_ ->
                                    position.txtTitle = title.text.toString()
                                    position.txtDate = date.text.toString()
                                    notifyDataSetChanged()
                                    Toast.makeText(c,"you editing the data", Toast.LENGTH_LONG).show()
                                    dialog.dismiss()
                                }
                                .setNegativeButton("Cancel"){
                                    dialog,_ ->
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
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}