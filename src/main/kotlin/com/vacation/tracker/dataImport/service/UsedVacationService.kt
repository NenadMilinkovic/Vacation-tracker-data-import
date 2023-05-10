package com.vacation.tracker.dataImport.service

import com.vacation.tracker.dataImport.model.Employee
import com.vacation.tracker.dataImport.model.UsedVacation
import com.vacation.tracker.dataImport.repository.jpa.EmployeeRepository
import com.vacation.tracker.dataImport.repository.jpa.UsedVacationRepository
import com.vacation.tracker.dataImport.repository.jpa.VacationRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class UsedVacationService(
    private val usedVacationRepository: UsedVacationRepository,
    private val employeeRepository: EmployeeRepository,
    private val vacationRepository: VacationRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun addUsedVacationDays(email: String, startDate: Date, endDate: Date) {
        val employee: Employee = employeeRepository.findByEmail(email)

        val calendar = Calendar.getInstance()
        calendar.time = startDate
        val year = calendar.get(Calendar.YEAR)


       var vacation = vacationRepository.findByEmployeeIdAndYear(employee.id, year)?.let {
           val diff: Long = endDate.time - startDate.time
           val spendDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toInt()
           val usedVacation = UsedVacation(
               startDate = startDate,
               endDate = endDate,
               spendDays = spendDays,
               vacation = it
           )

           if (it.freeDays - spendDays < 0) {
               logger.error("Employee $email don't have enough free days")
           } else {
               usedVacationRepository.save(usedVacation)
               it.usedDays += spendDays
               it.freeDays -= spendDays
               vacationRepository.save(it)
           }
       } ?: run {
           logger.error("Employee ${employee.email} don't have inserted vacation days for $year year")
       }
        logger.info("Success insert used vacation days for ${employee.email}")
    }
}