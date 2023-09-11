package com.example.hemant.moderntodolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date


class HomePageActivity : AppCompatActivity() {

    private val SHARED_PREF_FILE_NAME = "NOTES_LIST"
    private val NOTES_LIST_KEY = "notesList"

    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TodoListAdapter
    var gson = Gson()

    private var noteList = arrayListOf<Note>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate Called:::")
        loadNotesFromLocalStorage()
        setContentView(R.layout.activity_home_page)

        editText = findViewById(R.id.todo_list_edit_text)
        button = findViewById(R.id.todo_list_add_item_button)
        recyclerView = findViewById(R.id.todo_list_recycler_view)

        //recyclerview logic
        adapter = TodoListAdapter(noteList)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        //button click handling
        handleButtonClick()
    }

    override fun onStop() {
        saveDataBeforeExit()
        super.onStop()
    }

    private fun handleButtonClick() {
        button.setOnClickListener() {
            val currentNote = editText.text.toString();
            val date = formatDate(Date().toString())

            if (currentNote.isNotEmpty()) {
                appendNewNote(Note(currentNote, date))
            }
            editText.setText("");
        }
    }

    private fun formatDate(date: String): String {
        val splitDate = date.split(" ")
        return splitDate[1] + " " + splitDate[2]
    }

    private fun appendNewNote(note: Note) {
        noteList.add(0,note)
        adapter.notifyDataSetChanged()
    }

    private fun saveDataBeforeExit() {
        val sp = getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()

        val json = gson.toJson(noteList)
        editor.putString(NOTES_LIST_KEY, json)
        println("the saved json is $json")
        editor.apply()
    }

    private fun loadNotesFromLocalStorage() {
        println("loadNotesFromLocalStorage::: ")
        val sp = getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val savedValue = sp.getString(NOTES_LIST_KEY, null)

        if (savedValue == null) {
            println("Saved value is null")
        } else {
            println("The notes are::: $savedValue")

            val personListType = object : TypeToken<ArrayList<Note>>() {}.type
            val deserializedNotes : ArrayList<Note> = gson.fromJson(savedValue,personListType)

            noteList = deserializedNotes
        }
    }

}