package ru.clonsaldafon.shoppinglistapp.presentation.view.groups

import ru.clonsaldafon.shoppinglistapp.data.model.Group

data class GroupsUiState(
    val groups: List<Group>? = null,
    val isLoading: Boolean = true
) {

    fun reset() = copy(
        groups = null,
        isLoading = true
    )

}
