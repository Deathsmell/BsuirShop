package util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        const val PATTERN = "yyyy-MM-dd H:mm:ss"

        @JvmStatic fun getStringDate(date: Date): String {
            val simpleDateFormat = SimpleDateFormat(PATTERN)
            return simpleDateFormat.format(date)
        }

        @JvmStatic fun parseToDate(date: String): Date {
            val dateFormat = SimpleDateFormat(PATTERN)
            return dateFormat.parse(date)
        }
    }
}