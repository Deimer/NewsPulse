package com.testdeymer.newspulse.features.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testdeymer.newspulse.di.IoDispatcher
import com.testdeymer.newspulse.extensions.failure
import com.testdeymer.newspulse.extensions.launchIn
import com.testdeymer.newspulse.extensions.map
import com.testdeymer.newspulse.extensions.success
import com.testdeymer.newspulse.mapper.toUiModel
import com.testdeymer.presentation.models.ItemUiModel
import com.testdeymer.usecase.hit.FetchHitByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

sealed class DetailUiState {
    data object Loading: DetailUiState()
    data object Success: DetailUiState()
    data class Error(val message: String? = null): DetailUiState()
}

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val fetchHitByIdUseCase: FetchHitByIdUseCase
): ViewModel() {

    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState = _detailUiState.asStateFlow()

    private val _itemUiState = MutableStateFlow(ItemUiModel())
    val itemUiState: StateFlow<ItemUiModel> = _itemUiState.asStateFlow()

    fun getDetail(objectId: String) {
        fetchHitByIdUseCase(objectId).map { hit ->
            _itemUiState.value = hit.toUiModel()
        }.success {
            _detailUiState.emit(DetailUiState.Success)
        }.failure { exception ->
            _detailUiState.emit(DetailUiState.Error(exception.message))
        }.launchIn(viewModelScope, ioDispatcher)
    }
}