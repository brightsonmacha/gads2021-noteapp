package com.example.noteapp

import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var notePosition = NOTE_SET_POSITION
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adCourse = ArrayAdapter(this,android.R.layout.simple_spinner_item, DataManager.courses().values.toList())
        adCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinnerCourse =findViewById<Spinner>(R.id.spinnerCourse)
        spinnerCourse.adapter = adCourse

        notePosition = intent.getIntExtra(NOTE_POSITION, NOTE_SET_POSITION)

        if(notePosition != NOTE_SET_POSITION)
            displayNotes()
    }

    private fun displayNotes() {
        val note = DataManager.notes[notePosition]
        val txtTitle = findViewById<TextView>(R.id.txtTitle)
        val txtDesc = findViewById<TextView>(R.id.txtDesc)
        val spinner = findViewById<Spinner>(R.id.spinnerCourse)

        val course = DataManager.courses().values.indexOf(note.course)
        spinner.setSelection(course)

        txtTitle.text = note.noteTitle
        txtDesc.text = note.noteDesc
    }

    //Option Menu Item
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when(item.itemId){
            R.id.settings -> true
            R.id.next->{
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        ++notePosition
        val length = DataManager.notes.count()
        if(notePosition >= length){
            notePosition = 0
            displayNotes()
        }
        else
            displayNotes()
    }
}