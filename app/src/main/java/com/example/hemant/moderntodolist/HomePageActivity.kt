package com.example.hemant.moderntodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import java.util.Date

class HomePageActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var recyclerView: RecyclerView

    private var noteList = arrayListOf<Note>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        editText = findViewById(R.id.todo_list_edit_text)
        button = findViewById(R.id.todo_list_add_item_button)
        recyclerView = findViewById(R.id.todo_list_recycler_view)

        handleButtonClick()
    }

    private fun handleButtonClick() {
        button.setOnClickListener(){
            val currentNote = editText.text.toString();
            val date = Date().toString()
            if(currentNote.isNotEmpty())
            {
                appendNewNote(Note(currentNote,date))
            }
        }
    }

    private fun appendNewNote(note : Note){
        noteList.add(note)
    }

}