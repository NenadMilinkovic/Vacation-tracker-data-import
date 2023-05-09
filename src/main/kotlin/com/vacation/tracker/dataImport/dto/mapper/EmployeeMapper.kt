package com.vacation.tracker.dataImport.dto.mapper

import com.vacation.tracker.dataImport.dto.EmployeeDto
import com.vacation.tracker.dataImport.model.Employee
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface EmployeeMapper {

    fun convertToDto(employee: Employee) : EmployeeDto
    fun convertToModel(employeeDto: EmployeeDto) : Employee
}