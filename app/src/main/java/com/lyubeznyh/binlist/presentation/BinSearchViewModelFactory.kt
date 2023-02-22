package com.lyubeznyh.binlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class BinSearchViewModelFactory @Inject constructor(private val binSearchViewModel: BinSearchViewModel): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(binSearchViewModel::class.java)) {
            return binSearchViewModel as T
        } else {
            throw IllegalArgumentException("Unknown class name")
        }
    }
}
