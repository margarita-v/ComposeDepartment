package com.example.composedepartment.interactor

import com.example.composedepartment.R
import com.example.composedepartment.domain.Employee
import com.example.composedepartment.domain.EntityColor
import com.example.composedepartment.domain.Skill

/** For UI stub data purpose only */
object Employees {
    val employees = listOf(
        Employee(
            name = "Ольга Кудрявцева",
            job = "Аналитик-стажёр",
            age = 21,
            photo = R.drawable.ic_person_1,
            skills = listOf(
                Skill("Маркетинг", EntityColor.BLUE),
                Skill("Начало карьеры")
            )
        ),
        Employee(
            name = "Игорь Крутой",
            job = "IOS Разработчик",
            age = 23,
            photo = R.drawable.ic_person_2,
            skills = listOf(
                Skill("Разработка", EntityColor.ORANGE),
                Skill("Swift"),
                Skill("IOS SDK"),
                Skill("Apple Developer"),
                Skill("Figma")
            )
        ),
        Employee(
            name = "Алеся Патрикеевна",
            job = "UX/UI Дизайнер",
            age = 25,
            photo = R.drawable.ic_person_3,
            skills = listOf(
                Skill("Дизайн", EntityColor.GREEN),
                Skill("Figma"),
                Skill("Adobe"),
                Skill("Illustration")
            )
        ),
        Employee(
            name = "Иннокентий Христорожденный",
            job = "Бухгалтер",
            age = 31,
            photo = R.drawable.ic_person_4,
            skills = listOf(
                Skill("Бухгалтерия", EntityColor.PINK),
                Skill("Отчётность"),
                Skill("Кассовые операции")
            )
        )
    )
}