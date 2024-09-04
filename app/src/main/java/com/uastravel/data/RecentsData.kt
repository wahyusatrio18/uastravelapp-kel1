package com.uastravel.data

import java.io.Serializable

class RecentsData(
    var placeName: String,
    var countryName: String,
    var price: String,
    var date: Int,
    var imageUrl: Int,
) : Serializable