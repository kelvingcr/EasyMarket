package com.kelvingcr.easymarket.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductModel(
    val name: String = "",
    val price: Double = 0.0,
    val quantity: Int = 1,
) : Parcelable {

    override fun toString(): String {
        return "Item: $name, Valor: R$$price, Quantidade: $quantity"
    }
}