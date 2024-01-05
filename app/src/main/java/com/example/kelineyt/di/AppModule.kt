package com.example.kelineyt.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import com.example.kelineyt.firebase.FirebaseCommon
import com.example.kelineyt.util.Constants.INTRODUCTION_SP
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//입력시 앱 모듈클릭?
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton      //전체 앱에 걸쳐 하나의 인스턴스만 생성
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestoreDatabase() = Firebase.firestore


    //맨처음 start화면 한번나오고 사라지게(앱에만 적용)
    @Provides
    fun provideIntroductionSP(
        application: Application
    ) = application.getSharedPreferences(INTRODUCTION_SP, MODE_PRIVATE)


    @Provides
    @Singleton
    fun provideFirebaseCommon(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ) = FirebaseCommon(firestore,firebaseAuth)


    @Provides
    @Singleton
    fun provideStorage() = FirebaseStorage.getInstance().reference
}