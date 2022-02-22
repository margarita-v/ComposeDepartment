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
}