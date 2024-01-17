package utils

import data.source.remote.response.DetailResponse
import data.source.remote.response.UserItemResponse
import domain.model.DetailModel
import domain.model.UserItemModel

object DataMapper {
    fun UserItemResponse.mapToUserItemModel(): UserItemModel = UserItemModel(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        htmlUrl = htmlUrl
    )

    fun DetailResponse.mapToDetailModel(): DetailModel = DetailModel(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        followers = followers,
        following = following,
        publicRepos = publicRepos,
        name = name,
        bio = bio,
        company = company,
    )
}