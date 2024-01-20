package domain.repository

import app.cash.paging.PagingData
import domain.model.DetailModel
import domain.model.FollowItemModel
import domain.model.RepositoryItemModel
import domain.model.UserItemModel
import kotlinx.coroutines.flow.Flow
import utils.UiState


interface GithubRepository {
    fun getUsers(query: String): Flow<PagingData<UserItemModel>>

    fun getDetail(username: String): Flow<UiState<DetailModel>>

    fun getFollows(username: String, type: String): Flow<UiState<List<FollowItemModel>>>

    fun getRepositories(username: String): Flow<UiState<List<RepositoryItemModel>>>
}