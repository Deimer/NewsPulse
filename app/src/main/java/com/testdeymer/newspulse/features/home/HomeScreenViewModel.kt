package com.testdeymer.newspulse.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testdeymer.newspulse.di.IoDispatcher
import com.testdeymer.newspulse.extensions.default
import com.testdeymer.newspulse.extensions.isIOEx
import com.testdeymer.newspulse.extensions.failure
import com.testdeymer.newspulse.extensions.launchIn
import com.testdeymer.newspulse.extensions.map
import com.testdeymer.newspulse.extensions.success
import com.testdeymer.newspulse.extensions.start
import com.testdeymer.newspulse.mapper.toUiModel
import com.testdeymer.presentation.models.ItemUiModel
import com.testdeymer.usecase.hit.DeleteHitByIdUseCase
import com.testdeymer.usecase.hit.FetchAllHitsUseCase
import com.testdeymer.usecase.hit.GetHitsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
        fetchAllHitsUseCase()
            .map { hits ->
                _itemList.value = hits.toUiModel()
            }.success {
                _homeUiState.emit(HomeUiState.Success)
            }.failure { exception ->
                exception.isIOEx {
                    _homeUiState.emit(HomeUiState.ConnectionError)
                }
                exception.default {
                    _homeUiState.emit(HomeUiState.Error(exception.message.orEmpty()))
                }
            }.launchIn(viewModelScope, ioDispatcher)
    }

    fun getHits() {
        getHitsUseCase().start {
            _isRefreshing.emit(true)
        }.map { hits ->
            _itemList.value = hits.toUiModel()
        }.success {
            _isRefreshing.emit(false)
        }.failure { exception ->
            exception.isIOEx {
                _homeUiState.emit(HomeUiState.ConnectionError)
            }
            exception.default {
                _homeUiState.emit(HomeUiState.Error(exception.message.orEmpty()))
            }
        }.launchIn(viewModelScope, ioDispatcher)
    }

    fun deleteHitById(objectId: String) {
        deleteHitByIdUseCase(objectId).success {
            _itemList.value = _itemList.value.filterNot { it.id == objectId }
        }.failure { exception ->
            _homeUiState.emit(HomeUiState.Error(exception.message.orEmpty()))
        }.launchIn(viewModelScope, ioDispatcher)
    }
}