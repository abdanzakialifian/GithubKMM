package data.source.remote.client

import io.ktor.client.call.body
import io.ktor.client.request.get

class GithubApi(val githubClient: GithubClient) {
    suspend inline fun <reified T : Any> getData(url: String): Result<T> = runCatching {
        return@runCatching githubClient.createHttpClient().get(url).body()
    }
}