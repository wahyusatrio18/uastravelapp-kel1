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

class TopPlacesAdapter(var context: Context, var topPlacesDataList: List<UserEntityDetails>) :
    RecyclerView.Adapter<TopPlacesAdapter.TopPlacesViewHolder>() {

    // Deklarasikan variabel untuk menyimpan item click listener
    private var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopPlacesViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.top_places_row_item, parent, false)

        return TopPlacesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopPlacesViewHolder, position: Int) {
        val topPlacesData = topPlacesDataList[position]
        holder.countryName.text = topPlacesData.countryName
        holder.placeName.text = topPlacesData.placeName
        holder.price.text = topPlacesData.price
        Picasso.get().load(topPlacesData.imageUrl).into(holder.placeImage)

        // Set click listener pada item
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return topPlacesDataList.size
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    // Getter untuk mendapatkan item berdasarkan posisi
    fun getItem(position: Int): UserEntityDetails {
        return topPlacesDataList[position]
    }

    // Interface untuk item click listener
    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    class TopPlacesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var placeImage: ImageView = itemView.findViewById(R.id.place_image)
        var placeName: TextView = itemView.findViewById(R.id.place_name)
        var countryName: TextView = itemView.findViewById(R.id.country_name)
        var price: TextView = itemView.findViewById(R.id.price)
    }
}
