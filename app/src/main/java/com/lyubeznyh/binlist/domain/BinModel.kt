package com.lyubeznyh.binlist.domain

data class BinModel(
    val numberLength: String,
    val numberLuhn: String,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: String,
    val countryName: String,
    val countryLatitude: Int,
    val countryLongitude: Int,
    val bankName: String,
    val bankUrl: String,
    val bankPhone: String,
    val bankCity: String,
)
