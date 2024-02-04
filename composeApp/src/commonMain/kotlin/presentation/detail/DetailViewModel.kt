package presentation.detail

import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import domain.interactor.GetDetail
import domain.interactor.GetFollows
import domain.interactor.GetRepositories
import domain.model.DetailModel
import domain.model.FollowItemModel
import domain.model.RepositoryItemModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import utils.UiState

class DetailViewModel(
    private val getDetailUseCase: GetDetail,
    private val getFollowsUseCase: GetFollows,
    private val getRepositoriesUseCase: GetRepositories,
) : ViewModel() {
    private val _usernameDetail = MutableStateFlow("")

    private val _usernameRepositories = MutableStateFlow("")

    private val _usernameFollowers = MutableStateFlow("")

    private val _usernameFollowing = MutableStateFlow("")

    private val _type = MutableStateFlow("")

    fun setUsernameDetail(username: String) {
        this._usernameDetail.value = username
    }

    fun setUsernameRepositories(username: String) {
        this._usernameRepositories.value = username
    }

    fun setUsernameFollowers(username: String) {
        this._usernameFollowers.value = username
    }

    fun setUsernameFollowing(username: String) {
        this._usernameFollowing.value = username
    }

    fun setType(type: String) {
        this._type.value = type
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val getDetail: StateFlow<UiState<DetailModel>> = _usernameDetail.flatMapLatest { username ->
        getDetailUseCase(username)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        UiState.Loading,
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val getFollowers: StateFlow<PagingData<FollowItemModel>> =
        combine(_usernameFollowers, _type) { username, type ->
            Pair(username, type)
        }.flatMapLatest { pair ->
            val username = pair.first
            val type = pair.second
            getFollowsUseCase(username, type)
        }.cachedIn(viewModelScope).stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            PagingData.empty(),
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val getFollowing: StateFlow<PagingData<FollowItemModel>> =
        combine(_usernameFollowing, _type) { username, type ->
            Pair(username, type)
        }.flatMapLatest { pair ->
            val username = pair.first
            val type = pair.second
            getFollowsUseCase(username, type)
        }.cachedIn(viewModelScope).stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            PagingData.empty(),
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val getRepositories: StateFlow<PagingData<RepositoryItemModel>> = _usernameRepositories
        .flatMapLatest { username ->
            getRepositoriesUseCase(username)
        }
        .cachedIn(viewModelScope)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            PagingData.empty(),
        )
}