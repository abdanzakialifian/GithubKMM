package domain.interactor

import app.cash.paging.PagingData
import domain.model.RepositoryItemModel
import domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow

class GetRepositories(private val githubRepository: GithubRepository) {
    operator fun invoke(username: String): Flow<PagingData<RepositoryItemModel>> =
        githubRepository.getRepositories(username = username)
}