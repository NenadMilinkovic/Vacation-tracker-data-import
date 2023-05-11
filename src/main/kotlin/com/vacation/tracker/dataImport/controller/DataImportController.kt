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
        @RequestParam("file", required = true) file: MultipartFile
    ) {

        employeeService.createEmployeeProfile(file)
    }

    @PostMapping("/importVacationDays")
    fun importVacationDays(
        @RequestParam("file", required = true) file: MultipartFile
    ) {

        vacationService.addVacationDays(file)
    }

    @PostMapping("/importUsedVacationDays")
    fun importUsedVacationDays(
        @RequestParam("file", required = true) file: MultipartFile
    ) {

        usedVacationService.addUsedVacationDays(file)
    }
}
