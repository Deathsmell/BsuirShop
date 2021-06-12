package service

import com.mysql.cj.jdbc.Driver
import java.lang.RuntimeException
import java.sql.*
import java.util.*

class DatabaseService {
    var connection: Connection? = null

    fun createConnection(username: String, password: String): Boolean {
        val properties = Properties()
        properties["user"] = username
        properties["password"] = password
        try {
            val driver = Driver()
            connection = driver.connect(
                "jdbc:mysql://localhost:3306/BsuirShop",
                properties
            )
        } catch (ex: SQLException) {
            return false
        }
        return true
    }

    fun executeQuery(query: String): ResultSet? {
            try {
                val statement = getStatement()
                return statement.executeQuery(query)
            } catch (error: SQLException) {
                print("Some trouble with query! Query: $query")
                error.printStackTrace()
            } catch (error: RuntimeException) {
                println(error.message)
            }
        return null
    }

    fun executeUpdate(query: String): Int? {
            try {
                val statement = getStatement()
                return statement.executeUpdate(query)
            } catch (error: SQLException) {
                println("Some trouble with query! Query: $query")
                error.printStackTrace()
            } catch (error: RuntimeException) {
                println(error.message)
            }
        return null
    }

    private fun getStatement(): Statement {
        if (connection !== null) {
            return connection!!.createStatement()
        } else {
            throw RuntimeException("Connection not initialized!!!")
        }
    }
}