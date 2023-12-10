package data.source.remote

import data.source.remote.client.GithubApi
import data.source.remote.response.UsersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import utils.UiState

class RemoteDataSource(val githubApi: GithubApi) {
    inline fun <reified T : Any> getData(url: String): Flow<UiState<T>> = flow {
        githubApi.getData<T>(url)
            .onSuccess { response ->
                emit(UiState.Success(response))
            }
            .onFailure { throwable ->
                emit(UiState.Error(throwable.message ?: ""))
            }
    }.onStart {
        emit(UiState.Loading)
    }
}