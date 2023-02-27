package com.kelvingcr.easymarket.ui.fragments.newpurchase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kelvingcr.easymarket.repository.database.DatabaseRepository
import com.kelvingcr.easymarket.ui.models.ProductModel
import com.kelvingcr.easymarket.ui.models.PurchaseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewPurchaseViewModel @Inject constructor(private val databaseRepository: DatabaseRepository): ViewModel() {

    val newPurchaseLiveData = MutableLiveData<String>()

    fun save(purchase: PurchaseModel) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.Default) {
                databaseRepository.savePurchase(purchase)
            }
            response.addOnSuccessListener {
                newPurchaseLiveData.value = "Sucesso"
            }.addOnFailureListener {
                newPurchaseLiveData.value = "Falha"
            }
        }
    }

}