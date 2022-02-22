package com.example.composedepartment.domain

import androidx.compose.runtime.Immutable

enum class EntityColor {
    BLUE,
    ORANGE,
    GREEN,
    PINK,
    GRAY
}

interface ColoredEntity {
    val name: String
    val color: EntityColor
}

@Immutable
data class Skill(
    override val name: String,
    override val color: EntityColor = EntityColor.GRAY
) : ColoredEntity

@Immutable
data class Department(
    override val name: String,
    override val color: EntityColor = EntityColor.GRAY
) : ColoredEntity