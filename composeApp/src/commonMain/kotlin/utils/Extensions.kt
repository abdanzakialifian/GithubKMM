package utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun String?.orHyphen(): String = if (this.isNullOrEmpty()) "-" else this

fun <T : Any, R : Any> Flow<UiState<T>>.mapState(data: (T) -> R): Flow<UiState<R>> {
    return this.map { uiState ->
        when (uiState) {
            is UiState.Loading -> UiState.Loading
            is UiState.Success -> UiState.Success(data(uiState.data))
            is UiState.Error -> UiState.Error(uiState.message)
        }
    }
}