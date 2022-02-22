package com.example.composedepartment.interactor

import com.example.composedepartment.domain.Department
import com.example.composedepartment.domain.EntityColor
import com.example.composedepartment.domain.Skill

object Search {
    val projects = Projects.projects
    val departments = listOf(
        Department(
            name = "Маркетинг",
            color = EntityColor.BLUE
        ),
        Department(
            name = "Разработка",
            color = EntityColor.ORANGE
        ),
        Department(
            name = "Дизайн",
            color = EntityColor.GREEN
        ),
        Department(
            name = "Бухгалтерия",
            color = EntityColor.PINK
        )
    )
    val skills = listOf(
        Skill("Начало карьеры"),
        Skill("Figma"),
        Skill("Swift"),
        Skill("Adobe"),
        Skill("IOS SDK"),
        Skill("Illustration"),
        Skill("Apple developer"),
        Skill("Отчётность"),
        Skill("Кассовые операции")
    )
}