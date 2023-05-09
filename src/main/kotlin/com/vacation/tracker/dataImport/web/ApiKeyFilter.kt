package com.vacation.tracker.dataImport.web

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.Objects

@Component
class ApiKeyFilter: OncePerRequestFilter(){

    @Value("\${api.key}")
    private val apiKey: String = ""

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestApiKey = request.getHeader("X-API-KEY")

        if (Objects.equals(requestApiKey, apiKey)) {
            filterChain.doFilter(request, response)
        } else {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write("Invalid API key.")
        }
    }
}