package com.kelvingcr.easymarket.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.kelvingcr.easymarket.repository.auth.AuthRepository
import com.kelvingcr.easymarket.repository.auth.AuthRepositoryImp
import com.kelvingcr.easymarket.repository.database.DatabaseRepository
import com.kelvingcr.easymarket.repository.database.DatabaseRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    fun provideFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImp): AuthRepository = impl

    @Provides
    fun provideFirebaseDatabase() : FirebaseDatabase = FirebaseDatabase.getInstance()

    @Provides
    fun provideDatabaseRespository(impl: DatabaseRepositoryImp): DatabaseRepository = impl
}