package com.vacation.tracker.dataImport.repository.jpa

import com.vacation.tracker.dataImport.model.UsedVacation
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UsedVacationRepository: JpaRepository<UsedVacation, UUID>