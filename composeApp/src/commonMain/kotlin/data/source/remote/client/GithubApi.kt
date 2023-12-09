package data.source.remote.client

import data.source.remote.response.UsersResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class GithubApi(private val githubClient: GithubClient) {
    suspend fun getUsers(): Result<List<UsersResponse>> =
        githubClient.createHttpClient().get("users").body()
}