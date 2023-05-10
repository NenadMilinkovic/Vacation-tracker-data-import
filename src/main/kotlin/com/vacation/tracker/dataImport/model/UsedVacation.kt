package com.vacation.tracker.dataImport.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity(name = "UsedVacation")
@Table(name = "used_vacation")
class UsedVacation(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    val id: UUID = UUID.randomUUID(),
    @Column(name = "start_date")
    val startDate: Date = Date(),
    @Column(name = "end_date")
    val endDate: Date = Date(),
    @Column(name = "spend_days")
    val spendDays: Int = 0,
    @ManyToOne
    val vacation: Vacation = Vacation()
)