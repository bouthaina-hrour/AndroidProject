package com.fairclass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.OnRoomSelectedListener
import com.faircorp.R
import com.faircorp.model.RoomDto


class RoomAdapter (val listener: OnRoomSelectedListener): RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() { // (1)

    inner class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) { // (2)


        val room_name: TextView = view.findViewById(R.id.txt_room_name)

    }

    private val items = mutableListOf<RoomDto>() // (3)

    fun update(rooms: List<RoomDto>) {  // (4)
        items.clear()
        items.addAll(rooms)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size // (5)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder { // (6)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_rooms_item, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {  // (7)
        val room = items[position]
        holder.apply {
            room_name.text = room.name

            itemView.setOnClickListener { listener.onRoomSelected(room.id)
            }
        }
    }
    override fun onViewRecycled(holder: RoomViewHolder) { // (2)
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }
    }}