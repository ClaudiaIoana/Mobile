package com.example.myapplication.model

import android.os.Parcelable
import java.io.Serializable
import java.time.LocalDate


data class AlimentModel(
    var name: String,
    var buy_date: LocalDate,
    var expiration_date: LocalDate,
    var aliment_quantity: Float,
    var status: String
) : Serializable {

    fun getId(): Triple<String, LocalDate, LocalDate> {
        return Triple(name, buy_date, expiration_date)
    }
}