package com.example.noteapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRecycleAdapter(context : Context, private val notes : List<NoteInfo>) :
    RecyclerView.Adapter<NoteRecycleAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.note_list_item, parent, false)
        return  ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]

        holder.textCourse.text = note.course.toString()
        holder.textTile.text = note.noteTitle
        holder.notePosition =  position

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textCourse =  itemView.findViewById<TextView>(R.id.noteTextCourse)
        val textTile = itemView.findViewById<TextView>(R.id.noteTextTitle)
        var notePosition = 0

        init {
            itemView.setOnClickListener{
                    val intent = Intent(itemView.context, MainActivity::class.java)
                    intent.putExtra(NOTE_POSITION, notePosition)
                    itemView.context.startActivity(intent)
            }
        }
    }
}