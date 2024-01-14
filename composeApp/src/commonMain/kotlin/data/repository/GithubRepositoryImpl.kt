package data.repository

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.map
import data.source.remote.paging.UsersPagingSource
import domain.model.UserItemModel
import domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import utils.DataMapper.mapToUserItemModel

class GithubRepositoryImpl(private val usersPagingSource: UsersPagingSource) : GithubRepository {
    override fun getUsers(query: String): Flow<PagingData<UserItemModel>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            initialLoadSize = 10
        ),
        pagingSourceFactory = {
            usersPagingSource.apply {
                setSearchQuery(query)
            }
        }
    ).flow.map { pagingData ->
        pagingData.map { data ->
            data.mapToUserItemModel()
        }
    }
}