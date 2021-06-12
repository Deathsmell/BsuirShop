package util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        @JvmStatic fun getDateTime(date: Date): String {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd H:mm:ss")
            return simpleDateFormat.format(date)
        }
    }
}