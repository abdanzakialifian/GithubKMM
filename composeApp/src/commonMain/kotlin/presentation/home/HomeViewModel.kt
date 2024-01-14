package presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.interactor.GetUsers
import domain.model.UserItemModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(getUsersUseCase: GetUsers) : ViewModel() {
    var search by mutableStateOf("")

    private val searchQuery = MutableStateFlow("")

    fun onSearchQuery(query: String) {
        this.search = query
        this.searchQuery.value = query
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val getUsers: StateFlow<PagingData<UserItemModel>> = searchQuery.debounce(500L)
        .flatMapLatest { searchQuery ->
            getUsersUseCase(searchQuery)
        }
        .cachedIn(viewModelScope)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            PagingData.empty(),
        )
}