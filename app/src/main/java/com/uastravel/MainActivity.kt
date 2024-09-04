package com.uastravel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uastravel.adapter.RecentsAdapter
import com.uastravel.adapter.TopPlacesAdapter
import com.uastravel.data.entity.UserEntityDetails
import com.uastravel.repository.DataRepository

class MainActivity : AppCompatActivity() {
    private lateinit var recentsAdapter: RecentsAdapter
    private lateinit var topPlacesAdapter: TopPlacesAdapter
    private val recentRecycler: RecyclerView by lazy { findViewById(R.id.recent_recycler) }
    private val topPlacesRecycler: RecyclerView by lazy { findViewById(R.id.top_places_recycler) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SessionManager.initialize(this)
        val preferences = SharedPreferences.getInstance(this)
        val isLogged = preferences.getBoolean("logged")
        val userId = getUserIdFromSession(this)

        Log.d("SplashActivity", "Status Login: $isLogged")
        Log.d("DebugTag", "UserId saat login: $userId")


        recentsAdapter = RecentsAdapter(this, DataRepository.getRecentsData())
        recentRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recentRecycler.adapter = recentsAdapter

        topPlacesAdapter = TopPlacesAdapter(this, DataRepository.getTopPlacesData())
        topPlacesRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        topPlacesRecycler.adapter = topPlacesAdapter

        recentsAdapter.setItemClickListener(object : RecentsAdapter.ItemClickListener {
            override fun onItemClick(position: Int) {
                val selectedData = recentsAdapter.getItem(position)
                navigateToDetailActivity(selectedData)
            }
        })

        topPlacesAdapter.setItemClickListener(object : TopPlacesAdapter.ItemClickListener {
            override fun onItemClick(position: Int) {
                val selectedData = topPlacesAdapter.getItem(position)
                navigateToDetailActivityTopPlaces(selectedData)
            }
        })

        val myOrderImageView: ImageView = findViewById(R.id.forMyOrderButton)
        myOrderImageView.setOnClickListener {
            goToMyOrder()
        }

        val forProfileButton: ImageView = findViewById(R.id.forProfileButton)
        forProfileButton.setOnClickListener{
            goToProfile()
        }
    }

    fun goToMyOrder() {
        val intent = Intent(this, MyOrder::class.java)
        startActivity(intent)
    }

    fun goToProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    fun getUserIdFromSession(context: Context): Long {
        val sessionManager = SessionManager(context)
        return sessionManager.getUserId()
    }

    private fun navigateToDetailActivity(selectedData: UserEntityDetails) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("selectedData", selectedData)
        startActivity(intent)
    }

    private fun navigateToDetailActivityTopPlaces(selectedData: UserEntityDetails) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("selectedData", selectedData)
        startActivity(intent)
    }
}
