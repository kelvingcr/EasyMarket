package com.kelvingcr.easymarket.repository.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AuthRepositoryImp @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthRepository {

    override suspend fun signUp(email: String, password: String) : Task<AuthResult> {
        val onSignUp = firebaseAuth.createUserWithEmailAndPassword(email, password)
        return withContext(Dispatchers.Default) {
            onSignUp
        }
    }

    override suspend fun signIn(email: String, password: String): Task<AuthResult> {
        val onSignIn = firebaseAuth.signInWithEmailAndPassword(email, password)
        return withContext(Dispatchers.Default) {
            onSignIn
        }
    }

    override suspend fun getCurrentUser(): FirebaseUser? {
        val user = firebaseAuth
        return withContext(Dispatchers.Default) {
            user.currentUser
        }
    }

    override suspend fun disconnectUser(): Boolean {
        val user = firebaseAuth
        if(user.currentUser != null) {
            user.signOut()
            return true
        }
        return false
    }
}