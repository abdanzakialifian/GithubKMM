package data.source.remote.paging

import app.cash.paging.PagingSource
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import app.cash.paging.PagingState
import data.source.remote.client.GithubApi
import data.source.remote.response.UserItemResponse
import data.source.remote.response.UsersResponse
import kotlinx.coroutines.delay
import utils.URL

class UsersPagingSource(private val githubApi: GithubApi) : PagingSource<Int, UserItemResponse>() {
    private var search: String = ""

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserItemResponse> {
        val currentPage = params.key ?: 1

        return try {
            val query = mapOf(
                "q" to search,
                "page" to currentPage,
                "per_page" to params.loadSize
            )
            val response = githubApi.getDataPaging<UsersResponse>(URL.SEARCH_USERS, query = query)

            delay(3000L)

            PagingSourceLoadResultPage(
                data = response.items ?: listOf(),
                prevKey = (currentPage - 1).takeIf { currentPage != 1 },
                nextKey = (currentPage + 1).takeIf {
                    (currentPage * params.loadSize) < (response.totalCount ?: 0)
                }
            )
        } catch (e: Exception) {
            PagingSourceLoadResultError(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserItemResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    fun setSearchQuery(query: String) {
        search = query.ifBlank { "a" }
    }
}