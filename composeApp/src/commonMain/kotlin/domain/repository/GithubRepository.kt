package domain.repository

import domain.model.UsersModel
import kotlinx.coroutines.flow.Flow
import utils.UiState


interface GithubRepository {
    fun getUsers(): Flow<UiState<List<UsersModel>>>
}