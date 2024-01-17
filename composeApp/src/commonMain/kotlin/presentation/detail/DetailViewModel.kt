package presentation.detail

import domain.interactor.GetDetail
import domain.model.DetailModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import utils.UiState

class DetailViewModel(private val getDetailUseCase: GetDetail) : ViewModel() {
    private val _username = MutableStateFlow("")

    fun setUsername(username: String) {
        this._username.value = username
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val getDetail: StateFlow<UiState<DetailModel>> = _username.flatMapLatest { username ->
        getDetailUseCase(username)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        UiState.Loading,
    )
}