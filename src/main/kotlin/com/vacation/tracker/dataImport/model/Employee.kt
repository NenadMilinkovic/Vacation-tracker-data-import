package com.vacation.tracker.dataImport.model

import jakarta.persistence.*
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
    @Column(name = "email", unique = true, nullable = false)
    val email: String = "",
    @Column(name = "password", nullable = false)
    val password: String = ""
) {
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    val vacations: List<Vacation> = emptyList()
}