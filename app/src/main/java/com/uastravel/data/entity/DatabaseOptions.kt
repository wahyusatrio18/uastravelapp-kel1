package com.uastravel.data.entity

class DatabaseOptions {

    companion object {
        const val databaseName = "UasPariwisata.db"
        const val databaseVersion = 2

        /* TABLES */
        const val tbUser = "tbUser"
        const val idUser = "idUser"
        const val emailUser = "emailUser"
        const val nameUser = "nameUser"
        const val passwordUser = "passwordUser"

        /* CREATE TABLE QUERY */
        const val createTableUser =
            ("CREATE TABLE $tbUser (" +
                    "$idUser INTEGER PRIMARY KEY AUTOINCREMENT" +
                    ", $emailUser TEXT" +
                    ", $nameUser TEXT" +
                    ", $passwordUser TEXT" +
                    ")")
    }
}