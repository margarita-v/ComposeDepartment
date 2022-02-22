package com.example.composedepartment.domain

import androidx.compose.runtime.Immutable

@Immutable
data class Project(
    val id: String,
    val name: String,
    val description: String,
    val color: EntityColor,
    val leads: List<Employee>,
    val members: List<Employee>,
    val days: Int
) {
    val peopleCount: Int = leads.size + members.size
}