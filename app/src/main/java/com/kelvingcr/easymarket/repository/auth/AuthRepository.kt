package com.kelvingcr.easymarket.repository.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


interface AuthRepository {

    suspend fun signUp(email: String, password: String): Task<AuthResult>
    suspend fun signIn(email: String, password: String): Task<AuthResult>

    suspend fun getCurrentUser() : FirebaseUser?
    suspend fun disconnectUser() : Boolean
}