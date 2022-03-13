package com.example.composedepartment.ui.department.search

import androidx.lifecycle.ViewModel
import com.example.composedepartment.domain.Department
import com.example.composedepartment.domain.Project
import com.example.composedepartment.domain.Skill
import com.example.composedepartment.interactor.Search
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel : ViewModel() {

    private val _projects = MutableStateFlow(Search.projects)
    val projects: StateFlow<List<Project>> get() = _projects.asStateFlow()

    private val _departments = MutableStateFlow(Search.departments)
    val departments: StateFlow<List<Department>> get() = _departments.asStateFlow()

    private val _skills = MutableStateFlow(Search.skills)
    val skills: StateFlow<List<Skill>> get() = _skills.asStateFlow()

    private val _search = MutableStateFlow("")
    val search: StateFlow<String> get() = _search.asStateFlow()

    fun search(input: String) {
        _search.value = input
        // restore cache
        if (input.isEmpty()) {
            _projects.value = Search.projects
            _departments.value = Search.departments
            _skills.value = Search.skills
        } else {
            _projects.value = _projects.value.filter {
                contains(input = input, value = it.name)
            }
            _departments.value = _departments.value.filter {
                contains(input = input, value = it.name)
            }
            _skills.value = _skills.value.filter {
                contains(input = input, value = it.name)
            }
        }
    }

    private fun contains(input: String, value: String): Boolean {
        return value.lowercase().contains(input.lowercase())
    }
}