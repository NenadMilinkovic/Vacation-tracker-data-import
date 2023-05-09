package com.vacation.tracker.dataImport.repository.jpa

import com.vacation.tracker.dataImport.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface EmployeeRepository : JpaRepository<Employee, UUID>
