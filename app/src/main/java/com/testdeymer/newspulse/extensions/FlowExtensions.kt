package com.testdeymer.newspulse.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

fun <T> Flow<Result<T>>.launchInViewModelScope(
    viewModel: ViewModel,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    onEach: suspend (Result<T>) -> Unit = {}
): Job = viewModel.viewModelScope.launch(dispatcher) {
    collect { result ->
        onEach(result)
    }
}

fun <T> Flow<Result<T>>.start(action: suspend () -> Unit): Flow<Result<T>> = onStart {
    action()
}

fun <T, R> Flow<Result<T>>.map(transform: suspend (T) -> R): Flow<Result<R>> = map { result ->
    result.fold(
        onSuccess = { data -> Result.success(transform(data)) },
        onFailure = { exception -> Result.failure(exception) }
    )
}

fun <T> Flow<Result<T>>.success(action: suspend (T) -> Unit): Flow<Result<T>> = onEach { result ->
    result.getOrNull()?.let { action(it) }
}

fun <T> Flow<Result<T>>.failure(action: suspend (Throwable) -> Unit): Flow<Result<T>> = onEach { result ->
    result.exceptionOrNull()?.let { action(it) }
}

suspend fun Throwable.isIOEx(action: suspend () -> Unit) {
    if (this is java.io.IOException) action()
}

suspend fun Throwable.default(action: suspend () -> Unit) {
    action()
}

fun <T> Flow<T>.launchIn(
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher
): Job = scope.launch(dispatcher) {
    collect {}
}