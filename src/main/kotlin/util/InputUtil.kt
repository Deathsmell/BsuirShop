package util

import java.util.*

class InputUtil {
    companion object {

        @JvmStatic
        fun getFloat(message: String, errorMessage: String?): Float {
            var floatValue: Float? = null
            while (floatValue === null) {
                print(message)
                val inputValue = readLine()
                floatValue = try {
                    inputValue?.toFloat()
                } catch (error: NumberFormatException) {
                    if (errorMessage !== null) {
                        print("$errorMessage\n")
                    }
                    null
                }
            }
            return floatValue
        }

        @JvmStatic
        fun getInteger(message: String, errorMessage: String?): Int {
            var integerValue: Int? = null
            while (integerValue === null) {
                print(message)
                val inputValue = readLine()
                integerValue = try {
                    inputValue?.toInt()
                } catch (error: NumberFormatException) {
                    if (errorMessage !== null) {
                        print("$errorMessage\n")
                    }
                    null
                }
            }
            return integerValue
        }

        @JvmStatic
        fun getString(message: String): String {
            var stringValue: String? = null
            while (stringValue === null) {
                print(message)
                val value = readLine()?.trim()
                stringValue = if (value !== null) value else {
                    println("Illegal value. Input value not be empty")
                    null
                }
            }
            return stringValue
        }

        @JvmStatic
        fun getBoolean(
            message: String,
            approvals: Iterable<String>,
            disagreements: Iterable<String>,
        ): Boolean {
            while (true) {
                print(message)
                val value = readLine()?.trim()
                if (
                    value !== null
                    && approvals.any(isItEqual(value))
                ) {
                    return true
                }
                if (
                    value !== null
                    && disagreements.any(isItEqual(value))
                ) {
                    return false
                }
                println("You answer not matched with approvals and disagreement variants. Try again!")
                println("Approvals: [${approvals.joinToString(" / ")}]")
                println("Disagreements: [${disagreements.joinToString(" / ")}]")
            }
        }


        private fun isItEqual(value: String): (String) -> Boolean {
            return { t -> t.equals(value, ignoreCase = true) }
        }

        fun getUUID(message: String): UUID {
            var id: UUID? = null
            while (id === null) {
                print(message)
                val value = readLine()?.trim()
                id = if (value !== null) UUID.fromString(value) else {
                    println("Illegal value. Input value not be empty")
                    null
                }
            }
            return id
        }

        @JvmStatic
        fun getIndex(message: String, max: Int): Int {
            var index: Int
            do {
                index = getInteger(message, "Illegal value")
            } while (index < 0 || index >= max)
            return index
        }
    }
}