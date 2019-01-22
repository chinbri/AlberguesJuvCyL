package com.chinsoft.alb.juv.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import com.chinsoft.domain.entities.ShelterEntity
import com.chinsoft.presentation.main.ListActions
import com.chinsoft.alb.juv.R

typealias RechargePointsAdapterListener = (shelterEntity: ShelterEntity, action: ListActions) -> Unit

class RechargePointsAdapter(
    var shelterList: List<ShelterEntity> = emptyList(),
    private val listener: RechargePointsAdapterListener
): RecyclerView.Adapter<RechargePointItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): RechargePointItemViewHolder {
        return RechargePointItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    override fun getItemCount() = shelterList.size

    override fun onBindViewHolder(viewHolder: RechargePointItemViewHolder, position: Int) {
        viewHolder.bind(shelterList[position], listener)
    }
}

class RechargePointItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    fun bind(
        pointEntity: ShelterEntity,
        listener: RechargePointsAdapterListener
    ){
        itemView.setOnClickListener {
            listener.invoke(pointEntity, ListActions.SELECTION)
        }

        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val ivInfo = itemView.findViewById<ImageView>(R.id.ivInfo)
        val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)

        val tvLetter = itemView.findViewById<TextView>(R.id.tvLetter)
        val ivNavigation = itemView.findViewById<ImageView>(R.id.ivNavigation)
        val tvDistance = itemView.findViewById<TextView>(R.id.tvDistance)
        val wrapperDistance = itemView.findViewById<Group>(R.id.wrapperDistance)

        tvName.text = pointEntity.name
        tvDescription.text = pointEntity.description
        tvLetter.text = pointEntity.letter

        tvDistance.text = itemView.context.resources.getString(R.string.distance_label_km, pointEntity.distance)

        wrapperDistance.visibility = if (pointEntity.distance == 0f) {View.GONE} else {View.VISIBLE}

        if(pointEntity.url.isNullOrEmpty()){
            ivInfo.visibility = View.GONE
        }else{
            ivInfo.visibility = View.VISIBLE
            ivInfo.setOnClickListener {
                listener.invoke(pointEntity, ListActions.INFO)
            }
        }

        ivNavigation.setOnClickListener{
            listener.invoke(pointEntity, ListActions.NAVIGATION)
        }


    }


}