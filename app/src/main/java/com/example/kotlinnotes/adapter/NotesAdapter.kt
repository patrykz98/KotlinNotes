package com.example.kotlinnotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinnotes.R
import com.example.kotlinnotes.entities.Notes
import kotlinx.android.synthetic.main.item_rv_notes.view.*

class NotesAdapter(val arrList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
       return NotesViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.item_rv_notes,parent, false)
       )
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        holder.itemView.tvTtile.text = arrList[position].title
        holder.itemView.tvDesc.text = arrList[position].noteText
        holder.itemView.tvDateTime.text = arrList[position].dateTime
    }

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }

}