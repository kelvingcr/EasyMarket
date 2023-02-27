package com.kelvingcr.easymarket.repository.database

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.kelvingcr.easymarket.ui.models.ProductModel
import com.kelvingcr.easymarket.ui.models.PurchaseModel

interface DatabaseRepository {

    suspend fun savePurchase(purchase: PurchaseModel) : Task<Void>
    suspend fun getAllPurchases() : DatabaseReference
}