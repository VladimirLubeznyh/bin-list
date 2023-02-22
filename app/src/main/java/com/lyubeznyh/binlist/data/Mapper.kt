package com.lyubeznyh.binlist.data

import android.content.Context
import com.lyubeznyh.binlist.R
import com.lyubeznyh.binlist.data.local.BinHistoryEntity
import com.lyubeznyh.binlist.data.network.BinResponse
import com.lyubeznyh.binlist.domain.BinModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
const val DEFAULT_COORDINATE = 0
fun BinResponse.mapToBinModel(context: Context): BinModel = BinModel(
    numberLength = this.number.length?.toString() ?: context.getString(R.string.unknown),
    numberLuhn = number.luhn?.let {
        if (it) context.getString(R.string.yes)
        else context.getString(R.string.no)
    } ?: context.getString(R.string.unknown),
    scheme = scheme ?: context.getString(R.string.unknown),
    type = type ?: context.getString(R.string.unknown),
    brand = brand ?: context.getString(R.string.unknown),
    prepaid = prepaid?.let {
        if (it) context.getString(R.string.yes) else context.getString(R.string.no)
    } ?: context.getString(R.string.unknown),
    countryName = this.country.name ?: context.getString(R.string.unknown),
    countryLatitude = this.country.latitude ?: DEFAULT_COORDINATE,
    countryLongitude = this.country.longitude ?: DEFAULT_COORDINATE,
    bankName = this.bank.name ?: context.getString(R.string.unknown),
    bankCity = bank.city ?: context.getString(R.string.unknown),
    bankUrl = this.bank.url ?: context.getString(R.string.unknown),
    bankPhone = this.bank.phone ?: context.getString(R.string.unknown)
)
fun Flow<List<BinHistoryEntity>>.toFlowBinNumbers():Flow<List<Int>> = this.map { list->list.map { it.bin } }

