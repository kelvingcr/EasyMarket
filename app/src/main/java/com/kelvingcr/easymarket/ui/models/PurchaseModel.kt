package com.kelvingcr.easymarket.ui.models

import android.os.Parcelable
import com.google.firebase.database.FirebaseDatabase
import kotlinx.parcelize.Parcelize

@Parcelize
data class PurchaseModel (
    var id: String? = "",
    val purchaseName: String = "",
    val products: List<ProductModel> = arrayListOf(),
    val date: String = "",
    val total: String = ""
    ) : Parcelable {

            init {
                val databaseReference = FirebaseDatabase.getInstance().reference
                this.id = databaseReference.push().key
            }

        }