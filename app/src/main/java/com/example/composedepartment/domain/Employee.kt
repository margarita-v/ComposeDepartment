package com.example.composedepartment.domain

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

@Immutable
data class Employee(
    val name: String,
    val job: String,
    @DrawableRes val photo: Int, // string url for real app
    val skills: List<Skill>
)

@Immutable
data class Skill(
    val name: String,
    val color: EntityColor = EntityColor.GRAY
)

enum class EntityColor {
    BLUE,
    ORANGE,
    GREEN,
    PINK,
    GRAY
}