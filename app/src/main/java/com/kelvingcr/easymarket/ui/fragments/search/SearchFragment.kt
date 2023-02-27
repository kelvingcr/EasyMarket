package com.kelvingcr.easymarket.ui.fragments.search

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.R
import androidx.navigation.fragment.findNavController
import com.kelvingcr.easymarket.adapter.HomeAdapter
import com.kelvingcr.easymarket.databinding.FragmentSearchBinding
import com.kelvingcr.easymarket.ui.fragments.newpurchase.NewPurchaseFragment
import com.kelvingcr.easymarket.ui.fragments.search.viewmodel.SearchFragmentViewModel
import com.kelvingcr.easymarket.ui.models.PurchaseModel
import com.kelvingcr.easymarket.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<SearchFragmentViewModel>()

    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        setupObservers()
        setupSearchView()

    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(str: String?): Boolean {
                if (str.equals("")) {
                    viewModel.getAllPurchases()
                    return true
                }
                filterList(str)
                return true
            }
        })
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<PurchaseModel>()
            for (i in adapter.getPurchasesList()) {
                if (i.purchaseName.lowercase().contains(query.lowercase())
                    || i.date.lowercase().contains(query.lowercase())
                ) {
                    filteredList.add(i)
                }
            }
            adapter.setPurchasesList(filteredList)
        }


    }

    private fun setupRecycler() {
        adapter = HomeAdapter() {
            val bundle = Bundle().apply {
                putParcelable("purchaseModel", it)
            }
            findNavController().navigate(
                com.kelvingcr.easymarket.R.id.action_global_newPurchaseFragment,
                bundle
            )
        }
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getAllPurchasesLiveData.observe(viewLifecycleOwner, Observer {
            if (it.size > 0) {
                adapter.setPurchasesList(it)
                onProgressBar(false)

                if (it != null) onFoundPurchaseTextView(false)
            }
        })
    }

    private fun onProgressBar(visibility: Boolean) {
        if (visibility) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun onFoundPurchaseTextView(visibility: Boolean) {
        if (visibility) {
            binding.tvNotFoundPurchase.visibility = View.VISIBLE
        } else {
            binding.tvNotFoundPurchase.visibility = View.GONE
        }

    }

    override fun onResume() {
        onProgressBar(true)
        viewModel.getAllPurchases()
        if (adapter.getPurchasesList().isEmpty()) onFoundPurchaseTextView(true)
        super.onResume()
    }

}