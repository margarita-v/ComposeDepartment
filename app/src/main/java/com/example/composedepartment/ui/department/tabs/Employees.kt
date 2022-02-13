package com.example.composedepartment.ui.department.tabs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composedepartment.domain.Employee

@Composable
internal fun Employees(employees: List<Employee>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        employees.forEach {
            item {
                Text(it.name)
            }
        }
    }
}