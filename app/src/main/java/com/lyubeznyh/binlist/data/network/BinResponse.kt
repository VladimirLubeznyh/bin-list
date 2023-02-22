package com.lyubeznyh.binlist.data.network

import com.google.gson.annotations.SerializedName

data class BinResponse(
    @SerializedName("number") val number: NumberResponse,
    @SerializedName("scheme") val scheme: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("brand") val brand: String?,
    @SerializedName("prepaid") val prepaid: Boolean?,
    @SerializedName("country") val country: CountryResponse,
    @SerializedName("bank") val bank: BankResponse,
)

data class NumberResponse(
    @SerializedName("length") val length: Int?,
    @SerializedName("luhn") val luhn: Boolean?,
)

data class CountryResponse(
    @SerializedName("numeric") val length: String?,
    @SerializedName("alpha2") val alpha2: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("emoji") val emoji: String?,
    @SerializedName("currency") val currency: String?,
    @SerializedName("latitude") val latitude: Int?,
    @SerializedName("longitude") val longitude: Int?,
)

data class BankResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("city") val city: String?,
)
