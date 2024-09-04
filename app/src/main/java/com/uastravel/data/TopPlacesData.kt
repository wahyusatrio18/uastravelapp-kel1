package com.uastravel.data

import java.io.Serializable

class TopPlacesData(
    var placeName: String,
    var countryName: String,
    var price: String,
    var date: Int,
    var imageUrl: Int
): Serializable