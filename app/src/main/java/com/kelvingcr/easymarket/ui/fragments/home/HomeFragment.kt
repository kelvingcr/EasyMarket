package com.kelvingcr.easymarket.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kelvingcr.easymarket.databinding.FragmentHomeBinding
import com.kelvingcr.easymarket.ui.fragments.search.viewmodel.SearchFragmentViewModel
import com.kelvingcr.easymarket.ui.models.PurchaseModel
import com.kelvingcr.easymarket.utils.formatCurrencyToBR
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<SearchFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllPurchases()
    }


    override fun onResume() {
        viewModel.getAllPurchasesLiveData.observe(viewLifecycleOwner) {
            val total = it.sumOf { it.total.toDouble() }
            binding.tvPurchaseTotal.text = formatCurrencyToBR(total)
        }
        super.onResume()
    }

}