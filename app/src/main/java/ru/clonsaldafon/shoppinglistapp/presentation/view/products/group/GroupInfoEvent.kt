package ru.clonsaldafon.shoppinglistapp.presentation.view.products.group

import ru.clonsaldafon.shoppinglistapp.data.model.Member

sealed class GroupInfoEvent {
    data class OnMembersLoaded(val value: List<Member>) : GroupInfoEvent()
}