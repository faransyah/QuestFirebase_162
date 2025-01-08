package com.example.firebase

import android.app.Application
import com.example.firebase.di.AppContainer
import com.example.firebase.di.MahasiswaContainer

//setelah membuat networkmahasiswarepository, Lanjut masuk kesini

class MahasiswaApplications: Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container= MahasiswaContainer()
    }
}