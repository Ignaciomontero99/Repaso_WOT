package com.ignmonlop.repasowot.api

data class Tank (
    val id: Int,
    val model: String,
    val weight: Int,
    val manufacturer: String,
    val imageUrl: String,
    val originCountry: String
)