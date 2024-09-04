package com.uastravel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uastravel.adapter.MyOrderAdapter
import com.uastravel.data.DatabaseHelper
import com.uastravel.data.entity.MyOrder


class MyOrder : AppCompatActivity(), MyOrderAdapter.OnItemClickListener {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var myOrderAdapter: MyOrderAdapter
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_order)
        SessionManager.initialize(this)

        sessionManager = SessionManager(this)
        val userId = sessionManager.getUserId()

        databaseHelper = DatabaseHelper(this)

        Log.d("DebugTag", "UserId saat login: $userId")
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewMyOrder)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Gunakan userId yang sudah diperoleh dari sessionManager
        myOrderAdapter = MyOrderAdapter(getMyOrderList(userId), this)
        recyclerView.adapter = myOrderAdapter
    }

    override fun onDeleteClick(position: Int) {
        // Mendapatkan userId dari sesi pengguna
        val userId = sessionManager.getUserId()
        val myOrders = databaseHelper.getAllOrders(userId)

        // Mendapatkan orderToDelete dari myOrderAdapter
        val orderToDelete = myOrderAdapter.getItem(position)

        // Hapus item dari database
        databaseHelper.deleteOrder(userId, orderToDelete.id.toLong())

        // Perbarui RecyclerView
        myOrderAdapter.updateList(myOrders)
        myOrderAdapter.notifyDataSetChanged()
    }

    private fun getMyOrderList(userId: Long): List<MyOrder> {
        return databaseHelper.getAllOrders(userId)
    }
}
