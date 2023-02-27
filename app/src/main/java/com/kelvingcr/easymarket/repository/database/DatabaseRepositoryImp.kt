package com.kelvingcr.easymarket.repository.database

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kelvingcr.easymarket.ui.models.ProductModel
import com.kelvingcr.easymarket.ui.models.PurchaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseRepositoryImp @Inject constructor(private val database: FirebaseDatabase, private val firebaseAuth: FirebaseAuth): DatabaseRepository {


    override suspend fun savePurchase(purchase: PurchaseModel): Task<Void> {

        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val database = Firebase.database
        val referece = database.getReference("purchases").child(userId).child(purchase.id!!).setValue(purchase)

        return withContext(Dispatchers.Default) {
            referece
        }

    }


    override suspend fun getAllPurchases(): DatabaseReference {

        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val database = Firebase.database

        return withContext(Dispatchers.Default) {
            database.getReference("purchases").child(userId)
        }
    }

}