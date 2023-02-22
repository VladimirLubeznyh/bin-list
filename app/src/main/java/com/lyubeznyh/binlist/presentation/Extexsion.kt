package com.lyubeznyh.binlist.presentation

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isGone
import com.lyubeznyh.binlist.R
import com.lyubeznyh.binlist.databinding.FragmentBinSearchBinding
import com.lyubeznyh.binlist.domain.BinModel

fun TextView.isUnknown(context: Context): Boolean = this.text == context.getString(R.string.unknown)

fun BinSearchFragment.renderState(binding: FragmentBinSearchBinding, state: StateBinSearchUi) {
    when (state) {
        is StateBinSearchUi.Success -> {
            binding.textLayout.root.isGone = false
            binding.tilBinSearch.isErrorEnabled = false
            state.data.setView(binding, requireContext())
        }
        StateBinSearchUi.Error -> {
            binding.textLayout.root.isGone = true
            binding.tilBinSearch.error = getString(R.string.error)
        }
        StateBinSearchUi.Pending -> {
            binding.textLayout.root.isGone = true
        }
    }
}

fun BinModel.setView(binding: FragmentBinSearchBinding, context: Context) {
    with(binding.textLayout) {
        tvBrandValue.text = brand
        tvTypeValue.text = type
        tvPrepaidValue.text = prepaid
        tvSchemeValue.text = scheme
        tvLengthValue.text = numberLength
        tvLuhnValue.text = numberLuhn
        tvCoordinates.text = if (countryLatitude != 0 && countryLongitude != 0) {
            context.getString(
                R.string.latitude_longitude,
                countryLatitude,
                countryLongitude
            )
        } else context.getString(R.string.unknown)
        tvCountryValue.text = countryName
        tvBankName.text = bankName
        tvUrlBank.text = bankUrl
        tvBankNumberPhone.text = bankPhone
        tvUrlBank.apply { isEnabled = !isUnknown(context) }
        tvBankNumberPhone.apply { isEnabled = !isUnknown(context) }
        tvCoordinates.apply { isEnabled = !isUnknown(context) }
    }
}
