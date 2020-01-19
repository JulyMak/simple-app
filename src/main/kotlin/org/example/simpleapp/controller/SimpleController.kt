package org.example.simpleapp.controller

import org.example.simpleapp.response.SimpleResponse
import org.example.simpleapp.service.SimpleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/")
open class SimpleController {

    @Autowired
    lateinit var service: SimpleService

    @GetMapping
    @ResponseBody
    fun getDateOfProgrammerDay(
            @RequestParam(required = false) year: Int?,
            @RequestParam(required = false) currentDate: String?
    ): ResponseEntity<SimpleResponse> {
        return if (year != null && currentDate != null) {
            ResponseEntity.badRequest().build()
        } else if (year != null) {
            ResponseEntity.ok(service.getDateOfProgrammerDay(year))
        } else if (currentDate != null) {
            ResponseEntity.ok(service.getDaysToProgrammerDay(currentDate))
        } else {
            ResponseEntity.badRequest().build()
        }
    }
}