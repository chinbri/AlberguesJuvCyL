package com.chinsoft.alb.juv.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chinsoft.domain.entities.ShelterEntity
import com.chinsoft.presentation.main.ListActions
import com.chinsoft.alb.juv.R

typealias RechargePointsAdapterListener = (shelterEntity: ShelterEntity, action: ListActions) -> Unit

class RechargePointsAdapter(
    var shelterList: List<ShelterEntity> = emptyList(),
    val listener: RechargePointsAdapterListener
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

        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvAddress = itemView.findViewById<TextView>(R.id.tvAddress)
        val ivInfo = itemView.findViewById<ImageView>(R.id.ivInfo)
        val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        val ivNavigation = itemView.findViewById<ImageView>(R.id.ivNavigation)
        val tvDistance = itemView.findViewById<TextView>(R.id.tvDistance)

        tvName.text = pointEntity.name
        tvAddress.text = pointEntity.address
        tvDescription.text = pointEntity.description
        tvDistance.text = itemView.context.resources.getString(R.string.distance_label_km, pointEntity.distance)

        if(pointEntity.url.isNotEmpty()){
            ivInfo.setOnClickListener {
                listener.invoke(pointEntity, ListActions.INFO)
            }
        }else{
            ivInfo.visibility = View.GONE
        }

        ivNavigation.setOnClickListener{
            listener.invoke(pointEntity, ListActions.NAVIGATION)
        }


    }


}