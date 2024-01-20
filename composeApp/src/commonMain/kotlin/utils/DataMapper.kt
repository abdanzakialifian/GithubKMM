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
import kotlin.random.Random
import org.jetbrains.skia.Color as ColorSkia

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

    fun List<RepositoryItemResponse>.mapToRepositoryItemModel(): List<RepositoryItemModel> =
        this.map { map ->
            val random = Random.Default
            val color = Color(
                ColorSkia.makeARGB(
                    255,
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256)
                )
            )
            RepositoryItemModel(
                id = map.id,
                name = map.name,
                language = map.language,
                visibility = map.visibility,
                color = color,
            )
        }
}