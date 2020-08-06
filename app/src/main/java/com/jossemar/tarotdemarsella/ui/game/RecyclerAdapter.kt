package com.jossemar.tarotdemarsella.ui.game

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jossemar.tarotdemarsella.R
import com.jossemar.tarotdemarsella.model.Arcane

class RecyclerAdapter(
    var items: List<Arcane>,
    private val listener: MoveClickListener,
    private val height: Int
): RecyclerView.Adapter<RecyclerAdapter.RecyclerItemViewHolder>() {

    private lateinit var orientation: Any

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game,parent,false)
        orientation = parent.context.resources.configuration.orientation
        return RecyclerItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        if (orientation == Configuration.ORIENTATION_PORTRAIT){
            holder.card.layoutParams.height = height / 5
            holder.card.layoutParams.width = height / 10
        } else {
            holder.card.layoutParams.height = height / 4
            holder.card.layoutParams.width = height / 8
        }
        holder.card.requestLayout()
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