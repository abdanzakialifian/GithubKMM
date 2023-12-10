package data.source.remote

import data.source.remote.client.GithubApi
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import utils.UiState

class RemoteDataSource(val githubApi: GithubApi) {
    inline fun <reified T : Any> getData(
        url: String,
        query: Map<String, Any>? = null
    ): Flow<UiState<T>> = flow {
        val response = githubApi.getData(url, query)
        when (response.status) {
            HttpStatusCode.OK,
            HttpStatusCode.Accepted,
            HttpStatusCode.Created -> emit(UiState.Success(response.body<T>()))

            else -> emit(UiState.Error(response.bodyAsText()))
        }
    }.onStart {
        emit(UiState.Loading)
    }.catch { throwable ->
        emit(UiState.Error(throwable.message ?: ""))
    }.flowOn(Dispatchers.IO)
}