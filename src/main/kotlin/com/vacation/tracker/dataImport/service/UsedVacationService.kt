package com.vacation.tracker.dataImport.service

import com.opencsv.CSVReader
import com.vacation.tracker.dataImport.model.Employee
import com.vacation.tracker.dataImport.model.UsedVacation
import com.vacation.tracker.dataImport.repository.jpa.EmployeeRepository
import com.vacation.tracker.dataImport.repository.jpa.UsedVacationRepository
import com.vacation.tracker.dataImport.repository.jpa.VacationRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.InputStreamReader
import java.text.SimpleDateFormat
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
    fun addUsedVacationDays(file: MultipartFile) {

        if (file.originalFilename!! != "used_vacation_dates.csv") {
            throw Exception("Imported file is not valid")
        }

        if (file.isEmpty)
            throw Exception("Empty file")

        val reader = CSVReader(InputStreamReader(file.inputStream))
        var record: Array<String>?
        val dataFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.ENGLISH)
        reader.readNext()
        while (reader.readNext().also { record = it } != null) {
            parseCsv(
                record!![0],
                dataFormat.parse(record!![1]),
                dataFormat.parse(record!![2]))
        }

    }

    private fun parseCsv(email: String, startDate: Date, endDate: Date) {
        val employee: Employee = employeeRepository.findByEmail(email)

        val calendar = Calendar.getInstance()
        calendar.time = startDate
        val year = calendar.get(Calendar.YEAR)

        vacationRepository.findByEmployeeIdAndYear(employee.id, year)?.let {
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

                logger.info("Success insert used vacation days for ${employee.email}")
            }
        } ?: run {
            logger.error("Employee ${employee.email} don't have inserted vacation days for $year year")
        }
    }
}