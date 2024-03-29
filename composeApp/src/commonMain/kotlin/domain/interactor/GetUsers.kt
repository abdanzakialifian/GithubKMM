package domain.interactor

import app.cash.paging.PagingData
import domain.model.UserItemModel
import domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow

class GetUsers(private val githubRepository: GithubRepository) {
    operator fun invoke(query: String): Flow<PagingData<UserItemModel>> =
        githubRepository.getUsers(query = query)
}