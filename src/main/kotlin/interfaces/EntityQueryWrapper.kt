package interfaces

interface EntityQueryWrapper<T> {
    fun insert(entity: T): String
}