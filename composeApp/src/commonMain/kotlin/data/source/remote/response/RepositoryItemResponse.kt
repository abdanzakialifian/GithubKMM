package data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryItemResponse(
	@SerialName("stargazers_count")
	val stargazersCount: Int? = null,

	@SerialName("is_template")
	val isTemplate: Boolean? = null,

	@SerialName("pushed_at")
	val pushedAt: String? = null,

	@SerialName("subscription_url")
	val subscriptionUrl: String? = null,

	@SerialName("language")
	val language: String? = null,

	@SerialName("branches_url")
	val branchesUrl: String? = null,

	@SerialName("issue_comment_url")
	val issueCommentUrl: String? = null,

	@SerialName("labels_url")
	val labelsUrl: String? = null,

	@SerialName("subscribers_url")
	val subscribersUrl: String? = null,

	@SerialName("permissions")
	val permissions: PermissionsResponse? = null,

	@SerialName("releases_url")
	val releasesUrl: String? = null,

	@SerialName("svn_url")
	val svnUrl: String? = null,

	@SerialName("id")
	val id: Int? = null,

	@SerialName("has_discussions")
	val hasDiscussions: Boolean? = null,

	@SerialName("archive_url")
	val archiveUrl: String? = null,

	@SerialName("git_refs_url")
	val gitRefsUrl: String? = null,

	@SerialName("forks_url")
	val forksUrl: String? = null,

	@SerialName("visibility")
	val visibility: String? = null,

	@SerialName("statuses_url")
	val statusesUrl: String? = null,

	@SerialName("ssh_url")
	val sshUrl: String? = null,

	@SerialName("full_name")
	val fullName: String? = null,

	@SerialName("size")
	val size: Int? = null,

	@SerialName("languages_url")
	val languagesUrl: String? = null,

	@SerialName("html_url")
	val htmlUrl: String? = null,

	@SerialName("collaborators_url")
	val collaboratorsUrl: String? = null,

	@SerialName("clone_url")
	val cloneUrl: String? = null,

	@SerialName("name")
	val name: String? = null,

	@SerialName("pulls_url")
	val pullsUrl: String? = null,

	@SerialName("default_branch")
	val defaultBranch: String? = null,

	@SerialName("hooks_url")
	val hooksUrl: String? = null,

	@SerialName("trees_url")
	val treesUrl: String? = null,

	@SerialName("tags_url")
	val tagsUrl: String? = null,

	@SerialName("private")
	val jsonMemberPrivate: Boolean? = null,

	@SerialName("contributors_url")
	val contributorsUrl: String? = null,

	@SerialName("has_downloads")
	val hasDownloads: Boolean? = null,

	@SerialName("notifications_url")
	val notificationsUrl: String? = null,

	@SerialName("open_issues_count")
	val openIssuesCount: Int? = null,

	@SerialName("description")
	val description: String? = null,

	@SerialName("created_at")
	val createdAt: String? = null,

	@SerialName("deployments_url")
	val deploymentsUrl: String? = null,

	@SerialName("keys_url")
	val keysUrl: String? = null,

	@SerialName("has_projects")
	val hasProjects: Boolean? = null,

	@SerialName("archived")
	val archived: Boolean? = null,

	@SerialName("has_wiki")
	val hasWiki: Boolean? = null,

	@SerialName("updated_at")
	val updatedAt: String? = null,

	@SerialName("comments_url")
	val commentsUrl: String? = null,

	@SerialName("stargazers_url")
	val stargazersUrl: String? = null,

	@SerialName("disabled")
	val disabled: Boolean? = null,

	@SerialName("git_url")
	val gitUrl: String? = null,

	@SerialName("has_pages")
	val hasPages: Boolean? = null,

	@SerialName("owner")
	val owner: OwnerResponse? = null,

	@SerialName("commits_url")
	val commitsUrl: String? = null,

	@SerialName("compare_url")
	val compareUrl: String? = null,

	@SerialName("git_commits_url")
	val gitCommitsUrl: String? = null,

	@SerialName("topics")
	val topics: List<String?>? = null,

	@SerialName("blobs_url")
	val blobsUrl: String? = null,

	@SerialName("git_tags_url")
	val gitTagsUrl: String? = null,

	@SerialName("merges_url")
	val mergesUrl: String? = null,

	@SerialName("downloads_url")
	val downloadsUrl: String? = null,

	@SerialName("has_issues")
	val hasIssues: Boolean? = null,

	@SerialName("url")
	val url: String? = null,

	@SerialName("contents_url")
	val contentsUrl: String? = null,

	@SerialName("mirror_url")
	val mirrorUrl: String? = null,

	@SerialName("milestones_url")
	val milestonesUrl: String? = null,

	@SerialName("teams_url")
	val teamsUrl: String? = null,

	@SerialName("security_and_analysis")
	val securityAndAnalysis: SecurityAndAnalysisResponse? = null,

	@SerialName("fork")
	val fork: Boolean? = null,

	@SerialName("issues_url")
	val issuesUrl: String? = null,

	@SerialName("events_url")
	val eventsUrl: String? = null,

	@SerialName("issue_events_url")
	val issueEventsUrl: String? = null,

	@SerialName("assignees_url")
	val assigneesUrl: String? = null,

	@SerialName("watchers_count")
	val watchersCount: Int? = null,

	@SerialName("node_id")
	val nodeId: String? = null,

	@SerialName("homepage")
	val homepage: String? = null,

	@SerialName("forks_count")
	val forksCount: Int? = null
)

@Serializable
data class SecurityAndAnalysisResponse(
	@SerialName("secret_scanning_push_protection")
	val secretScanningPushProtection: SecretScanningPushProtectionResponse? = null,

	@SerialName("secret_scanning")
	val secretScanning: SecretScanningResponse? = null,

	@SerialName("advanced_security")
	val advancedSecurity: AdvancedSecurityResponse? = null
)

@Serializable
data class SecretScanningResponse(
	@SerialName("status")
	val status: String? = null
)

@Serializable
data class SecretScanningPushProtectionResponse(
	@SerialName("status")
	val status: String? = null
)

@Serializable
data class AdvancedSecurityResponse(
	@SerialName("status")
	val status: String? = null
)

@Serializable
data class OwnerResponse(
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

@Serializable
data class PermissionsResponse(
	@SerialName("pull")
	val pull: Boolean? = null,

	@SerialName("admin")
	val admin: Boolean? = null,

	@SerialName("push")
	val push: Boolean? = null
)
