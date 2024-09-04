package com.uastravel.data.entity

class User {

    var email : String? = null
    var name : String? = null
    var password : String? = null

    constructor(email : String, name : String, password : String) {
        this.email = email
        this.name = name
        this.password = password
    }

    constructor(email : String) {
        this.email = email
    }
}