package `interface`

interface EntityQueryWrapper<T> {
    fun insert(entity: T): String
}