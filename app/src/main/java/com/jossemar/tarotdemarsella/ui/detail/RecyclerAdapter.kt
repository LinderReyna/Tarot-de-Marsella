package com.jossemar.tarotdemarsella.ui.detail

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jossemar.tarotdemarsella.R
import com.jossemar.tarotdemarsella.model.Arcane

class RecyclerAdapter(
    var items: List<Arcane>,
    private val context: Context,
    private val height: Int
): RecyclerView.Adapter<RecyclerAdapter.RecyclerItemViewHolder>() {

    private lateinit var orientation: Any

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail,parent,false)
        orientation = parent.context.resources.configuration.orientation
        return RecyclerItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.title.text = items.get(position).name
        if (orientation == Configuration.ORIENTATION_PORTRAIT){
            holder.image.layoutParams.height = height / 2
            holder.image.layoutParams.width = height / 4
            holder.image.requestLayout()
        }
        holder.image.setBackgroundResource(context.resources.getIdentifier(items.get(position).image,"drawable",context.packageName))
        holder.description.text = items.get(position).description
    }

    class RecyclerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.detail_title)
        var image: ImageView = itemView.findViewById(R.id.detail_image)
        var description: TextView = itemView.findViewById(R.id.detail_description)
    }
}