package com.vacation.tracker.dataImport.controller

import com.vacation.tracker.dataImport.annotations.ApiKeyProtected
import com.vacation.tracker.dataImport.dto.EmployeeDto
import com.vacation.tracker.dataImport.dto.mapper.EmployeeMapper
import com.vacation.tracker.dataImport.service.EmployeeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api/dataImport")
@ApiKeyProtected
class EmployeeController(
    private val employeeService: EmployeeService,
    private val employeeMapper: EmployeeMapper
) {

    @PostMapping("/employee")
    fun createEmployeeProfile(
        @RequestBody employee: EmployeeDto
    ): ResponseEntity<EmployeeDto> {
        val uri: URI = URI.create(
            ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/dataImport/employee/${employee.email}")
                .toUriString()
        )
        employeeService.createEmployeeProfile(employeeMapper.convertToModel(employee))
        return ResponseEntity.created(uri).build()
    }
}