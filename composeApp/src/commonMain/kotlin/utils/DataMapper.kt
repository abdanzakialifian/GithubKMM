package utils

import data.source.remote.response.DetailResponse
import data.source.remote.response.FollowItemResponse
import data.source.remote.response.UserItemResponse
import domain.model.DetailModel
import domain.model.FollowItemModel
import domain.model.UserItemModel

object DataMapper {
    fun UserItemResponse.mapToUserItemModel(): UserItemModel = UserItemModel(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        htmlUrl = htmlUrl,
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

    fun List<FollowItemResponse>.mapToFollowItemModel(): List<FollowItemModel> = this.map { map ->
        FollowItemModel(
            id = map.id,
            avatarUrl = map.avatarUrl,
            login = map.login,
        )
    }
}