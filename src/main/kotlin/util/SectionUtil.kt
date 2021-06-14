package util

import java.util.*

class SectionUtil {
    companion object {
        fun getSectionName(): String {
            return InputUtil.getString("Enter section name: ")
        }

        fun getSectionId(): UUID {
            val string = InputUtil.getString("Enter section id: ")
            return UUID.fromString(string)
        }
    }
}