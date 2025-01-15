package com.example.firebase.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebase.model.Mahasiswa
import com.example.firebase.navigation.DestinasiNavigasi
import com.example.firebase.ui.viewmodel.DetailUiState

object DestinasiDetail : DestinasiNavigasi{

    override val route = "detail"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
    override val titleRes = "Detail Mhs"

}

@Composable
fun BodyDetailMhs(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState,
    retryAction: () -> Unit = {}
){
    when(detailUiState){
        is DetailUiState.Loading -> {
            OnLoading(modifier = modifier.fillMaxSize())
        }
        is DetailUiState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            ) { ItemDetailMhs(mahasiswa = detailUiState.mahasiswa)
            }
        }
        is DetailUiState.Error -> {
           OnError(
               retryAction = retryAction,
               modifier = modifier.fillMaxSize()
           )

        }

        else -> {
            Text("Unexpected state encountered")

        }
    }
}

@Composable
fun ItemDetailMhs(
    modifier: Modifier,
    mahasiswa: Mahasiswa
){
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailMhs(judul = "NIM", isinya = mahasiswa.nim)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "Nama", isinya = mahasiswa.nama)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "Alamat", isinya = mahasiswa.alamat)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "Jenis Kelamin", isinya = mahasiswa.jenisKelamin)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "Kelas", isinya = mahasiswa.kelas)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "Angkatan", isinya = mahasiswa.angkatan)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "Skripsi", isinya = mahasiswa.skripsi)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "Skripsi", isinya = mahasiswa.dosen)
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }

}

@Composable
fun ComponentDetailMhs(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya, fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}


