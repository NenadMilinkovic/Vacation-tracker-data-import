package com.vacation.tracker.dataImport.service

import com.opencsv.CSVReader
import com.vacation.tracker.dataImport.model.Employee
import com.vacation.tracker.dataImport.repository.jpa.EmployeeRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.InputStreamReader

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository
){

    fun createEmployeeProfile(file: MultipartFile) {

        if (file.originalFilename!! != "employee_profiles.csv") {
            throw Exception("Imported file is not valid")
        }

        if (file.isEmpty)
            throw Exception("Empty file")

        val reader = CSVReader(InputStreamReader(file.inputStream))
        var record: Array<String>?
        reader.readNext()
        reader.readNext()
        while (reader.readNext().also { record = it } != null) {
            employeeRepository.save(Employee(email = record!![0], password = record!![1]))
        }
    }
}