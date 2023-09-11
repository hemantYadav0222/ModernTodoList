package com.example.hemant.moderntodolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoListAdapter(private val noteList: ArrayList<Note>) :
    RecyclerView.Adapter<TodoListAdapter.TodoListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListVH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_list_item, parent, false)
        return TodoListVH(v)
    }

    override fun onBindViewHolder(holder: TodoListVH, position: Int) {
        val noteInfo = noteList[position]

        holder.note.text = noteInfo.noteString
        holder.date.text = noteInfo.noteDate
//        holder.delete_button

        holder.delete_button.setOnClickListener(){
            noteList.removeAt(position)
            notifyDataSetChanged()
            println("The position is: $position")
        }

    }

    override fun getItemCount(): Int {
        return noteList.size
    }


    inner class TodoListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var note: TextView;
        var delete_button: ImageView
        var date: TextView

        init {
            note = itemView.findViewById(R.id.note_title)
            delete_button = itemView.findViewById(R.id.delete_button)
            date = itemView.findViewById(R.id.note_subtitle)
        }

    }
}