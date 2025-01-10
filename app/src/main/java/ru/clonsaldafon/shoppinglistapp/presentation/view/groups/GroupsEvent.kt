package ru.clonsaldafon.shoppinglistapp.presentation.view.groups

import ru.clonsaldafon.shoppinglistapp.data.model.Group

sealed class GroupsEvent {
    data class OnGroupsLoaded(val value: List<Group>) : GroupsEvent()
}