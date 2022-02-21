package com.example.composedepartment.interactor

import com.example.composedepartment.domain.Employee
import com.example.composedepartment.domain.EntityColor
import com.example.composedepartment.domain.Project

/** For UI stub data purpose only */
object Projects {
    private const val description = "Такое понимание ситуации восходит\n" +
            "к Эл Райс, при этом BTL транслирует конструктивный медиамикс, отвоевывая свою долю рынка."
    val leads: List<Employee> = Employees.employees.take(3)

    val projects = listOf(
        Project(
            id = "1",
            name = "Название",
            description = description,
            color = EntityColor.ORANGE,
            leads = leads,
            members = generateEmployees(24),
            days = 4
        ),
        Project(
            id = "2",
            name = "Второй проект",
            description = description,
            color = EntityColor.BLUE,
            leads = leads,
            members = generateEmployees(8),
            days = 5
        ),
        Project(
            id = "3",
            name = "Очень длинное название проекта на две строки",
            description = description,
            color = EntityColor.GREEN,
            leads = leads,
            members = generateEmployees(14),
            days = 31
        )
    )

    private fun generateEmployees(count: Int): List<Employee> =
        generateSequence { Employee() }.take(count).toList()
}