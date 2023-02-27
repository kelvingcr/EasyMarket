package com.kelvingcr.easymarket.ui.fragments.search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.kelvingcr.easymarket.repository.database.DatabaseRepository
import com.kelvingcr.easymarket.ui.models.PurchaseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) : ViewModel() {

    val getAllPurchasesLiveData = MutableLiveData<ArrayList<PurchaseModel>>()

    fun getAllPurchases() {

         val list: ArrayList<PurchaseModel> = arrayListOf()

        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.Default) {
                databaseRepository.getAllPurchases()
            }
            response.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()
                    if (snapshot != null) {
                        for(snap in snapshot.children) {
                            val purchaseModel = snap.getValue(PurchaseModel::class.java) as PurchaseModel
                            list.add(purchaseModel)
                        }
                    }
                    getAllPurchasesLiveData.value = list
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

}