package com.kelvingcr.easymarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kelvingcr.easymarket.databinding.ItemPurchaseAdapterBinding
import com.kelvingcr.easymarket.ui.models.PurchaseModel
import com.kelvingcr.easymarket.utils.formatCurrencyToBR

class HomeAdapter(
    private val onPurchaseClicked: (PurchaseModel) -> Unit
) : RecyclerView.Adapter<HomeViewHolder>() {

    private var purchasesList = mutableListOf<PurchaseModel>()

    fun setPurchasesList(lives: List<PurchaseModel>) {
        this.purchasesList = lives.toMutableList()
        notifyDataSetChanged()
    }

    fun getPurchasesList() : List<PurchaseModel> {
        return purchasesList
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPurchaseAdapterBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = purchasesList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val purchases = purchasesList[position]
        holder.bind(purchases, onPurchaseClicked)
    }
}

class HomeViewHolder(private val binding: ItemPurchaseAdapterBinding) : ViewHolder(binding.root) {

    fun bind(purchase: PurchaseModel, onPurchaseClicked: (PurchaseModel) -> Unit) {
        binding.tvPrice.text = formatCurrencyToBR(purchase.total.toDouble())
        binding.tvPurchaseName.text = purchase.purchaseName
        binding.tvDate.text = purchase.date


        binding.cardView.setOnClickListener {
            onPurchaseClicked(purchase)
        }
    }
}