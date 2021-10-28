package com.example.noteapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.databinding.ActivityNoteListBinding

class NoteListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.noteList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, DataManager.notes)

        binding.noteList.setOnItemClickListener{_, _, position, _ ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(NOTE_POSITION, position)
            startActivity(intent)
        }

        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        (binding.noteList.adapter as ArrayAdapter<*>).notifyDataSetChanged()
    }
}