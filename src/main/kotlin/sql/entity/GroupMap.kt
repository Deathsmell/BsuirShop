package sql.entity

enum class GroupMap(val column: Int, val label: String) {
    ID(1, "id"),
    NAME(2, "name"),
    CREATED(3, "created"),
    UPDATED(4, "updated")
}
