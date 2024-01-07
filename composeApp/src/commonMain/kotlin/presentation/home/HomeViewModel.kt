package presentation.home

import app.cash.paging.PagingData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.interactor.GetUsers
import domain.model.UserItemModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(getUsersUseCase: GetUsers) : ViewModel() {
    val getUsers: StateFlow<PagingData<UserItemModel>> = getUsersUseCase().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000L), PagingData.empty()
    )
}