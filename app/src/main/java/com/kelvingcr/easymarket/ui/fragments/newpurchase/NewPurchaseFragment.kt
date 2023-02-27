package com.kelvingcr.easymarket.ui.fragments.newpurchase

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kelvingcr.easymarket.databinding.FragmentNewPurchaseBinding
import com.kelvingcr.easymarket.ui.fragments.newpurchase.viewmodel.NewPurchaseViewModel
import com.kelvingcr.easymarket.ui.models.ProductModel
import com.kelvingcr.easymarket.ui.models.PurchaseModel
import com.kelvingcr.easymarket.utils.Constants.Companion.ON_FAILED_SAVE_PURCHASE
import com.kelvingcr.easymarket.utils.Constants.Companion.ON_SUCESS_SAVE_PURCHASE
import com.kelvingcr.easymarket.utils.getDateFormated
import com.kelvingcr.easymarket.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewPurchaseFragment : Fragment() {

    private val binding by lazy { FragmentNewPurchaseBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<NewPurchaseViewModel>()

    private lateinit var adapter: ArrayAdapter<ProductModel>
    private var listProducts= ArrayList<ProductModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, listProducts)
        binding.listView.adapter = adapter

        onClickFabAddProduct()
        onClickFabSavePurchase()
        onBundle()

        viewModel.newPurchaseLiveData.observe(viewLifecycleOwner, Observer {
            showToast(it)
        })
    }

    private fun sumPurchase(products: List<ProductModel>) = products.sumOf { it.price }

    private fun addProduct(name: String, price: Double, quantity: Int = 1) {
        val item = ProductModel(name, price, quantity)
        listProducts.add(item)
        adapter.notifyDataSetChanged()
    }

    private fun onClickFabAddProduct() {
        binding.fabAddProduct.setOnClickListener {
            val name = binding.edtProductName.text.toString()
            val price = binding.edtProductPrice.text.toString()

            if(name.isNotEmpty() && name.isNotBlank()) {
                if(price.isNotEmpty() && price.isNotBlank()) {
                    addProduct(name, price.toDouble())
                } else {
                    binding.edtProductPrice.apply {
                        error = getString(com.kelvingcr.easymarket.R.string.onFieldError)
                        requestFocus()
                    }
                }
            } else {
                binding.edtProductName.apply {
                    error = getString(com.kelvingcr.easymarket.R.string.onFieldError)
                    requestFocus()
                }
            }
        }
    }

    private fun onClickFabSavePurchase() {
        binding.fabSave.setOnClickListener {
            val purchaseName = binding.edtPurchaseName.text.toString()
            val listSize = listProducts.count()


            if(purchaseName.isNotBlank() && purchaseName.isNotEmpty()) {
                if(listSize > 0) {
                    showToast(ON_SUCESS_SAVE_PURCHASE)
                    viewModel.save(
                        PurchaseModel(
                            purchaseName = purchaseName,
                            products = listProducts,
                            date = getDateFormated(),
                            total = sumPurchase(listProducts).toString()))
                } else {
                    showToast(ON_FAILED_SAVE_PURCHASE)
                }
            }else {
                binding.edtPurchaseName.apply {
                    error = getString(com.kelvingcr.easymarket.R.string.onFieldError)
                    requestFocus()
                }
            }
        }

    }

    private fun onBundle() {
        val purchaseModel = arguments?.getParcelable<PurchaseModel>("purchaseModel")
        if (purchaseModel != null) {

            binding.edtPurchaseName.apply {
                setText(purchaseModel.purchaseName)
                isFocusable = false
                isFocusableInTouchMode = false
            }

            binding.edtProductPrice.apply {
                isFocusable = false
                isFocusableInTouchMode = false
            }
            binding.edtProductName.apply {
                isFocusable = false
                isFocusableInTouchMode = false
            }

            purchaseModel.products.forEach {
                addProduct(it.name, it.price)
            }

            binding.listView.apply {
                isFocusable = false
                isFocusableInTouchMode = false
            }
            binding.fabSave.apply {
                isClickable = false
                isEnabled = false
            }
            binding.fabAddProduct.apply {
                isClickable = false
                isEnabled = false
            }

        }
    }


}