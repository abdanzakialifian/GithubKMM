package domain.repository

import app.cash.paging.PagingData
import domain.model.DetailModel
import domain.model.UserItemModel
import kotlinx.coroutines.flow.Flow
import utils.UiState


interface GithubRepository {
    fun getUsers(query: String): Flow<PagingData<UserItemModel>>

    fun getDetail(username: String): Flow<UiState<DetailModel>>
}