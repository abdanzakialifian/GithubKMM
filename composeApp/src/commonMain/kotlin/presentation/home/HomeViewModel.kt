package presentation.home

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.interactor.GetUsers
import domain.model.UsersModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import utils.UiState

class HomeViewModel(private val getUsersUseCase: GetUsers) : ViewModel() {
    val getUsers: StateFlow<UiState<List<UsersModel>>> = getUsersUseCase().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000L), UiState.Loading
    )
}