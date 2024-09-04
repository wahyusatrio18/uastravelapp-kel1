package com.uastravel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uastravel.R
import com.uastravel.data.entity.UserEntityDetails

class RecentsAdapter(
    private val context: Context,
    private val recentsDataList: List<UserEntityDetails>
) : RecyclerView.Adapter<RecentsAdapter.RecentsViewHolder>() {

    // Deklarasikan variabel untuk menyimpan item click listener
    private var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentsViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.recents_row_item, parent, false)
        return RecentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentsViewHolder, position: Int) {
        val recentsData = recentsDataList[position]
        holder.countryName.text = recentsData.countryName
        holder.placeName.text = recentsData.placeName
        holder.price.text = recentsData.price
        Picasso.get().load(recentsData.imageUrl).into(holder.placeImage)

        // Set click listener pada item
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return recentsDataList.size
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    // Getter untuk mendapatkan item berdasarkan posisi
    fun getItem(position: Int): UserEntityDetails {
        return recentsDataList[position]
    }

    // Interface untuk item click listener
    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    class RecentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var placeImage: ImageView = itemView.findViewById(R.id.place_image)
        var placeName: TextView = itemView.findViewById(R.id.place_name)
        var countryName: TextView = itemView.findViewById(R.id.country_name)
        var price: TextView = itemView.findViewById(R.id.price)
    }
}
