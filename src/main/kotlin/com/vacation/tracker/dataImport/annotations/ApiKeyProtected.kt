package com.vacation.tracker.dataImport.annotations

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION
)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiKeyProtected