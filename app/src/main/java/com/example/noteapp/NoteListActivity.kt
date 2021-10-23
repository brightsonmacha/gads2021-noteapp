package com.example.noteapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class NoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        val noteList = findViewById<ListView>(R.id.noteList)
        noteList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, DataManager.notes)

        noteList.setOnItemClickListener{_, _, position, _ ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(NOTE_POSITION, position)
            startActivity(intent)
        }

        val addBtnNote = findViewById<Button>(R.id.btn_add_note)
        addBtnNote.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //print(item);
        return  when(item.itemId){
            R.id.action_bar -> true
            else -> super.onOptionsItemSelected(item)
        }
        //return super.onOptionsItemSelected(item)
    }
}