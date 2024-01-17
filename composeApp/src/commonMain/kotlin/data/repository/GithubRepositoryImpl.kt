package data.repository

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.map
import data.source.remote.RemoteDataSource
import data.source.remote.paging.UsersPagingSource
import data.source.remote.response.DetailResponse
import domain.model.DetailModel
import domain.model.UserItemModel
import domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import utils.DataMapper.mapToDetailModel
import utils.DataMapper.mapToUserItemModel
import utils.URL
import utils.UiState

class GithubRepositoryImpl(
    private val usersPagingSource: UsersPagingSource,
    private val remoteDataSource: RemoteDataSource,
) : GithubRepository {
    override fun getUsers(query: String): Flow<PagingData<UserItemModel>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            initialLoadSize = 10,
        ),
        pagingSourceFactory = {
            usersPagingSource.apply {
                setSearchQuery(query)
            }
        },
    ).flow.map { pagingData ->
        pagingData.map { data ->
            data.mapToUserItemModel()
        }
    }

    override fun getDetail(username: String): Flow<UiState<DetailModel>> =
        remoteDataSource.getData<DetailResponse>(URL.DETAIL_USER.replace("username", username))
            .map { uiState ->
                when (uiState) {
                    is UiState.Loading -> UiState.Loading
                    is UiState.Success -> UiState.Success(uiState.data.mapToDetailModel())
                    is UiState.Error -> UiState.Error(uiState.message)
                }
            }
}