package di

import data.repository.GithubRepositoryImpl
import data.source.remote.RemoteDataSource
import data.source.remote.client.GithubApi
import data.source.remote.client.GithubClient
import data.source.remote.paging.FollowsPagingSource
import data.source.remote.paging.RepositoriesPagingSource
import data.source.remote.paging.UsersPagingSource
import domain.interactor.GetDetail
import domain.interactor.GetFollows
import domain.interactor.GetRepositories
import domain.interactor.GetUsers
import domain.repository.GithubRepository
import org.koin.dsl.module
import presentation.detail.DetailViewModel
import presentation.home.HomeViewModel

val dataModule = module {
    single { GithubClient }
    single { GithubApi(get()) }
    single { RemoteDataSource(get()) }
    factory { UsersPagingSource(get()) }
    factory { RepositoriesPagingSource(get()) }
    factory { FollowsPagingSource(get()) }
    factory<GithubRepository> { GithubRepositoryImpl(get(), get(), get(), get()) }
}

val useCaseModule = module {
    factory { GetUsers(get()) }
    factory { GetDetail(get()) }
    factory { GetFollows(get()) }
    factory { GetRepositories(get()) }
}

val viewModelModule = module {
    factory { HomeViewModel(get()) }
    factory { DetailViewModel(get(), get(), get()) }
}