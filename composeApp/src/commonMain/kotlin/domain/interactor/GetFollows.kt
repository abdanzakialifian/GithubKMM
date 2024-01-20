package domain.interactor

import app.cash.paging.PagingData
import domain.model.FollowItemModel
import domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow

class GetFollows(private val githubRepository: GithubRepository) {
    operator fun invoke(username: String, type: String): Flow<PagingData<FollowItemModel>> =
        githubRepository.getFollows(username = username, type = type)
}