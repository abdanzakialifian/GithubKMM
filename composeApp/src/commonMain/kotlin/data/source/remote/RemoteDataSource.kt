package data.source.remote

import data.source.remote.client.GithubApi
import data.source.remote.response.UsersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import utils.UiState

class RemoteDataSource(private val githubApi: GithubApi) {
    fun getUsers(): Flow<UiState<List<UsersResponse>>> = flow {
        githubApi.getUsers()
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