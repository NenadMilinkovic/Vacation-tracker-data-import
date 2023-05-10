package com.vacation.tracker.dataImport.service

import com.vacation.tracker.dataImport.model.Employee
import com.vacation.tracker.dataImport.repository.jpa.EmployeeRepository
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository
){

    fun createEmployeeProfile(employee: Employee): Employee = employeeRepository.save(employee)
}