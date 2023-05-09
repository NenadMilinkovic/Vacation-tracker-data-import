package com.vacation.tracker.dataImport.configuration

import com.vacation.tracker.dataImport.web.ApiKeyFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig(
    private val apiKeyFilter: ApiKeyFilter
) {

    @Value("\${api.key}")
    private val apiKey: String = ""

    @Bean
    fun apiFilter(): FilterRegistrationBean<ApiKeyFilter>? {
        val registrationBean = FilterRegistrationBean<ApiKeyFilter>()
        registrationBean.filter = apiKeyFilter
        registrationBean.addUrlPatterns("/api/*")
        return registrationBean
    }
}