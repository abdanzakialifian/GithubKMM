package di

import data.repository.GithubRepositoryImpl
import data.source.remote.RemoteDataSource
import data.source.remote.client.GithubApi
import data.source.remote.client.GithubClient
import domain.interactor.GetUsers
import domain.repository.GithubRepository
import org.koin.dsl.module
import presentation.home.HomeViewModel

val dataModule = module {
    single { GithubClient }
    single { GithubApi(get()) }
    single { RemoteDataSource(get()) }
    single<GithubRepository> { GithubRepositoryImpl(get()) }
}

val useCaseModule = module {
    factory { GetUsers(get()) }
}

val viewModelModule = module {
    factory { HomeViewModel(get()) }
}