package domain.repository

import app.cash.paging.PagingData
import domain.model.UserItemModel
import kotlinx.coroutines.flow.Flow


interface GithubRepository {
    fun getUsers(): Flow<PagingData<UserItemModel>>
}