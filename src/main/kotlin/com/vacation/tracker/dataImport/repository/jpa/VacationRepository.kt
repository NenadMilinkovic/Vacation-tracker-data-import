package com.vacation.tracker.dataImport.repository.jpa

import com.vacation.tracker.dataImport.model.Vacation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

interface VacationRepository: JpaRepository<Vacation, UUID> {
    fun findByEmployeeIdAndYear(employeeId: UUID, year: Int): Vacation?

    @Transactional
    @Modifying
    @Query(
        value = "Update vacation SET used_days = :usedDays, free_days = :freeDays WHERE employee_id = :employeeId " +
                "and year = :year",
        nativeQuery = true
    )
    fun update(
        @Param("employeeId") employeeId: UUID,
        @Param("usedDays") usedDays: Int,
        @Param("freeDays") freeDays: Int,
        @Param("year") year: Int
    )
}