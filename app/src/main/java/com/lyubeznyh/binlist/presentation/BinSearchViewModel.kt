package com.lyubeznyh.binlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lyubeznyh.binlist.data.BinRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class BinSearchViewModel @Inject constructor(private val repository: BinRepository):ViewModel() {

    private val _state = MutableStateFlow<StateBinSearchUi>(StateBinSearchUi.Pending)
    val state = _state.asStateFlow()

    val binHistory = repository.getBinHistory()

    fun loadBinInfo(binNumber:Int){
        val expectationHandler = CoroutineExceptionHandler{ _, _->
            _state.update { StateBinSearchUi.Error }
        }
        viewModelScope.launch(expectationHandler) {
            _state.update { StateBinSearchUi.Pending }
            val response = repository.getBinInfo(binNumber)
            _state.update { insertBinHistory(binNumber)
                StateBinSearchUi.Success(response)
            }
        }
    }

    private suspend fun insertBinHistory( bin:Int){
        repository.insertBin(bin)
    }
}
