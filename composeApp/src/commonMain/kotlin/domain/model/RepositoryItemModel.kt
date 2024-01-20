package domain.model

import androidx.compose.ui.graphics.Color

data class RepositoryItemModel(
    val id: Int? = null,
    val name: String? = null,
    val language: String? = null,
    val visibility: String? = null,
    val color: Color? = null
)
