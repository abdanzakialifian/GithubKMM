package utils

import data.source.remote.response.UserItemResponse
import domain.model.UserItemModel

object DataMapper {
    fun UserItemResponse.mapToUserItemModel(): UserItemModel = UserItemModel(
        id = id,
        login = login,
        avatarUrl = avatarUrl
    )
}