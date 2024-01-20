package domain.interactor

import domain.model.RepositoryItemModel
import domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import utils.UiState

class GetRepositories(private val githubRepository: GithubRepository) {
    operator fun invoke(username: String): Flow<UiState<List<RepositoryItemModel>>> =
        githubRepository.getRepositories(username = username)
}