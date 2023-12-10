package domain.interactor

import domain.model.UsersModel
import domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import utils.UiState

class GetUsers(private val githubRepository: GithubRepository) {
    operator fun invoke(): Flow<UiState<List<UsersModel>>> = githubRepository.getUsers()
}