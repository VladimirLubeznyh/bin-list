package com.lyubeznyh.binlist.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.lyubeznyh.binlist.R
import com.lyubeznyh.binlist.databinding.FragmentBinSearchBinding
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class BinSearchFragment : Fragment(), HasAndroidInjector {

    private var _binding: FragmentBinSearchBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: BinSearchViewModelFactory

    private val viewModel: BinSearchViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBinSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeState()
        observeBinHistory()
        onClickWebpage()
        onClickCoordinates()
        onClickNumberPhone()
        listenToBinSearch()
    }

    private fun listenToBinSearch() {
        binding.actvBinSearch.addTextChangedListener {
            if (it != null && it.toString().length == 8) viewModel.loadBinInfo(
                it.toString().toInt()
            )
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                renderState(binding, it)
            }
        }
    }

    private fun onClickCoordinates() {
        binding.textLayout.tvCoordinates.setOnClickListener {
            viewModel.state.value.let {
                if (it is StateBinSearchUi.Success) {
                    showMap(it.data.countryLatitude, it.data.countryLongitude)
                }
            }
        }
    }

    private fun onClickNumberPhone() {
        binding.textLayout.tvBankNumberPhone.setOnClickListener {
            viewModel.state.value.let {
                if (it is StateBinSearchUi.Success) dialPhoneNumber(it.data.bankPhone)
            }
        }
    }

    private fun onClickWebpage() {
        binding.textLayout.tvUrlBank.setOnClickListener {
            viewModel.state.value.let {
                if (it is StateBinSearchUi.Success) openWebPage(it.data.bankUrl)
            }
        }
    }

    private fun openWebPage(url: String) {
        val webpage = Uri.parse("https:$url")
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(intent)
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val phone = Uri.parse("tel:$phoneNumber")
        val intent = Intent(Intent.ACTION_VIEW, phone)
        startActivity(intent)
    }

    private fun showMap(lat: Int, lon: Int) {
        val coordinates = Uri.parse("geo:$lat,$lon")
        val intent = Intent(Intent.ACTION_VIEW, coordinates)
        startActivity(intent)
    }

    private fun observeBinHistory() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.binHistory.collect {
                binding.actvBinSearch.setAdapter(
                    ArrayAdapter(
                        requireContext(),
                        R.layout.list_item,
                        it
                    )
                )
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun androidInjector(): AndroidInjector<Any> = fragmentInjector
}
