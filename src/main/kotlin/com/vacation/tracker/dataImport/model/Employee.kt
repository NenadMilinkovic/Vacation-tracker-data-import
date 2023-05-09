package com.vacation.tracker.dataImport.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.util.UUID

@Entity(name = "Employee")
@Table(name = "employee")
class Employee (
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    val id: UUID = UUID.randomUUID(),
    @Column(name = "email", nullable = false)
    var email: String = "",
    @Column(name = "password", nullable = false)
    var password: String = ""
)