package domain.model

data class DetailModel(
    val id: Int? = null,
    val login: String? = null,
    val avatarUrl: String? = null,
    val followers: Int? = null,
    val following: Int? = null,
    val publicRepos: Int? = null,
    val name: String? = null,
    val bio: String? = null,
    val company: String? = null,
)