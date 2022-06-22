package com.example.myapplication.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {
        private const val REQ_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

        fun getDateFormat(dateString: String): String {
            val format = SimpleDateFormat(REQ_DATE_FORMAT)
            var time = String()
            val date: Date
            try {
                val timeFormat = SimpleDateFormat("dd-MM-yyyy'  ' HH:mm")
                date = format.parse(dateString)
                time = timeFormat.format(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return time

        }
    }
}