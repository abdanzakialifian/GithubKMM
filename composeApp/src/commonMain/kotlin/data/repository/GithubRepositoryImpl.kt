package data.repository

import data.source.remote.RemoteDataSource
import data.source.remote.response.UsersResponse
import domain.model.UsersModel
import domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import utils.DataMapper.mapToUsersModel
import utils.UiState

class GithubRepositoryImpl(private val remoteDataSource: RemoteDataSource) : GithubRepository {
    override fun getUsers(): Flow<UiState<List<UsersModel>>> =
        remoteDataSource.getData<List<UsersResponse>>("users").map { uiState ->
            when(uiState) {
                is UiState.Loading -> UiState.Loading
                is UiState.Success -> {
                    val mapper = uiState.data.mapToUsersModel()
                    UiState.Success(mapper)
                }
                is UiState.Error -> UiState.Error(uiState.message)
            }
        }
}