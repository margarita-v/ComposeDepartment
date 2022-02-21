package com.example.composedepartment.domain

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import java.util.*

@Immutable
data class Employee(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val job: String = "",
    val projectId: String? = null,
    val previousProjectCount: Int = 0,
    val age: Int = 0,
    val gender: Gender = Gender.MALE,
    val experienceMonths: Int = 0,
    val phone: String = "+7 (931) 456 32 34",
    val city: String = "Санкт-Петербург",
    val department: String = "Маркетинг",
    @DrawableRes val photo: Int = -1, // string url for real app
    val skills: List<Skill> = listOf()
)

@Immutable
data class Skill(
    val name: String,
    val color: EntityColor = EntityColor.GRAY
)

enum class Gender {
    MALE,
    FEMALE
}