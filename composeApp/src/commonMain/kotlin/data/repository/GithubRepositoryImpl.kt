package data.repository

import androidx.compose.ui.graphics.Color
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.map
import data.source.remote.RemoteDataSource
import data.source.remote.paging.RepositoriesPagingSource
import data.source.remote.paging.UsersPagingSource
import data.source.remote.response.DetailResponse
import data.source.remote.response.FollowItemResponse
import domain.model.DetailModel
import domain.model.FollowItemModel
import domain.model.RepositoryItemModel
import domain.model.UserItemModel
import domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import utils.DataMapper.mapToDetailModel
import utils.DataMapper.mapToFollowItemModel
import utils.DataMapper.mapToRepositoryItemModel
import utils.DataMapper.mapToUserItemModel
import utils.URL
import utils.UiState
import utils.mapState
import kotlin.random.Random
import org.jetbrains.skia.Color as ColorSkia

class GithubRepositoryImpl(
    private val usersPagingSource: UsersPagingSource,
    private val repositoriesPagingSource: RepositoriesPagingSource,
    private val remoteDataSource: RemoteDataSource,
) : GithubRepository {
    override fun getUsers(query: String): Flow<PagingData<UserItemModel>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            initialLoadSize = 10,
        ),
        pagingSourceFactory = {
            usersPagingSource.apply {
                setSearchQuery(query)
            }
        },
    ).flow.map { pagingData ->
        pagingData.map { data ->
            data.mapToUserItemModel()
        }
    }

    override fun getDetail(username: String): Flow<UiState<DetailModel>> =
        remoteDataSource
            .getData<DetailResponse>(URL.DETAIL_USER.replace("username", username))
            .mapState { detailResponse ->
                detailResponse.mapToDetailModel()
            }

    override fun getFollows(username: String, type: String): Flow<UiState<List<FollowItemModel>>> =
        remoteDataSource
            .getData<List<FollowItemResponse>>(
                URL.FOLLOW_USER.replace("username", username).replace("follow", type)
            )
            .mapState { followItemResponse ->
                followItemResponse.mapToFollowItemModel()
            }

    override fun getRepositories(username: String): Flow<PagingData<RepositoryItemModel>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            initialLoadSize = 10,
        ),
        pagingSourceFactory = {
            repositoriesPagingSource.apply {
                setUsername(username)
            }
        },
    ).flow.map { pagingData ->
        pagingData.map { data ->
            val random = Random.Default
            val color = Color(
                ColorSkia.makeARGB(
                    255,
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256)
                )
            )

            data.mapToRepositoryItemModel(color)
        }
    }
}