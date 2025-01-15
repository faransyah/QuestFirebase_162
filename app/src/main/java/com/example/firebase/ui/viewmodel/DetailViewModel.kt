package com.example.firebase.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase.model.Mahasiswa
import com.example.firebase.repository.MahasiswaRepository
import com.example.firebase.ui.view.DestinasiDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class DetailUiState {
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel(private val mhs: MahasiswaRepository, savedStateHandle: SavedStateHandle)
    :ViewModel() {

    private val _nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM] )

    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState

    init {
        getDetailMahasiswa()
    }


    fun getDetailMahasiswa() {
        viewModelScope.launch {
            try {
                _detailUiState.value = DetailUiState.Loading

                val mahasiswa = mhs.getMahasiswaByNim(_nim)

                if (mahasiswa != null) {
                    _detailUiState.value = DetailUiState.Success(mahasiswa)
                } else {
                    _detailUiState.value = DetailUiState.Error
                }
            } catch (e: Exception) {
                _detailUiState.value = DetailUiState.Error
            }
        }

    }
}
