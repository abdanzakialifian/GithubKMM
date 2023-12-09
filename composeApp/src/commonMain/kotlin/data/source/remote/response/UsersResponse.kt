package data.source.remote.response

import kotlinx.serialization.SerialName

data class UsersResponse(
	@SerialName("gists_url")
	val gistsUrl: String? = null,

	@SerialName("repos_url")
	val reposUrl: String? = null,

	@SerialName("following_url")
	val followingUrl: String? = null,

	@SerialName("starred_url")
	val starredUrl: String? = null,

	@SerialName("login")
	val login: String? = null,

	@SerialName("followers_url")
	val followersUrl: String? = null,

	@SerialName("type")
	val type: String? = null,

	@SerialName("url")
	val url: String? = null,

	@SerialName("subscriptions_url")
	val subscriptionsUrl: String? = null,

	@SerialName("received_events_url")
	val receivedEventsUrl: String? = null,

	@SerialName("avatar_url")
	val avatarUrl: String? = null,

	@SerialName("events_url")
	val eventsUrl: String? = null,

	@SerialName("html_url")
	val htmlUrl: String? = null,

	@SerialName("site_admin")
	val siteAdmin: Boolean? = null,

	@SerialName("id")
	val id: Int? = null,

	@SerialName("gravatar_id")
	val gravatarId: String? = null,

	@SerialName("node_id")
	val nodeId: String? = null,

	@SerialName("organizations_url")
	val organizationsUrl: String? = null
)
