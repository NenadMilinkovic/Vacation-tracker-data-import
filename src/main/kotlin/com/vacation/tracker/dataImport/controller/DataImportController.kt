package com.vacation.tracker.dataImport.controller

import com.opencsv.CSVReader
import com.vacation.tracker.dataImport.annotations.ApiKeyProtected
import com.vacation.tracker.dataImport.model.Employee
import com.vacation.tracker.dataImport.service.EmployeeService
import com.vacation.tracker.dataImport.service.UsedVacationService
import com.vacation.tracker.dataImport.service.VacationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*


@RestController
@RequestMapping("/api/dataImport")
@ApiKeyProtected
class DataImportController(
    private val employeeService: EmployeeService,
    private val vacationService: VacationService,
    private val usedVacationService: UsedVacationService
) {

    @PostMapping("/importEmployee")
    fun importEmployee(
        @RequestParam("file") file: MultipartFile
    ): ResponseEntity<*> {

        val reader = CSVReader(InputStreamReader(file.inputStream))
        var record: Array<String>?
        reader.readNext()
        reader.readNext()
        while (reader.readNext().also { record = it } != null) {
            employeeService.createEmployeeProfile(Employee(email = record!![0], password = record!![1]))
        }

        return ResponseEntity.ok().build<Any>()
    }

    @PostMapping("/importVacationDays")
    fun importVacationDays(
        @RequestParam("file") file: MultipartFile
    ): ResponseEntity<*> {

        val reader = CSVReader(InputStreamReader(file.inputStream))
        var record: Array<String>?
        val year = reader.readNext()[1].toInt()
        reader.readNext()
        while (reader.readNext().also { record = it } != null) {
            vacationService.addVacationDays(record!![0], year, record!![1].toInt() )
        }

        return ResponseEntity.ok().build<Any>()
    }

    @PostMapping("/importUsedVacationDays")
    fun importUsedVacationDays(
        @RequestParam("file") file: MultipartFile
    ): ResponseEntity<*> {

        val reader = CSVReader(InputStreamReader(file.inputStream))
        var record: Array<String>?
        val dataFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.ENGLISH)
        reader.readNext()
        while (reader.readNext().also { record = it } != null) {
            usedVacationService.addUsedVacationDays(
                record!![0],
                dataFormat.parse(record!![1]),
                dataFormat.parse(record!![2]))
        }

        return ResponseEntity.ok().build<Any>()
    }
}