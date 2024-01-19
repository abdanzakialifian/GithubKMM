package presentation.detail

import domain.interactor.GetDetail
import domain.interactor.GetFollows
import domain.model.DetailModel
import domain.model.FollowItemModel
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
) : ViewModel() {
    private val _username = MutableStateFlow("")
    private val _type = MutableStateFlow("")

    fun setUsername(username: String) {
        this._username.value = username
    }

    fun setType(type: String) {
        this._type.value = type
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val getDetail: StateFlow<UiState<DetailModel>> = _username.flatMapLatest { username ->
        getDetailUseCase(username)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        UiState.Loading,
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val getFollows: StateFlow<UiState<List<FollowItemModel>>> =
        combine(_username, _type) { username, type ->
            Pair(username, type)
        }.flatMapLatest { pair ->
            val username = pair.first
            val type = pair.second
            getFollowsUseCase(username, type)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            UiState.Loading,
        )
}