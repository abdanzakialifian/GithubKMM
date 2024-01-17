package domain.interactor

import domain.model.DetailModel
import domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import utils.UiState

class GetDetail(private val githubRepository: GithubRepository) {
    operator fun invoke(username: String): Flow<UiState<DetailModel>> =
        githubRepository.getDetail(username)
}