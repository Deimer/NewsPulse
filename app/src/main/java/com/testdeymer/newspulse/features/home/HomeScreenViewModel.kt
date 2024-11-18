package com.testdeymer.newspulse.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testdeymer.newspulse.di.IoDispatcher
import com.testdeymer.newspulse.mapper.toUiModel
import com.testdeymer.presentation.models.ItemUiModel
import com.testdeymer.repository.utils.OnResult
import com.testdeymer.usecase.hit.DeleteHitByIdUseCase
import com.testdeymer.usecase.hit.FetchAllHitsUseCase
import com.testdeymer.usecase.hit.GetHitsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed class HomeUiState {
    data object Loading: HomeUiState()
    data object Success: HomeUiState()
    data object ConnectionError: HomeUiState()
    data class Error(val message: String? = null): HomeUiState()
}

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val getHitsUseCase: GetHitsUseCase,
    private val fetchAllHitsUseCase: FetchAllHitsUseCase,
    private val deleteHitByIdUseCase: DeleteHitByIdUseCase
): ViewModel() {

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState = _homeUiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private val _itemList = MutableStateFlow<List<ItemUiModel>>(emptyList())
    val itemList: StateFlow<List<ItemUiModel>> = _itemList.asStateFlow()

    init {
        fetchAllHits()
    }

    private fun fetchAllHits() {
        CoroutineScope(ioDispatcher).launch {
            when(val result = fetchAllHitsUseCase.invoke()) {
                is OnResult.Success -> {
                    _itemList.value = result.data.toUiModel()
                    _homeUiState.emit(HomeUiState.Success)
                }
                is OnResult.Error -> {
                    if (result.exception is IOException) {
                        _homeUiState.emit(HomeUiState.ConnectionError)
                    } else {
                        _homeUiState.emit(HomeUiState.Error(result.exception.message.orEmpty()))
                    }
                }
            }
        }
    }

    fun getHits() {
        CoroutineScope(ioDispatcher).launch {
            _isRefreshing.emit(true)
            when(val result = getHitsUseCase.invoke()) {
                is OnResult.Success -> {
                    _itemList.value = result.data.toUiModel()
                    _isRefreshing.emit(false)
                }
                is OnResult.Error -> {
                    _isRefreshing.emit(false)
                    if (result.exception is IOException) {
                        _homeUiState.emit(HomeUiState.ConnectionError)
                    } else {
                        _homeUiState.emit(HomeUiState.Error(result.exception.message.orEmpty()))
                    }
                }
            }
        }
    }

    fun deleteHitById(objectId: String) {
        viewModelScope.launch {
            deleteHitByIdUseCase.invoke(objectId)
            _itemList.value = _itemList.value.filterNot { it.id == objectId }
        }
    }
}