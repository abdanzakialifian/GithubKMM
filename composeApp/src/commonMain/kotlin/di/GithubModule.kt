package di

import data.repository.GithubRepositoryImpl
import data.source.remote.RemoteDataSource
import data.source.remote.client.GithubApi
import data.source.remote.client.GithubClient
import data.source.remote.paging.UsersPagingSource
import domain.interactor.GetUsers
import domain.repository.GithubRepository
import org.koin.dsl.module
import presentation.home.HomeViewModel

val dataModule = module {
    single { GithubClient }
    single { GithubApi(get()) }
    single { RemoteDataSource(get()) }
    factory <GithubRepository> { GithubRepositoryImpl(get()) }
    factory { UsersPagingSource(get()) }
}

val useCaseModule = module {
    factory { GetUsers(get()) }
}

val viewModelModule = module {
    factory { HomeViewModel(get()) }
}