package com.example.composedepartment.domain

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

@Immutable
data class Employee(
    val name: String = "",
    val job: String = "",
    @DrawableRes val photo: Int = -1, // string url for real app
    val skills: List<Skill> = listOf()
)

@Immutable
data class Skill(
    val name: String,
    val color: EntityColor = EntityColor.GRAY
)