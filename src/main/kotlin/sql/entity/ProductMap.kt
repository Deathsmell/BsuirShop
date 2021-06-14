package sql.entity

enum class ProductMap(val index: Int, val label: String) {
    ID(1, "id"),
    NAME(2, "name"),
    PRICE(3, "price"),
    DESCRIPTION(4, "description"),
    CREATED(5, "created"),
    UPDATED(6, "updated")
}