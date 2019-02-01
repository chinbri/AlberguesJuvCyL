package com.chin.alb.juv.ui.main.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import com.chin.domain.entities.ShelterEntity
import com.chin.presentation.main.ListActions
import com.chin.alb.juv.R

typealias ShelterAdapterListener = (shelterEntity: ShelterEntity, action: ListActions) -> Unit

class ShelterAdapter(
    var shelterList: List<ShelterEntity> = emptyList(),
    private val listener: ShelterAdapterListener
): RecyclerView.Adapter<ShelterItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): ShelterItemViewHolder {
        return ShelterItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    override fun getItemCount() = shelterList.size

    override fun onBindViewHolder(viewHolder: ShelterItemViewHolder, position: Int) {
        viewHolder.bind(shelterList[position], listener)
    }
}

class ShelterItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    fun bind(
        shelterEntity: ShelterEntity,
        listener: ShelterAdapterListener
    ){
        itemView.setOnClickListener {
            listener.invoke(shelterEntity, ListActions.SELECTION)
        }

        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val ivInfo = itemView.findViewById<ImageView>(R.id.ivInfo)
        val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)

        val tvOpened = itemView.findViewById<TextView>(R.id.tvOpened)
        val ivNavigation = itemView.findViewById<ImageView>(R.id.ivNavigation)
        val tvDistance = itemView.findViewById<TextView>(R.id.tvDistance)
        val wrapperDistance = itemView.findViewById<Group>(R.id.wrapperDistance)

        tvName.text = shelterEntity.name
        tvDescription.text = shelterEntity.description
        tvOpened.text = if(shelterEntity.opened.isNullOrBlank()) {""} else{
            itemView.context.resources.getString(R.string.opened) + shelterEntity.opened
        }
        tvOpened.visibility = if(shelterEntity.opened?.trim().isNullOrEmpty()) { View.GONE } else{ View.VISIBLE }

        tvDistance.text = itemView.context.resources.getString(R.string.distance_label_km, shelterEntity.distance)

        wrapperDistance.visibility = if (shelterEntity.distance == 0f) {View.GONE} else {View.VISIBLE}

        if(shelterEntity.url.isNullOrEmpty()){
            ivInfo.visibility = View.GONE
        }else{
            ivInfo.visibility = View.VISIBLE
            ivInfo.setOnClickListener {
                listener.invoke(shelterEntity, ListActions.INFO)
            }
        }

        ivNavigation.setOnClickListener{
            listener.invoke(shelterEntity, ListActions.NAVIGATION)
        }


    }

}