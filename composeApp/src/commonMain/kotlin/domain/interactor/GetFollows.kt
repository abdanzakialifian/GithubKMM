package domain.interactor

import domain.model.FollowItemModel
import domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import utils.UiState

class GetFollows(private val githubRepository: GithubRepository) {
    operator fun invoke(username: String, type: String): Flow<UiState<List<FollowItemModel>>> =
        githubRepository.getFollows(username = username, type = type)
}