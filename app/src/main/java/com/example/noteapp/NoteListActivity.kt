package com.example.noteapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.databinding.ActivityNoteListBinding
import com.example.noteapp.databinding.NoteListBinding

class NoteListActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityNoteListBinding
    private lateinit var binding: NoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding = ActivityNoteListBinding.inflate(layoutInflater)
        binding = NoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.noteList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, DataManager.notes)

        //binding.noteList.setOnItemClickListener{_, _, position, _ ->
        //    val intent = Intent(this, MainActivity::class.java)
        //    intent.putExtra(NOTE_POSITION, position)
        //    startActivity(intent)
        //}

        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.noteList.layoutManager = LinearLayoutManager(this)
        binding.noteList.adapter = NoteRecycleAdapter(this, DataManager.notes)


    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        binding.noteList.adapter?.notifyDataSetChanged()
        //(binding.noteList.adapter as ArrayAdapter<*>).notifyDataSetChanged()
    }
}