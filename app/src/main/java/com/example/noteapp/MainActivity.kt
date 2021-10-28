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

        val adCourse = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            DataManager.courses().values.toList()
        )
        adCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner = findViewById<Spinner>(R.id.spinnerCourse)
        spinner.adapter = adCourse

        notePosition = savedInstanceState?.getInt(NOTE_POSITION, NOTE_SET_POSITION) ?:
            intent.getIntExtra(NOTE_POSITION, NOTE_SET_POSITION)

        if(notePosition != NOTE_SET_POSITION)
            displayNotes()
        else{
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex
        }
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

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NOTE_POSITION, notePosition)
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]
        val txtDesc = findViewById<TextView>(R.id.txtDesc)
        val txtTitle = findViewById<TextView>(R.id.txtTitle)
        val spinner = findViewById<Spinner>(R.id.spinnerCourse)

        note.noteTitle = txtTitle.text.toString()
        note.course = spinner.selectedItem as CourseInfo
        note.noteDesc = txtDesc.text.toString()
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

    private fun displayNotes() {
        val note = DataManager.notes[notePosition]

        val course = DataManager.courses().values.indexOf(note.course)
        val txtDesc = findViewById<TextView>(R.id.txtDesc)
        val txtTitle = findViewById<TextView>(R.id.txtTitle)
        val spinner = findViewById<Spinner>(R.id.spinnerCourse)

        spinner.setSelection(course)

        txtTitle.text = note.noteTitle
        txtDesc.text = note.noteDesc
    }
}