package com.example.firebase.model

// Step 1
data class Mahasiswa(
    val nim: String,
    val nama: String,
    val alamat: String,
    val jenisKelamin: String,
    val kelas: String,
    val angkatan: String,
    val skripsi: String,
    val dosen: String
)

// Step 2 Constructor
{
    constructor(

    ):this("","","","","","","", "")
}