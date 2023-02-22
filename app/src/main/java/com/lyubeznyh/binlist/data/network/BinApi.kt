package com.lyubeznyh.binlist.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface BinApi {
    @GET("/{binNumber}")
    suspend fun getBinInfo( @Path("binNumber") binNumber:Int): BinResponse
}
