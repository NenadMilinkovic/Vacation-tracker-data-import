package com.vacation.tracker.dataImport.service

import com.vacation.tracker.dataImport.model.Employee
import com.vacation.tracker.dataImport.model.Vacation
import com.vacation.tracker.dataImport.repository.jpa.EmployeeRepository
import com.vacation.tracker.dataImport.repository.jpa.VacationRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VacationService(
    private val employeeRepository: EmployeeRepository,
    private val vacationRepository: VacationRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun addVacationDays(email: String, year: Int, days: Int) {
        val employee: Employee = employeeRepository.findByEmail(email)

        employee.vacations.find {
            it.year == year && it.employee.email == email
        }?.let { logger.error("Already inserted vacation days for $year year") } ?: run {
            val vacation = Vacation(year = year, days = days, freeDays = days, usedDays = 0, employee = employee)
            vacationRepository.save(vacation)
        }
    }
}