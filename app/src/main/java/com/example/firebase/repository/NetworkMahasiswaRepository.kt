package com.example.firebase.repository

import com.example.firebase.model.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

//Implement Member
// Buat Variabel FireStore
class NetworkMahasiswaRepository(
    private val firestore: FirebaseFirestore

): MahasiswaRepository {

    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa){
        try {
            firestore.collection("Mahasiswa") .add(mahasiswa) .await()
        }catch (e: Exception){
            throw Exception("Gagal menambahkan data mahasiswa: ${e.message}")
        }
    }
    override suspend fun getAllMahasiswa(): Flow<List<Mahasiswa>> = callbackFlow {

        val mhsCollection = firestore.collection("mahasiswa")
            .orderBy("nim", Query.Direction.DESCENDING)
            .addSnapshotListener{value, error ->
                if (value != null){
                    val mhsList = value.documents.mapNotNull {
                        it.toObject(Mahasiswa::class.java)!!
                    }
                    trySend(mhsList)
                }

            }
        awaitClose {
            mhsCollection.remove()
        }
    }
    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        try {
            firestore.collection("Mahasiswa")
                .document(nim)
                .set(mahasiswa)
                .await()
        } catch (e: Exception) {
            throw Exception("Gagal memperbarui data mahasiswa: ${e.message}")
        }
    }

    // Menghapus data mahasiswa berdasarkan NIM
    override suspend fun deleteMahasiswa(nim: String) {
        try {
            firestore.collection("Mahasiswa")
                .document(nim)
                .delete()
                .await()
        } catch (e: Exception) {
            throw Exception("Gagal menghapus data mahasiswa: ${e.message}")
        }
    }

    // Mendapatkan data mahasiswa berdasarkan NIM
    override suspend fun getMahasiswaByNim(nim: String): Flow<Mahasiswa> = callbackFlow {
        val mhsDocument = firestore.collection("mahasiswa")
            .document(nim)
            .addSnapshotListener{value, error ->
                if(value != null){
                    val mhs = value.toObject(Mahasiswa::class.java)!!
                    trySend(mhs)

                }
            }
        awaitClose{
            mhsDocument.remove()
        }
    }
}


// setelah ini buat homeviewmodel