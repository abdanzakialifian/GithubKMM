package data.source.remote.client

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse

class GithubApi(val githubClient: GithubClient) {
    suspend fun getData(url: String, query: Map<String, Any>? = null): HttpResponse =
        githubClient.createHttpClient().get(url) {
            query?.forEach { map ->
                parameter(map.key, map.value)
            }
        }

    suspend inline fun <reified T : Any> getDataPaging(
        url: String,
        query: Map<String, Any>? = null
    ): T = githubClient.createHttpClient().get(url) {
        query?.forEach { map ->
            parameter(map.key, map.value)
        }
    }.body()
}