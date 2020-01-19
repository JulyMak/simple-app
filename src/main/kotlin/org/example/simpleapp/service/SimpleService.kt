package org.example.simpleapp.service

import org.example.simpleapp.response.SimpleResponse
import org.springframework.stereotype.Service
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

@Service
open class SimpleService {
    private val monthsDaysNumbers = arrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    private val monthsDaysNumbersOfLeapYear = arrayOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    fun getDateOfProgrammerDay(year: Int): SimpleResponse {
        if (year < 1) {
            return SimpleResponse(400, "")
        }
        val months = if (isLeapYear(year)) monthsDaysNumbersOfLeapYear else monthsDaysNumbers
        var month = 0
        var day = 256
        for (daysNumber in months) {
            if (day < daysNumber) {
                break
            }
            month++
            day -= daysNumber
        }
        val date = Date(GregorianCalendar(year, month, day).time.time)
        val dateFormat = SimpleDateFormat("dd/MM/yy")
        return SimpleResponse(200, dateFormat.format(date))
    }

    fun getDaysToProgrammerDay(currentDate: String): SimpleResponse {
        if (currentDate.length < 5) {
            return SimpleResponse(400, "")
        }
        val day = currentDate.take(2).toIntOrNull()
        val month = currentDate.substring(2, 4).toIntOrNull()
        val year = currentDate.substring(4).toIntOrNull()
        if (day == null || month == null || year == null) {
            return SimpleResponse(400, "")
        }
        if (month < 1 || month > 12 || year < 1 || day < 1) {
            return SimpleResponse(400, "")
        }
        val isLeapYear = isLeapYear(year)
        val months = if (isLeapYear) monthsDaysNumbersOfLeapYear else monthsDaysNumbers
        if (day > months[month - 1]) {
            return SimpleResponse(400, "")
        }
        val yearDaysNumber = if (isLeapYear) 366 else 365
        var daysPassed = 0
        for (i in 0 until (month-1)) {
            daysPassed += months[i]
        }
        daysPassed += day
        val daysToProgrammerDay = if (daysPassed < 256) 256 - daysPassed else yearDaysNumber - daysPassed + 256
        return SimpleResponse(200, "$daysToProgrammerDay")
    }

    private fun isLeapYear(year: Int): Boolean {
        return !(year % 4 != 0 || (year % 100 == 0 && year % 400 != 0))
    }
}