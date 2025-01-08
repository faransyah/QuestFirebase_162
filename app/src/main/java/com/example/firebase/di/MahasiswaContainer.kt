package com.example.firebase.di

import com.example.firebase.repository.MahasiswaRepository
import com.example.firebase.repository.NetworkMahasiswaRepository
import com.google.firebase.firestore.FirebaseFirestore
// menambahkan interface appcontainer
interface AppContainer{
    val mahasiswaRepository: MahasiswaRepository
}
//implement member
class MahasiswaContainer : AppContainer{
    private val  firebase: FirebaseFirestore = FirebaseFirestore.getInstance()

    override val mahasiswaRepository: MahasiswaRepository by lazy {
        NetworkMahasiswaRepository(firebase)
    }
}