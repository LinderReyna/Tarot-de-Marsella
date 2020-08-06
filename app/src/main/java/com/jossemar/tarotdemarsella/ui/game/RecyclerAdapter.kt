package com.jossemar.tarotdemarsella.ui.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jossemar.tarotdemarsella.R
import com.jossemar.tarotdemarsella.model.Arcane

class RecyclerAdapter(var items: List<Arcane>, private val listener: MoveClickListener): RecyclerView.Adapter<RecyclerAdapter.RecyclerItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game,parent,false)
        return RecyclerItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.card.tag = items.get(position).image
        holder.card.setOnLongClickListener {
            listener.OnMoveClick(it)
        }
    }

    class RecyclerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var card: ImageView = itemView.findViewById(R.id.card_item)
    }

    fun notifyData(items: List<Arcane>){
        this.items = items
        notifyDataSetChanged()
    }

}