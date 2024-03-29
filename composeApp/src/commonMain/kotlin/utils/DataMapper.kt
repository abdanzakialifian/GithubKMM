package utils

import androidx.compose.ui.graphics.Color
import data.source.remote.response.DetailResponse
import data.source.remote.response.FollowItemResponse
import data.source.remote.response.RepositoryItemResponse
import data.source.remote.response.UserItemResponse
import domain.model.DetailModel
import domain.model.FollowItemModel
import domain.model.RepositoryItemModel
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

    fun FollowItemResponse.mapToFollowItemModel(): FollowItemModel = FollowItemModel(
        id = id,
        avatarUrl = avatarUrl,
        login = login,
    )

    fun RepositoryItemResponse.mapToRepositoryItemModel(color: Color): RepositoryItemModel =
        RepositoryItemModel(
            id = id,
            name = name,
            language = language,
            visibility = visibility,
            color = color,
        )
}