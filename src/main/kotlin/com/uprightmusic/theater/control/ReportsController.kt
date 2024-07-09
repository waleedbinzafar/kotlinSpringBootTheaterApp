package com.uprightmusic.theater.control

import com.uprightmusic.theater.services.ReportingService
import jakarta.websocket.server.PathParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.memberFunctions

@Controller
@RequestMapping("/reports")
class ReportsController {

    @Autowired
    lateinit var reportingService: ReportingService

    private fun getListOfReports() = reportingService::class.declaredFunctions.map{it.name}

    @RequestMapping("")
    fun main() = ModelAndView("reports", mapOf("reports" to getListOfReports()))

    @RequestMapping("/getReport")
    fun getReport(@PathParam("report") report : String) : ModelAndView {
        val matchedReport = reportingService::class.declaredMemberFunctions.filter { it.name == report }.firstOrNull()
        val result = matchedReport?.call(reportingService) ?: ""
        return ModelAndView("reports", mapOf("reports" to getListOfReports(), "result" to result))
    }
}