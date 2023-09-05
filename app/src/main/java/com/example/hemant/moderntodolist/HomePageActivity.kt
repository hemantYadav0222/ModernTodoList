package com.example.hemant.moderntodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Date

class HomePageActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TodoListAdapter

    private var noteList = arrayListOf<Note>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        editText = findViewById(R.id.todo_list_edit_text)
        button = findViewById(R.id.todo_list_add_item_button)
        recyclerView = findViewById(R.id.todo_list_recycler_view)

        //recyclerview logic
        adapter = TodoListAdapter(noteList)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = adapter

        //button click handling
        handleButtonClick()
    }

    private fun handleButtonClick() {
        button.setOnClickListener(){
            val currentNote = editText.text.toString();
            val date = formatDate(Date().toString())

            if(currentNote.isNotEmpty())
            {
                appendNewNote(Note(currentNote,date))
            }
            editText.setText("");
        }
    }

    private fun formatDate(date : String) : String{
        val splitDate = date.split(" ")
        return splitDate[1] + " "+  splitDate[2]
    }

    private fun appendNewNote(note : Note){
        noteList.add(note)
        adapter.notifyDataSetChanged()
    }

}