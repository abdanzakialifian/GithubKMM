package data.source.remote.paging

import androidx.paging.PagingSource
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import app.cash.paging.PagingState
import data.source.remote.client.GithubApi
import data.source.remote.response.FollowItemResponse
import utils.URL

class FollowsPagingSource(private val githubApi: GithubApi) :
    PagingSource<Int, FollowItemResponse>() {

    private var username: String = ""
    private var type: String = ""

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FollowItemResponse> {
        val currentPage = params.key ?: 1

        return try {
            val query = mapOf(
                "page" to currentPage,
                "per_page" to params.loadSize
            )

            val response = githubApi.getDataPaging<List<FollowItemResponse>>(
                URL.FOLLOW_USER.replace("username", username).replace("follow", type),
                query = query
            )

            PagingSourceLoadResultPage(
                data = response,
                prevKey = (currentPage - 1).takeIf { currentPage != 1 },
                nextKey = (currentPage + 1).takeIf {
                    response.isNotEmpty()
                }
            )
        } catch (e: Exception) {
            PagingSourceLoadResultError(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, FollowItemResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    fun setData(username: String, type: String) {
        this.username = username
        this.type = type
    }
}