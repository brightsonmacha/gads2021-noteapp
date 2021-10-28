package com.example.noteapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var notePosition = NOTE_SET_POSITION
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adCourse = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            DataManager.courses().values.toList()
        )
        adCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCourse.adapter = adCourse

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
        note.noteTitle = binding.txtTitle.text.toString()
        note.course = binding.spinnerCourse.selectedItem as CourseInfo
        note.noteDesc = binding.txtDesc.text.toString()
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

        binding.spinnerCourse.setSelection(course)
        binding.txtTitle.setText(note.noteTitle)
        binding.txtDesc.setText( note.noteDesc)
    }
}