package com.example.hemant.moderntodolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        holder.position.text = (position + 1).toString()

    }

    override fun getItemCount(): Int {
        return noteList.size
    }


    inner class TodoListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var note: TextView;
        var position: TextView
        var date: TextView

        init {
            note = itemView.findViewById(R.id.note_title)
            position = itemView.findViewById(R.id.note_right_text)
            date = itemView.findViewById(R.id.note_subtitle)
        }

    }
}