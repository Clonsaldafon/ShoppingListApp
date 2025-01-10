package ru.clonsaldafon.shoppinglistapp.presentation.view.products.group

import ru.clonsaldafon.shoppinglistapp.data.model.Member

data class GroupInfoUiState(
    val members: List<Member>? = null,
    val isLoading: Boolean = true
) {

    fun reset() = copy(
        members = null,
        isLoading = true
    )

}
