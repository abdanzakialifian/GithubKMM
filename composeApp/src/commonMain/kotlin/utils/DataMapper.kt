package utils

import data.source.remote.response.UsersResponse
import domain.model.UsersModel

object DataMapper {
    fun List<UsersResponse>.mapToUsersModel(): List<UsersModel> = this.map { data ->
        UsersModel(
            id = data.id,
            login = data.login,
            avatarUrl = data.avatarUrl
        )
    }
}