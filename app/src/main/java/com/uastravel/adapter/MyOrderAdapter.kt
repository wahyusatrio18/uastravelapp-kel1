package com.uastravel.adapter

// MyOrderAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uastravel.R
import com.uastravel.data.entity.MyOrder

class MyOrderAdapter(private var myOrderList: List<MyOrder>, private val listener: OnItemClickListener) : RecyclerView.Adapter<MyOrderAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onDeleteClick(position: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeNameTextView: TextView = itemView.findViewById(R.id.textViewPlaceName)
        val priceTextView: TextView = itemView.findViewById(R.id.textViewPrice)
        val dateTextView: TextView = itemView.findViewById(R.id.textViewDepartureDate)
        val deleteBtn: Button = itemView.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_order, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentOrder = myOrderList[position]
        holder.placeNameTextView.text = "Nama Tempat: ${currentOrder.placeName}"
        holder.priceTextView.text = "Price: ${currentOrder.price}"
        holder.dateTextView.text = "Departure Date: ${currentOrder.departureDate}"
        holder.deleteBtn.setOnClickListener {
            listener.onDeleteClick(position)
        }
    }

    fun getItem(position: Int): MyOrder {
        return myOrderList[position]
    }

    override fun getItemCount(): Int {
        return myOrderList.size
    }

    fun updateList(newList: List<MyOrder>) {
        myOrderList = newList
        notifyDataSetChanged()
    }
}

