package com.lyubeznyh.binlist.presentation

import com.lyubeznyh.binlist.domain.BinModel

sealed class StateBinSearchUi {
    object Pending:StateBinSearchUi()
    object Error : StateBinSearchUi()
    data class Success(val data: BinModel):StateBinSearchUi()
}
