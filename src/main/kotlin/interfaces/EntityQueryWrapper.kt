package interfaces

import java.util.*

interface EntityQueryWrapper<T> {
    fun insert(entity: T): String
    fun getById(id: UUID): String
    fun getByName(name: String): String
    fun getAll(): String
}